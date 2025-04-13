package joshie.harvest.crops.handlers.rules;

import javax.annotation.Nonnull;
import joshie.harvest.api.core.ISpecialRules;
import joshie.harvest.buildings.HFBuildings;
import joshie.harvest.town.TownHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SpecialRulesRanch implements ISpecialRules {
	@Override
	public boolean canDo(@Nonnull World world, @Nonnull EntityPlayer player, int amount) {
		return TownHelper.getClosestTownToEntity(player, false).hasBuilding(HFBuildings.BARN);
	}
}
