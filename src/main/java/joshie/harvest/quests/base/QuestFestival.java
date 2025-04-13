package joshie.harvest.quests.base;

import java.util.Set;

import joshie.harvest.api.quests.Quest;
import net.minecraftforge.fml.common.eventhandler.EventPriority;

public abstract class QuestFestival extends QuestTown {
	@Override
	public EventPriority getPriority() {
		return EventPriority.HIGHEST;
	}

	@Override
	public boolean canStartQuest(Set<Quest> active, Set<Quest> finished) {
		return false;
	}

	@Override
	public boolean isRepeatable() {
		return true;
	}
}
