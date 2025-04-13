package joshie.harvest.fishing.block;

import java.util.Locale;

import javax.annotation.Nonnull;
import joshie.harvest.core.HFTab;
import joshie.harvest.core.base.block.BlockHFEnum;
import joshie.harvest.core.base.item.ItemBlockHF;
import joshie.harvest.core.proxy.HFClientProxy;
import joshie.harvest.fishing.block.BlockFishTrap.Aquatic;
import joshie.harvest.fishing.item.ItemBlockAquatic;
import joshie.harvest.fishing.tile.TileTrap;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFishTrap extends BlockHFEnum<BlockFishTrap, Aquatic> {
	public BlockFishTrap() {
		super(Material.WATER, Aquatic.class, HFTab.FISHING);
		setHardness(0.1F);
	}

	@Override
	@Nonnull
	protected BlockStateContainer createBlockState() {
		if (property == null) {
			return new BlockStateContainer(this, BlockLiquid.LEVEL, temporary);
		}
		return new BlockStateContainer(this, BlockLiquid.LEVEL, property);
	}

	@Override
	public ItemBlockHF getItemBlock() {
		return new ItemBlockAquatic(this);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return true;
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean causesSuffocation(IBlockState state) {
		return false;
	}

	@Override
	public boolean onBlockActivated(
			World world,
			BlockPos pos,
			IBlockState state,
			EntityPlayer player,
			EnumHand hand,
			EnumFacing side,
			float hitX,
			float hitY,
			float hitZ) {
		TileEntity tile = world.getTileEntity(pos);
		return tile instanceof TileTrap && ((TileTrap) tile).onRightClicked(player, player.getHeldItem(hand));
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, @Nonnull BlockPos pos) {
		return false;
	}

	@Override
	@SuppressWarnings("deprecation")
	@Nonnull
	public IBlockState getActualState(@Nonnull IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tile = world instanceof ChunkCache ?
				((ChunkCache) world).getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK) :
				world.getTileEntity(pos);
		if (tile instanceof TileTrap) {
			TileTrap trap = ((TileTrap) tile);
			if (trap.isBaited()) {
				return getStateFromEnum(Aquatic.TRAP_BAITED);
			} else {
				return getStateFromEnum(Aquatic.TRAP);
			}
		}

		return state;
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	@Nonnull
	public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
		return new TileTrap();
	}

	@Override
	protected boolean shouldDisplayInCreative(Aquatic block) {
		return block != Aquatic.TRAP_BAITED;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(Item item, String name) {
		ModelLoader.setCustomStateMapper(this, HFClientProxy.NO_WATER);
		super.registerModels(item, name);
	}

	public enum Aquatic implements IStringSerializable {
		TRAP, TRAP_BAITED;

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}
