package joshie.harvest.quests.town.festivals.harvest;

import joshie.harvest.HarvestFestival;
import joshie.harvest.api.npc.NPCEntity;
import joshie.harvest.api.npc.greeting.Script;
import joshie.harvest.quests.town.festivals.QuestHarvestFestival;
import net.minecraft.util.text.translation.I18n;

public class ScriptHarvestDecide extends Script {
	public ScriptHarvestDecide() {
		super(HarvestFestival.id("harvest"));
		unlocalised = "harvestfestival.quest.festival.harvest.decide.";
	}

	@Override
	@SuppressWarnings("deprecation")
	public String getLocalized(NPCEntity entity) {
		int score = QuestHarvestFestival.getPotScore(entity);
		return I18n.translateToLocalFormatted(unlocalised + score);
	}
}
