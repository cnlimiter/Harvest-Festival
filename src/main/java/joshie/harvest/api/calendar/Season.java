package joshie.harvest.api.calendar;

import java.util.List;
import java.util.Locale;

import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

public enum Season {
	SPRING(TextFormatting.GREEN),
	SUMMER(TextFormatting.YELLOW),
	AUTUMN(TextFormatting.GOLD),
	WINTER(TextFormatting.WHITE);

	public static final List<Season> VALUES = ImmutableList.copyOf(values());

	private final TextFormatting textColor;

	Season(TextFormatting textColor) {
		this.textColor = textColor;
	}

	@Nonnull
	@SuppressWarnings("deprecation")
	public String getDisplayName() {
		return textColor + I18n.translateToLocal("harvestfestival.season." + name().toLowerCase(Locale.ENGLISH));
	}
}