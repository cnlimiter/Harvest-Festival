package joshie.harvest.quests.player.recipes;

import java.util.Set;

import joshie.harvest.api.quests.HFQuest;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.quests.Quests;
import joshie.harvest.quests.base.QuestRecipe;

@HFQuest("recipe.potsticker")
public class QuestJenni10KPotsticker extends QuestRecipe {
	public QuestJenni10KPotsticker() {
		super("potsticker", HFNPCs.GS_OWNER, 10000);
	}

	@Override
	public boolean canStartQuest(Set<Quest> active, Set<Quest> finished) {
		return finished.contains(Quests.JENNI_5K);
	}
}
