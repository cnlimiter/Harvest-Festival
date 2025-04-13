package joshie.harvest.quests.town.festivals.contest;

import org.apache.commons.lang3.StringEscapeUtils;

import joshie.harvest.HarvestFestival;
import joshie.harvest.api.npc.NPCEntity;
import joshie.harvest.api.npc.greeting.Script;
import joshie.harvest.quests.town.festivals.Place;
import joshie.harvest.town.TownHelper;
import joshie.harvest.town.data.TownData;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ContestWinningScript extends Script {
	public ContestWinningScript(String name) {
		super(HarvestFestival.id(name + "_winner"));
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getLocalized(NPCEntity entity) {
		TownData data = TownHelper.getClosestTownToEntity(entity.getAsEntity(), false);
		QuestContest quest = data.getQuests().getAQuest(data.getFestival().getQuest());
		ContestEntries entries = quest.getEntries();
		World world = entity.getAsEntity().getEntityWorld();
		ContestEntry third = entries.getEntry(Place.THIRD);
		ContestEntry second = entries.getEntry(Place.SECOND);
		ContestEntry first = entries.getEntry(Place.FIRST);
		return StringEscapeUtils.unescapeJava(I18n.translateToLocalFormatted(
				unlocalised, third.getOwnerName(world), third.getName(world),
				second.getOwnerName(world), second.getName(world),
				first.getOwnerName(world), first.getName(world)));
	}
}
