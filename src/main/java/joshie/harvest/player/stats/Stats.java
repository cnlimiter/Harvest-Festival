package joshie.harvest.player.stats;

import javax.annotation.Nullable;
import joshie.harvest.api.calendar.CalendarDate;
import joshie.harvest.api.player.IPlayerStats;

public abstract class Stats implements IPlayerStats {
	protected CalendarDate birthday = null;
	protected long gold;

	@Nullable
	public CalendarDate getBirthday() {
		return birthday;
	}

	@Override
	public long getGold() {
		return gold;
	}
}
