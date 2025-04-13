package joshie.harvest.quests.base;

import java.util.Set;

import joshie.harvest.api.quests.Quest;

public abstract class QuestDummyTown extends QuestTown {
	@Override
	public boolean canStartQuest(Set<Quest> active, Set<Quest> finished) {
		return false;
	}
}
