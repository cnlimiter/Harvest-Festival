package joshie.harvest.town.data;

import joshie.harvest.api.buildings.Building;
import joshie.harvest.api.buildings.BuildingLocation;
import joshie.harvest.api.calendar.CalendarDate;
import joshie.harvest.api.calendar.Festival;
import joshie.harvest.api.calendar.Season;
import joshie.harvest.api.npc.NPC;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.api.town.Town;
import joshie.harvest.buildings.BuildingStage;
import joshie.harvest.core.helpers.NBTHelper;
import joshie.harvest.knowledge.letter.LetterData;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.quests.data.QuestData;
import joshie.harvest.shops.data.ShopData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public abstract class TownData<Q extends QuestData, L extends LetterData> implements Town {
    protected final Set<NPC> inhabitants = new HashSet<>();
    protected final ShopData shops = new ShopData();
    protected Map<ResourceLocation, TownBuilding> buildings = new HashMap<>();
    protected LinkedList<BuildingStage> buildingQueue = new LinkedList<>();
    protected Festival festival = Festival.NONE;
    protected int festivalDays;
    protected CalendarDate birthday;
    protected Quest dailyQuest;
    protected BlockPos townCentre;
    protected UUID uuid;

    /** Overriden to actually return what we should **/
    public abstract Q getQuests();

    public abstract L getLetters();

    public boolean isNull() {
        return false;
    }

    public Quest getDailyQuest() {
        return dailyQuest;
    }

    public CalendarDate getBirthday() {
        return birthday;
    }

    public UUID getID() {
        return uuid;
    }

    public ShopData getShops() {
        return shops;
    }

    public TownBuilding getBuilding(Building building) {
        return buildings.get(building.getResource());
    }

    public TownBuilding getBuilding(ResourceLocation building) {
        return buildings.get(building);
    }

    /** Building currently being worked on **/
    public BuildingStage getCurrentlyBuilding() {
        return buildingQueue.size() > 0 ? buildingQueue.getFirst() : null;
    }

    /** If this building is being built currently **/
    public boolean isBuilding(Building building) {
        if (building == null)
            return buildingQueue.size() > 0;
        return buildingQueue.contains(new BuildingStage(building, BlockPos.ORIGIN, Rotation.NONE));
    }

    public boolean hasBuilding(ResourceLocation building) {
        return buildings.get(building) != null;
    }

    @Override
    public boolean hasBuilding(Building building) {
        return buildings.get(building.getResource()) != null;
    }

    @Override
    public boolean hasNPC(NPC npc) {
        return getInhabitants().contains(npc);
    }

    public boolean hasBuildings(ResourceLocation[] buildings) {
        for (ResourceLocation building : buildings) {
            if (this.buildings.get(building) == null)
                return false;
        }

        return true;
    }

    @Override
    public int getBuildingCount() {
        return getBuildings().size();
    }

    @Override
    @Nullable
    public BlockPos getCoordinatesFor(@Nonnull BuildingLocation location) {
        TownBuilding building = buildings.get(location.getBuilding().getResource());
        return building != null ? building.getRealCoordinatesFor(location.getLocation()) : null;
    }

    @Override
    @Nullable
    public BlockPos getCoordinatesFromOffset(Building building, BlockPos offset) {
        TownBuilding townBuilding = buildings.get(building.getResource());
        return townBuilding != null ? townBuilding.getTransformedPosition(offset) : null;
    }

    @Nonnull
    public Festival getFestival() {
        return festival;
    }

    public Set<NPC> getInhabitants() {
        return inhabitants;
    }

    public Collection<TownBuilding> getBuildings() {
        return buildings.values();
    }

    @Nonnull
    public BlockPos getTownCentre() {
        return townCentre;
    }

    public double getRange() {
        return HFNPCs.TOWN_DISTANCE;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        if (nbt.hasKey("Created"))
            birthday = CalendarDate.fromNBT(nbt.getCompoundTag("Created"));
        else
            birthday = new CalendarDate(0, Season.SPRING, 0);
        shops.readFromNBT(nbt);
        uuid = NBTHelper.readUUID("Town", nbt);
        townCentre = NBTHelper.readBlockPos("TownCentre", nbt);
        NBTHelper.readMap("TownBuildingList", TownBuilding.class, buildings, nbt);
        NBTHelper.readList("CurrentlyBuilding", BuildingStage.class, buildingQueue, nbt);
        for (TownBuilding building : buildings.values()) {
            inhabitants.addAll(building.building.getInhabitants());
        }

        if (nbt.hasKey("DailyQuest")) {
            dailyQuest = Quest.REGISTRY.getValue(new ResourceLocation(nbt.getString("DailyQuest")));
            dailyQuest.readFromNBT(nbt.getCompoundTag("DailyQuestData"));
        }

        if (nbt.hasKey("Festival")) {
            festival = Festival.REGISTRY.get(new ResourceLocation(nbt.getString("Festival")));
            festivalDays = nbt.getInteger("FestivalDaysRemaining");
        }

        //Fix the broken festivla if it applies
        if (festival == null)
            festival = Festival.NONE;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setTag("Created", birthday.toNBT());
        shops.writeToNBT(nbt);
        NBTHelper.writeBlockPos("TownCentre", nbt, townCentre);
        NBTHelper.writeUUID("Town", nbt, uuid);
        NBTHelper.writeMap("TownBuildingList", nbt, buildings);
        NBTHelper.writeList("CurrentlyBuilding", nbt, buildingQueue);
        if (dailyQuest != null) {
            nbt.setString("DailyQuest", String.valueOf(dailyQuest.getRegistryName()));
            nbt.setTag("DailyQuestData", dailyQuest.writeToNBT(new NBTTagCompound()));
        }

        if (festival != null) {
            nbt.setString("Festival", festival.getResource().toString());
            nbt.setInteger("FestivalDaysRemaining", festivalDays);
        }

        return nbt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TownData townData = (TownData) o;
        return uuid != null ? uuid.equals(townData.uuid) : townData.uuid == null;
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }
}
