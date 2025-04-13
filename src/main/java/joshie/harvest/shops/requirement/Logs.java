package joshie.harvest.shops.requirement;

import joshie.harvest.core.helpers.InventoryHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Logs extends AbstractRequirement {
	private static final ItemStack logItem = new ItemStack(Blocks.LOG);

	private Logs(ItemStack icon, int cost) {
		super(icon, cost);
	}

	public static Logs of(int amount) {
		return new Logs(logItem, amount);
	}

	@Override
	public boolean isFulfilled(World world, EntityPlayer player, int amount) {
		return InventoryHelper.hasInInventory(player, InventoryHelper.ORE_DICTIONARY, "logWood", (cost * amount));
	}

	@Override
	public void onPurchased(EntityPlayer player) {
		InventoryHelper.takeItemsInInventory(player, InventoryHelper.ORE_DICTIONARY, "logWood", cost);
	}
}
