package joshie.harvest.shops.requirement;

import joshie.harvest.core.helpers.InventoryHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Glass extends AbstractRequirement {
	private static final ItemStack glassItem = new ItemStack(Blocks.GLASS);

	private Glass(ItemStack icon, int cost) {
		super(icon, cost);
	}

	public static Glass of(int amount) {
		return new Glass(glassItem, amount);
	}

	@Override
	public boolean isFulfilled(World world, EntityPlayer player, int amount) {
		return InventoryHelper.hasInInventory(player, InventoryHelper.ORE_DICTIONARY, "blockGlass", (cost * amount));
	}

	@Override
	public void onPurchased(EntityPlayer player) {
		InventoryHelper.takeItemsInInventory(player, InventoryHelper.ORE_DICTIONARY, "blockGlass", cost);
	}
}
