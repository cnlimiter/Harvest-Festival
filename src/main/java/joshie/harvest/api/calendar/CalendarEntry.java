package joshie.harvest.api.calendar;

import java.util.List;

import javax.annotation.Nonnull;
import net.minecraft.item.ItemStack;

public interface CalendarEntry {
	/**
	 * Returns this item as a stack, to be used for displaying in the calendar
	 **/
	@Nonnull
	ItemStack getStackRepresentation();

	/**
	 * Add tooltip for displaying in the calender
	 *
	 * @param tooltip add to this list
	 **/
	void addTooltipForCalendarEntry(List<String> tooltip);
}