package joshie.harvest.knowledge.gui.calendar;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import joshie.harvest.HarvestFestival;
import joshie.harvest.api.HFApi;
import joshie.harvest.api.calendar.CalendarDate;
import joshie.harvest.api.calendar.CalendarEntry;
import joshie.harvest.api.calendar.Season;
import joshie.harvest.api.calendar.Weekday;
import joshie.harvest.api.npc.NPC;
import joshie.harvest.api.npc.RelationStatus;
import joshie.harvest.calendar.CalendarAPI;
import joshie.harvest.calendar.CalendarHelper;
import joshie.harvest.core.base.gui.ContainerNull;
import joshie.harvest.core.base.gui.GuiBase;
import joshie.harvest.knowledge.gui.calendar.button.ButtonDate;
import joshie.harvest.knowledge.gui.calendar.button.ButtonNext;
import joshie.harvest.knowledge.gui.calendar.button.ButtonPrevious;
import joshie.harvest.shops.gui.ShopFontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GuiCalendar extends GuiBase {
	public static final ResourceLocation CALENDAR_TEXTURE = HarvestFestival.id("textures/gui/calendar.png");
	private final Multimap<CalendarDate, CalendarEntry> entries = HashMultimap.create();
	public static CalendarDate date;
	public static Season season;
	public static int year = -1;
	private int rows;


	public GuiCalendar(EntityPlayer player) {
		super(new ContainerNull(), "calendar", 36);
		date = HFApi.calendar.getDate(player.world);
		season = date.getSeason();
		year = date.getYear();
		xSize = 226;

		CalendarAPI.INSTANCE.getFestivals().forEach((key, value) -> {
			if (!value.isHidden()) {
				entries.get(getNextDay(key)).add(value);
			}
		});

		NPC.REGISTRY.values().forEach(npc -> {
			if (HFApi.player.getRelationsForPlayer(player).isStatusMet(npc, RelationStatus.MET)) {
				entries.get(npc.getBirthday()).add(npc);
			}
		});
	}

	private CalendarDate getNextDay(CalendarDate date) {
		if (date.getDay() < 30) {
			return new CalendarDate(date.getDay() + 1, date.getSeason(), date.getYear());
		} else {
			if (date.getSeason() == Season.WINTER) {
				return new CalendarDate(1, Season.SPRING, date.getYear());
			} else {
				Season next = CalendarHelper.SEASONS[season.ordinal() + 1];
				return new CalendarDate(1, next, date.getYear());
			}
		}
	}

	@Override
	public void initGui() {
		super.initGui();
		buttonList.clear();
		rows = getNumberOfRows();
		//int yExtra = rows
		int y = guiTop + 30 - 6;
		for (int day = 1; day <= 30; day++) {
			CalendarDate date = new CalendarDate(day, season, year);
			int x = 14 + date.getWeekday().ordinal() * 30;
			buttonList.add(new ButtonDate(
					this,
					day,
					getStacksForDate(new CalendarDate(day * (CalendarDate.DAYS_PER_SEASON / 30), season, year)),
					x,
					y));
			if (date.getWeekday() == Weekday.SATURDAY) {
				y += 30;
			}
		}

		if ((GuiCalendar.year > 1 || (GuiCalendar.year == 1 && GuiCalendar.season != Season.SPRING))) {
			buttonList.add(new ButtonPrevious(this, buttonList.size(), guiLeft + 30, guiTop - 12));
		}

		buttonList.add(new ButtonNext(this, buttonList.size(), guiLeft + 180, guiTop - 12));
	}

	private List<CalendarEntry> getStacksForDate(CalendarDate date) {
		return entries.entries().stream().filter(entry -> CalendarHelper.isDateSame(date, entry.getKey())).map(Entry::getValue).collect(
				Collectors.toList());
	}

	private int getNumberOfRows() {
		CalendarDate date = new CalendarDate(1, season, year);
		return date.getWeekday() == Weekday.SATURDAY ? 6 : 5;
	}

	@Override
	protected void drawGuiTexture() {
		GlStateManager.color(1F, 1F, 1F);
		mc.renderEngine.bindTexture(CALENDAR_TEXTURE);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, 22);
	}

	@Override
	public void drawBackground(int x, int y) {
		for (int i = 0; i < rows; i++) {
			if (i == 0) {
				drawTexturedModalRect(guiLeft + 10, guiTop + 22, 8, 26, 210, 26);
			} else {
				drawTexturedModalRect(guiLeft + 10, guiTop + 18 + 30 * i, 8, 22, 210, 30);
			}
		}

		drawTexturedModalRect(guiLeft + 10, guiTop + 18 + 30 * rows, 8, 52, 210, 2);
		drawCenteredString(
				fontRenderer,
				season.getDisplayName() + TextFormatting.RESET + " - Year " + year,
				guiLeft + 113,
				guiTop - 10,
				0xFFFFFF);
	}

	@Override
	public void drawForeground(int x, int y) {
		for (Weekday weekday : CalendarHelper.DAYS) {
			GlStateManager.color(1F, 1F, 1F);
			RenderHelper.disableStandardItemLighting();
			ShopFontRenderer.render(this, 12 + weekday.ordinal() * 30, 10, weekday.getLocalizedName(), 1F);
		}
	}
}
