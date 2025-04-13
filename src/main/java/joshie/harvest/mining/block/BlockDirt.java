package joshie.harvest.mining.block;

import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import joshie.harvest.core.HFTab;
import joshie.harvest.core.base.block.BlockHFBase;
import joshie.harvest.core.helpers.TextHelper;
import joshie.harvest.mining.HFMining;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDirt extends BlockHFBase<BlockDirt> {
	private static final PropertyEnum<TextureStyle> NORTH_EAST = PropertyEnum.create("ne", TextureStyle.class);
	private static final PropertyEnum<TextureStyle> NORTH_WEST = PropertyEnum.create("nw", TextureStyle.class);
	private static final PropertyEnum<TextureStyle> SOUTH_EAST = PropertyEnum.create("se", TextureStyle.class);
	private static final PropertyEnum<TextureStyle> SOUTH_WEST = PropertyEnum.create("sw", TextureStyle.class);

	public enum TextureStyle implements IStringSerializable {
		BLANK, INNER, VERTICAL, HORIZONTAL, OUTER;

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}

	public BlockDirt() {
		super(Material.GROUND, HFTab.MINING);
		setSoundType(SoundType.GROUND);
		setDefaultState(getDefaultState()
				.withProperty(NORTH_EAST, BlockDirt.TextureStyle.OUTER)
				.withProperty(NORTH_WEST, BlockDirt.TextureStyle.OUTER)
				.withProperty(SOUTH_EAST, BlockDirt.TextureStyle.OUTER)
				.withProperty(SOUTH_WEST, BlockDirt.TextureStyle.OUTER));
	}

	@Override
	@Nonnull
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
	}

	@Override
	public String getItemStackDisplayName(@Nonnull ItemStack stack) {
		return TextHelper.localizeFully(getUnlocalizedName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.getItem() == Item.getItemFromBlock(HFMining.DIRT_DECORATIVE)) {
			tooltip.add(TextFormatting.YELLOW + TextHelper.translate("tooltip.cosmetic"));
		}
	}

	//TECHNICAL/
	@Override
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
		return blockHardness != -1F;
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, @Nonnull IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public boolean canSustainPlant(
			@Nonnull IBlockState state,
			@Nonnull IBlockAccess world,
			BlockPos pos,
			@Nonnull EnumFacing direction,
			IPlantable plantable) {
		return plantable.getPlantType(world, pos.up()) == EnumPlantType.Plains;
	}

	private boolean isSameBlock(IBlockAccess world, BlockPos pos) {
		return world.getBlockState(pos).getBlock() == this;
	}

	private TextureStyle getStateFromBoolean(boolean one, boolean two, boolean three) {
		if (one && !two && !three) {
			return BlockDirt.TextureStyle.VERTICAL;
		}
		if (!one && two && !three) {
			return BlockDirt.TextureStyle.HORIZONTAL;
		}
		if (one && two && !three) {
			return BlockDirt.TextureStyle.INNER;
		}
		if (!one && two) {
			return BlockDirt.TextureStyle.HORIZONTAL;
		}
		if (one && !two) {
			return BlockDirt.TextureStyle.VERTICAL;
		}
		if (one) {
			return BlockDirt.TextureStyle.BLANK;
		}
		return BlockDirt.TextureStyle.OUTER;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@Nonnull
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@SuppressWarnings("deprecation, unchecked")
	@Override
	@Nonnull
	public IBlockState getActualState(@Nonnull IBlockState state, IBlockAccess world, BlockPos pos) {
		boolean north = isSameBlock(world, pos.north());
		boolean west = isSameBlock(world, pos.west());
		boolean south = isSameBlock(world, pos.south());
		boolean east = isSameBlock(world, pos.east());
		boolean northEast = north && east && isSameBlock(world, pos.north().east());
		boolean northWest = north && west && isSameBlock(world, pos.north().west());
		boolean southEast = south && east && isSameBlock(world, pos.south().east());
		boolean southWest = south && west && isSameBlock(world, pos.south().west());
		TextureStyle ne = getStateFromBoolean(north, east, northEast);
		TextureStyle nw = getStateFromBoolean(north, west, northWest);
		TextureStyle se = getStateFromBoolean(south, east, southEast);
		TextureStyle sw = getStateFromBoolean(south, west, southWest);
		return state.withProperty(NORTH_EAST, ne).withProperty(NORTH_WEST, nw).withProperty(SOUTH_EAST, se).withProperty(SOUTH_WEST, sw);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(Item item, String name) {
		ModelLoader.setCustomModelResourceLocation(
				item,
				0,
				new ModelResourceLocation("harvestfestival:dirt", "ne=outer,nw=outer,se=outer,sw=outer"));
	}
}