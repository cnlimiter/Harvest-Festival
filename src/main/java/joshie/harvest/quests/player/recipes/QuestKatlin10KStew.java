package joshie.harvest.quests.player.recipes;

import java.util.Set;

import joshie.harvest.api.quests.HFQuest;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.quests.Quests;
import joshie.harvest.quests.base.QuestRecipe;

@HFQuest("recipe.stew")
public class QuestKatlin10KStew extends QuestRecipe {
	public QuestKatlin10KStew() {
		super("stew", HFNPCs.CAFE_GRANNY, 10000);
	}

	@Override
	public boolean canStartQuest(Set<Quest> active, Set<Quest> finished) {
		return finished.contains(Quests.KATLIN_5K);
	}
}
