package joshie.harvest.quests.base;

import joshie.harvest.api.quests.Quest;
import net.minecraftforge.fml.common.eventhandler.EventPriority;

public class QuestTrade extends Quest {
	public QuestTrade() {}

	@Override
	public EventPriority getPriority() {
		return EventPriority.HIGH;
	}

	@Override
	public boolean isRepeatable() {
		return true;
	}

	@Override
	public boolean isRealQuest() {
		return false;
	}
}
