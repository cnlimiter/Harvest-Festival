package joshie.harvest.calendar;

import gnu.trove.map.TIntIntMap;
import javax.annotation.Nonnull;
import joshie.harvest.api.calendar.CalendarDate;
import joshie.harvest.api.calendar.Season;
import joshie.harvest.api.calendar.Weekday;
import joshie.harvest.calendar.data.CalendarServer;
import joshie.harvest.core.HFTrackers;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class CalendarHelper {
	public static final Season[] SEASONS;
	public static final Weekday[] DAYS;

	static {
		SEASONS = Season.class.getEnumConstants();
		DAYS = Weekday.class.getEnumConstants();
	}

	//Dates are 1-30
	public static boolean isDateSame(CalendarDate today, CalendarDate dateOffByOne) {
		return today.getDay() == dateOffByOne.getDay() && today.getSeason() == dateOffByOne.getSeason();
	}

	public static Weekday getWeekday(int days) {
		int modulus = days % 7;
		if (modulus < 0) {
			modulus = 0;
		}
		return DAYS[modulus];
	}

	public static void setDate(World world, CalendarDate date) {
		long time = world.getWorldTime();
		Season previous = date.getSeason();
		date.setDate(getDay(time), getSeason(time), getYear(time));
		if (previous != date.getSeason()) {
			HFTrackers.getCalendar(world).onSeasonChanged();
		}
	}

	private static int getYear(long totalTime) {
		return (int) Math.floor((double) getElapsedDays(totalTime) / 4 / CalendarDate.DAYS_PER_SEASON) + 1;
	}

	public static Season getSeason(long totalTime) {
		return SEASONS[Math.max(0, (int) (double) ((getElapsedDays(totalTime) / CalendarDate.DAYS_PER_SEASON) % 4))];
	}

	private static int getDay(long totalTime) {
		return getElapsedDays(totalTime) % CalendarDate.DAYS_PER_SEASON;
	}

	public static int getElapsedDays(long totalTime) {
		return (int) (totalTime / HFCalendar.TICKS_PER_DAY);
	}

	private static int getTotalDays(int day, Season season, int year) {
		int season_days = CalendarDate.DAYS_PER_SEASON * season.ordinal();
		int year_days = (year - 1) * (CalendarDate.DAYS_PER_SEASON * 4);
		return day + season_days + year_days;
	}

	public static int getTotalDays(CalendarDate date) {
		return getTotalDays(date.getDay(), date.getSeason(), date.getYear());
	}

	public static int getYearsPassed(@Nonnull CalendarDate birthday, @Nonnull CalendarDate date) {
		double current_total_days = getTotalDays(date);
		double birthday_total_days = getTotalDays(birthday);
		int one_year = CalendarDate.DAYS_PER_SEASON * 4;

		int years_passed = (int) Math.floor(current_total_days / one_year);
		int birthday_years = (int) Math.floor(birthday_total_days / one_year);

		return Math.max(0, years_passed - birthday_years);
	}

	public static long getTime(int day, Season season, int year) {
		return (getTotalDays(day, season, year)) * HFCalendar.TICKS_PER_DAY;
	}

	public static long getTime(World world) {
		return (world.getWorldTime() + 6000) % HFCalendar.TICKS_PER_DAY;
	}

	public static int getScaledTime(int time) {
		return (int) (((double) time / HFCalendar.TICKS_PER_DAY) * 24000D);
	}

	public static void setWorldTime(MinecraftServer server, long worldTime) {
		worldTime = Math.max(0L, worldTime);
		for (int j = 0; j < server.worlds.length; ++j) {
			WorldServer worldserver = server.worlds[j];
			worldserver.setWorldTime(worldTime);
		}

		if (worldTime % HFCalendar.TICKS_PER_DAY != 23999) {
			CalendarServer calendar = HFTrackers.getCalendar(server.worlds[0]);
			calendar.recalculateAndUpdate(server.worlds[0]);
		}
	}

	public static int getBlendedColour(TIntIntMap map, int original, int additional) {
		try {
			int ret = map.get(original);
			if (ret != map.getNoEntryValue()) {
				return ret;
			} else {
				int size = 2;
				int r = (original & 0xFF0000) >> 16;
				int g = (original & 0x00FF00) >> 8;
				int b = original & 0x0000FF;
				r += (additional & 0xFF0000) >> 16;
				g += (additional & 0x00FF00) >> 8;
				b += additional & 0x0000FF;

				int value = (r / size & 255) << 16 | (g / size & 255) << 8 | b / size & 255;
				map.put(original, value);
				return value;
			}
		} catch (IndexOutOfBoundsException exception) {
			return original;
		}
	}

	public static boolean isBetween(World world, int open, int close) {
		long daytime = CalendarHelper.getTime(world); //0-23999 by default
		int scaledOpening = CalendarHelper.getScaledTime(open);
		int scaledClosing = CalendarHelper.getScaledTime(close);
		return daytime >= scaledOpening && daytime <= scaledClosing;
	}

	public static int getDays(CalendarDate then, CalendarDate now) {
		int thenDays = getTotalDays(then);
		int nowDays = getTotalDays(now);
		return (nowDays - thenDays);
	}
}
