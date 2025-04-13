package joshie.harvest.quests.player.recipes;

import java.util.Set;

import joshie.harvest.api.quests.HFQuest;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.quests.Quests;
import joshie.harvest.quests.base.QuestRecipe;

@HFQuest("recipe.icecream")
public class QuestCandice10KIceCream extends QuestRecipe {
	public QuestCandice10KIceCream() {
		super("ice_cream", HFNPCs.MILKMAID, 10000);
	}

	@Override
	public boolean canStartQuest(Set<Quest> active, Set<Quest> finished) {
		return finished.contains(Quests.CANDICE_5K);
	}
}
