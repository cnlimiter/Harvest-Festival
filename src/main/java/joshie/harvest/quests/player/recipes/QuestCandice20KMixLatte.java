package joshie.harvest.quests.player.recipes;

import java.util.Set;

import joshie.harvest.api.quests.HFQuest;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.quests.Quests;
import joshie.harvest.quests.base.QuestRecipe;

@HFQuest("recipe.latte.mix")
public class QuestCandice20KMixLatte extends QuestRecipe {
	public QuestCandice20KMixLatte() {
		super("latte_mix", HFNPCs.MILKMAID, 20000);
	}

	@Override
	public boolean canStartQuest(Set<Quest> active, Set<Quest> finished) {
		return finished.contains(Quests.CANDICE_15K);
	}
}
