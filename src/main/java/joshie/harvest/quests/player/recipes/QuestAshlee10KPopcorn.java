package joshie.harvest.quests.player.recipes;

import java.util.Set;

import joshie.harvest.api.quests.HFQuest;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.quests.Quests;
import joshie.harvest.quests.base.QuestRecipe;

@HFQuest("recipe.popcorn")
public class QuestAshlee10KPopcorn extends QuestRecipe {
	public QuestAshlee10KPopcorn() {
		super("popcorn", HFNPCs.POULTRY, 10000);
	}

	@Override
	public boolean canStartQuest(Set<Quest> active, Set<Quest> finished) {
		return finished.contains(Quests.ASHLEE_5K);
	}
}
