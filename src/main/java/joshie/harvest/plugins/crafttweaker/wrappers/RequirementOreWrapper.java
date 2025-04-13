package joshie.harvest.plugins.crafttweaker.wrappers;

import joshie.harvest.core.helpers.InventoryHelper;
import joshie.harvest.shops.requirement.AbstractRequirement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class RequirementOreWrapper extends AbstractRequirement {
	private final String name;

	public RequirementOreWrapper(String ore, int cost) {
		super(OreDictionary.getOres(ore).get(0), cost);
		name = ore;
	}

	@Override
	public boolean isFulfilled(World world, EntityPlayer player, int amount) {
		return InventoryHelper.hasInInventory(player, InventoryHelper.ORE_DICTIONARY, name, (cost * amount));
	}

	@Override
	public void onPurchased(EntityPlayer player) {
		InventoryHelper.takeItemsInInventory(player, InventoryHelper.ORE_DICTIONARY, "ingotBrick", cost);
	}
}
