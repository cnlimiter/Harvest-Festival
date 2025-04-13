package joshie.harvest.api.calendar;

import java.util.List;

import com.google.common.collect.ImmutableList;

public enum Weather {
	SUNNY, RAIN, TYPHOON, SNOW, BLIZZARD;

	public static final List<Weather> VALUES = ImmutableList.copyOf(values());

	public boolean isRain() {
		return this == RAIN || this == TYPHOON;
	}

	public boolean isSnow() {
		return this == SNOW || this == BLIZZARD;
	}

	public boolean isBadWeather() {
		return this == TYPHOON || this == BLIZZARD;
	}

	public boolean isUndesirable() {
		return isBadWeather() || isRain();
	}

	public boolean isSunny() {
		return this == SUNNY;
	}
}