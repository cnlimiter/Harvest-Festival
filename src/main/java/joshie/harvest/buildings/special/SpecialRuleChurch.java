package joshie.harvest.buildings.special;

import javax.annotation.Nonnull;
import joshie.harvest.api.core.ISpecialRules;
import joshie.harvest.town.TownHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SpecialRuleChurch implements ISpecialRules {
	@Override
	public boolean canDo(@Nonnull World world, @Nonnull EntityPlayer player, int amount) {
		return amount == 1 && TownHelper.getClosestTownToEntity(player, false).getInhabitants().size() >= 9;
	}
}
