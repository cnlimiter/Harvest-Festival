package joshie.harvest.town.data;

import joshie.harvest.api.buildings.Building;
import joshie.harvest.api.calendar.Festival;
import joshie.harvest.api.npc.NPC;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.buildings.BuildingStage;
import joshie.harvest.knowledge.letter.LetterDataClient;
import joshie.harvest.quests.data.QuestDataClient;
import net.minecraft.util.math.BlockPos;

import java.util.LinkedList;

public class TownDataClient extends TownData<QuestDataClient, LetterDataClient> {
    private final QuestDataClient quest = new QuestDataClient();
    private final LetterDataClient letters = new LetterDataClient();

    @Override
    public QuestDataClient getQuests() {
        return quest;
    }

    @Override
    public LetterDataClient getLetters() {
        return letters;
    }

    public void removeBuilding(Building building) {
        buildings.remove(building.getResource());
        for (NPC inhabitant : building.getInhabitants()) {
            inhabitants.remove(inhabitant);
        }
    }

    public void addBuilding(TownBuilding building) {
        buildings.put(building.building.getResource(), building);
        for (NPC inhabitant : building.building.getInhabitants()) {
            inhabitants.computeIfAbsent(inhabitant, $ -> null);
        }
    }

    public void setBuilding(LinkedList<BuildingStage> buildingQueue) {
        this.buildingQueue = buildingQueue;
    }

    public void setCentre(BlockPos centre) {
        this.townCentre = centre;
    }

    public void setDailyQuest(Quest dailyQuest) {
        this.dailyQuest = dailyQuest;
    }

    public void setFestival(Festival festival, int days) {
        this.festival = festival;
        this.festivalDays = days;
    }
}