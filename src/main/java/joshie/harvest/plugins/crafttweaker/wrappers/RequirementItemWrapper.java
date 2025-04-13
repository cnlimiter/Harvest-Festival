package joshie.harvest.plugins.crafttweaker.wrappers;

import javax.annotation.Nonnull;
import joshie.harvest.core.helpers.InventoryHelper;
import joshie.harvest.shops.requirement.AbstractRequirement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RequirementItemWrapper extends AbstractRequirement {
	public RequirementItemWrapper(@Nonnull ItemStack stack, int cost) {
		super(stack, cost);
	}

	@Override
	public boolean isFulfilled(World world, EntityPlayer player, int amount) {
		return InventoryHelper.hasInInventory(player, InventoryHelper.ITEM_STACK, getIcon(), (cost * amount));
	}

	@Override
	public void onPurchased(EntityPlayer player) {
		InventoryHelper.takeItemsInInventory(player, InventoryHelper.ITEM_STACK, getIcon(), cost);
	}
}
