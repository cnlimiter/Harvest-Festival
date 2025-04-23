package joshie.harvest.api.calendar;

import joshie.harvest.HarvestFestival;
import joshie.harvest.calendar.CalendarHelper;
import net.minecraft.nbt.NBTTagCompound;

public class CalendarDate {
	/**
	 * This gets set by the config files, so it won't ALWAYS be 30
	 */
	public static int DAYS_PER_SEASON = 30;
	private Weekday weekday;
	private int day;
	private Season season;
	private int year;

	public CalendarDate(int day, Season season) {
		this(day, season, 1);
	}

	public CalendarDate(int day, Season season, int year) {
		setDate(day, season, year);
	}

	/**
	 * Make a copy of this date
	 **/
	public CalendarDate copy() {
		CalendarDate date = new CalendarDate(day, season, year);
		date.weekday = weekday;
		return date;
	}

	/**
	 * Update the internal values of this date
	 *
	 * @param day    the day of the season
	 * @param season the season
	 * @param year   the year
	 * @return the full date
	 */
	public CalendarDate setDate(int day, Season season, int year) {
		this.day = day;
		this.season = season;
		this.year = year;
		if (day == 0) {
			HarvestFestival.LOGGER.error("Day cannot be 0, setting to 1");
			this.day = 1;
		}
		if (year == 0) {
			HarvestFestival.LOGGER.error("Year cannot be 0, setting to 1");
			this.year = 1;
		}
		weekday = null;
		return this;
	}

	/**
	 * @return the day of the week
	 **/
	public Weekday getWeekday() {
		if (weekday == null) {
			weekday = CalendarHelper.getWeekday(CalendarHelper.getTotalDays(this));
		}
		return weekday;
	}

	/**
	 * @return the day of the season
	 **/
	public int getDay() {
		return day;
	}

	/**
	 * @return the season
	 **/
	public Season getSeason() {
		return season;
	}

	/**
	 * @return the year
	 **/
	public int getYear() {
		return year;
	}

	/**
	 * Load a date from a nbt tag
	 *
	 * @param nbt the tag to read
	 * @return the date
	 */
	public static CalendarDate fromNBT(NBTTagCompound nbt) {
		int day = Math.max(1, nbt.getInteger("Day"));
		Season season = Season.VALUES.get(nbt.getByte("Season"));
		int year = Math.max(1, nbt.getInteger("Year"));
		return new CalendarDate(day, season, year);
	}

	/**
	 * Save a date to nbt
	 **/
	public NBTTagCompound toNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("Day", day);
		nbt.setByte("Season", (byte) season.ordinal());
		nbt.setInteger("Year", year);
		return nbt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CalendarDate that = (CalendarDate) o;
		return day == that.day && year == that.year && season == that.season;
	}

	@Override
	public int hashCode() {
		int result = day;
		result = 31 * result + (season != null ? season.hashCode() : 0);
		result = 31 * result + year;
		return result;
	}
}