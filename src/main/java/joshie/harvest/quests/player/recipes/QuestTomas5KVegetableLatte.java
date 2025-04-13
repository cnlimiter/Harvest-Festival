package joshie.harvest.quests.player.recipes;

import java.util.Set;

import joshie.harvest.api.quests.HFQuest;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.quests.Quests;
import joshie.harvest.quests.base.QuestRecipe;

@HFQuest("recipe.latte.vegetable")
public class QuestTomas5KVegetableLatte extends QuestRecipe {
	public QuestTomas5KVegetableLatte() {
		super("latte_vegetable", HFNPCs.PRIEST, 5000);
	}

	@Override
	public boolean canStartQuest(Set<Quest> active, Set<Quest> finished) {
		return finished.contains(Quests.TOMAS_MEET);
	}
}
