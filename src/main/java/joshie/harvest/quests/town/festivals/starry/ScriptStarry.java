package joshie.harvest.quests.town.festivals.starry;

import joshie.harvest.HarvestFestival;
import joshie.harvest.api.npc.NPCEntity;
import joshie.harvest.api.npc.greeting.Script;
import net.minecraft.util.text.translation.I18n;

public class ScriptStarry extends Script {
	public ScriptStarry(String name) {
		super(HarvestFestival.id("starry_" + name));
		unlocalised = "%s.npc.%s..festival.starry.night." + name;
	}

	@Override
	@SuppressWarnings("deprecation")
	public String getLocalized(NPCEntity entity) {
		return I18n.translateToLocalFormatted(
				unlocalised,
				entity.getNPC().getResource().getResourceDomain(),
				entity.getNPC().getResource().getResourcePath());
	}
}
