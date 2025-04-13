package joshie.harvest.mining.item;

import java.util.Locale;

import javax.annotation.Nonnull;
import joshie.harvest.HarvestFestival;
import joshie.harvest.core.HFTab;
import joshie.harvest.core.base.item.ItemHFEnum;
import joshie.harvest.mining.entity.EntityDarkChick;
import joshie.harvest.mining.entity.EntityDarkChicken;
import joshie.harvest.mining.entity.EntityDarkCow;
import joshie.harvest.mining.entity.EntityDarkSheep;
import joshie.harvest.mining.item.ItemDarkSpawner.DarkSpawner;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDarkSpawner extends ItemHFEnum<ItemDarkSpawner, DarkSpawner> {
	public enum DarkSpawner implements IStringSerializable {
		COW, SHEEP, CHICKEN, CHICK;

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}

	public ItemDarkSpawner() {
		super(HFTab.MINING, DarkSpawner.class);
	}

	private EntityMob getEntityFromEnum(World world, DarkSpawner spawner) {
		switch (spawner) {
			case COW:
				return new EntityDarkCow(world);
			case SHEEP:
				return new EntityDarkSheep(world);
			case CHICKEN:
				return new EntityDarkChicken(world);
			case CHICK:
				return new EntityDarkChick(world);
			default:
				return null;
		}
	}

	@Override
	@Nonnull
	public EnumActionResult onItemUse(
			EntityPlayer player,
			World world,
			BlockPos pos,
			EnumHand hand,
			EnumFacing facing,
			float hitX,
			float hitY,
			float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote) {
			EntityMob entity = getEntityFromEnum(world, getEnumFromStack(stack));
			if (entity != null) {
				entity.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
				world.spawnEntity(entity);
			}
		}

		stack.shrink(1);
		return EnumActionResult.SUCCESS;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(Item item, String name) {
		for (int i = 0; i < values.length; i++) {
			ModelLoader.setCustomModelResourceLocation(
					item,
					i,
					new ModelResourceLocation(
							HarvestFestival.id("dark_spawner_" + values[i].name().toLowerCase(Locale.ENGLISH)),
							"inventory"));
		}
	}
}