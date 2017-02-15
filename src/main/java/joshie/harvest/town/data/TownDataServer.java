package joshie.harvest.town.data;

import com.google.common.cache.Cache;
import joshie.harvest.api.HFApi;
import joshie.harvest.api.buildings.Building;
import joshie.harvest.api.calendar.CalendarDate;
import joshie.harvest.api.calendar.Festival;
import joshie.harvest.api.npc.NPC;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.api.quests.TargetType;
import joshie.harvest.buildings.BuildingStage;
import joshie.harvest.buildings.HFBuildings;
import joshie.harvest.calendar.CalendarAPI;
import joshie.harvest.calendar.CalendarHelper;
import joshie.harvest.calendar.HFFestivals;
import joshie.harvest.core.HFTrackers;
import joshie.harvest.core.helpers.EntityHelper;
import joshie.harvest.core.helpers.NBTHelper;
import joshie.harvest.core.network.PacketHandler;
import joshie.harvest.core.util.interfaces.ISyncMaster;
import joshie.harvest.gathering.GatheringData;
import joshie.harvest.knowledge.letter.LetterDataServer;
import joshie.harvest.knowledge.packet.PacketSyncLetters;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.npcs.NPCHelper;
import joshie.harvest.npcs.entity.EntityNPCBuilder;
import joshie.harvest.npcs.entity.EntityNPCHuman;
import joshie.harvest.quests.Quests;
import joshie.harvest.quests.data.QuestDataServer;
import joshie.harvest.quests.packet.PacketSharedSync;
import joshie.harvest.town.packet.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.Map.Entry;

public class TownDataServer extends TownData<QuestDataServer, LetterDataServer> implements ISyncMaster {
    public final GatheringData gathering = new GatheringData();
    private Map<ResourceLocation, BlockPos> deadVillagers = new HashMap<>();
    private final QuestDataServer quests = new QuestDataServer(this);
    private final LetterDataServer letters = new LetterDataServer(this);
    private Festival targetFestival = Festival.NONE;
    private int targetFestivalDays;
    private int dimension;

    public TownDataServer() {}
    public TownDataServer(int dimension, BlockPos townCentre, CalendarDate date) {
        this.dimension = dimension;
        this.townCentre = townCentre;
        this.uuid = UUID.randomUUID();
        this.birthday = date.copy();
    }

    @Override
    public QuestDataServer getQuests() {
        return quests;
    }

    @Override
    public LetterDataServer getLetters() {
        return letters;
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.TOWN;
    }

    @Override
    public void sync(@Nullable EntityPlayer player, @Nonnull PacketSharedSync packet) {
        if (player != null) PacketHandler.sendToClient(packet.setUUID(getID()), player);
        else PacketHandler.sendToDimension(dimension, packet.setUUID(getID()));
    }

    private boolean isDead(NPC npc) {
        return deadVillagers.keySet().contains(npc.getRegistryName());
    }

    public void createOrUpdateBuilder(WorldServer world, BlockPos pos) {
        Entity builder = world.getEntityFromUuid(getID());
        if (!(builder instanceof EntityNPCBuilder) && !isDead(HFNPCs.CARPENTER)) {
            EntityNPCBuilder creator = new EntityNPCBuilder(world);
            creator.setPositionAndUpdate(pos.getX(), pos.getY() + 1.5D, pos.getZ());
            creator.setUniqueId(getID()); //Marking the builder as having the same data
            world.spawnEntityInWorld(creator); //Towns owner now spawned
        }
    }

    public void markNPCDead(ResourceLocation name, BlockPos location) {
        deadVillagers.put(name, location);
    }

    public void syncBuildings(World world) {
        PacketHandler.sendToDimension(world.provider.getDimension(), new PacketSyncBuilding(getID(), buildingQueue));
    }

    public boolean setBuilding(World world, Building building, BlockPos pos, Rotation rotation) {
        BuildingStage stage = new BuildingStage(building, pos, rotation);
        if (!buildingQueue.contains(stage)) {
            buildingQueue.addLast(stage);

            syncBuildings(world);
            if (building == HFBuildings.CARPENTER) {
                townCentre = pos; //Set the town centre to the carpenters position
                PacketHandler.sendToDimension(world.provider.getDimension(), new PacketSyncCentre(getID(), townCentre));
            }

            HFTrackers.markTownsDirty();
            return true;
        }

        return false;
    }

    public void finishBuilding() {
        buildingQueue.removeFirst(); //Remove the first building
        HFTrackers.markTownsDirty();
    }

    public void addBuilding(World world, Building building, Rotation rotation, BlockPos pos) {
        TownBuilding newBuilding = new TownBuilding(building, rotation, pos);
        buildings.put(Building.REGISTRY.getKey(building), newBuilding);
        PacketHandler.sendToDimension(world.provider.getDimension(), new PacketNewBuilding(uuid, newBuilding));
        building.onBuilt(world, pos, rotation);
        HFTrackers.markTownsDirty();
    }

    private boolean isRepeatable(World world, Quest quest) {
        if (!quest.isRepeatable()) return false;
        if (quest.getDaysBetween() == 0) return true;
        else {
            CalendarDate date = getQuests().getLastCompletionOfQuest(quest);
            return date == null || CalendarHelper.getDays(date, HFApi.calendar.getDate(world)) >= quest.getDaysBetween();
        }
    }

    private void generateNewDailyQuest(World world) {
        List<Quest> quests = new ArrayList<>();
        for (Quest quest: Quest.REGISTRY) {
            if (isRepeatable(world, quest) || !getQuests().getFinished().contains(quest)) {
                if (!getQuests().getCurrent().contains(quest)) {
                    if (quest.canStartDailyQuest(world, townCentre)) {
                        quests.add(quest);
                    }
                }
            }
        }

        if (quests.size() > 0) {
            dailyQuest = quests.get(world.rand.nextInt(quests.size()));
        } else dailyQuest = null;

        PacketHandler.sendToDimension(world.provider.getDimension(), new PacketDailyQuest(uuid, dailyQuest));
    }

    public void startFestival(Festival festival) {
        this.targetFestival = festival;
        this.targetFestivalDays = festival.getFestivalLength();
    }

    public void newDay(World world, Cache<BlockPos, Boolean> isFar, CalendarDate yesterday, CalendarDate today) {
        if (world.isBlockLoaded(getTownCentre())) {
            shops.newDay(world, uuid);
            gathering.newDay(world, townCentre, buildings.values(), isFar);
            generateNewDailyQuest(world);
            for (Entry<ResourceLocation, BlockPos> entry: deadVillagers.entrySet()) {
                NPC npc = NPC.REGISTRY.getValue(entry.getKey());
                if (npc != HFNPCs.GODDESS) {
                    EntityNPCHuman entity = NPCHelper.getEntityForNPC(world, npc);
                    entity.setPosition(townCentre.getX(), townCentre.getY(), townCentre.getZ());
                    entity.resetSpawnHome();
                    BlockPos home = NPCHelper.getHomeForEntity(entity);
                    BlockPos pos = home != null ? home : entry.getValue();
                    int attempts = 0;
                    while (!EntityHelper.isSpawnable(world, pos) && attempts < 64) {
                        pos = pos.add(world.rand.nextInt(16) - 8, world.rand.nextInt(8), world.rand.nextInt(16) - 8);
                        attempts++;
                    }

                    entity.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
                    if (npc == HFNPCs.CARPENTER) entity.setUniqueId(getID()); //Keep the Unique ID the same
                    world.spawnEntityInWorld(entity);
                }
            }

            //Festival updates
            //Set the festival for today
            Festival previousFestival = festival;
            if (targetFestival != Festival.NONE) {
                festival = targetFestival;
                festivalDays = targetFestivalDays;
                targetFestival = Festival.NONE;
                targetFestivalDays = 0;
            }

            if (previousFestival == festival) { //If there is a festival active
                festivalDays--;  //Decrease the amount of days of the festival left
                if (festivalDays <= 0 && (festival != HFFestivals.NEW_YEARS_EVE || (today.getDay() != 0 && festival == HFFestivals.NEW_YEARS_EVE))) { //If we have no days left then v
                    festival = Festival.NONE; //Cancel the festival
                }
            }

            //If the festival is not the same, call for a change of festivals
            if (festival != previousFestival) {
                PacketHandler.sendToDimension(world.provider.getDimension(), new PacketSyncFestival(uuid, festival, festivalDays));
                for (TownBuilding building : buildings.values()) {
                    building.building.onFestivalChanged(world, building.pos, building.rotation, previousFestival, festival);
                }
            }

            letters.newDay(today);
            boolean changed = false;
            Festival newFestival = CalendarAPI.INSTANCE.getFestivalFromDate(today);
            if (!newFestival.equals(Festival.NONE)) {
                Festival oldFestival = CalendarAPI.INSTANCE.getFestivalFromDate(yesterday);
                if (!newFestival.equals(oldFestival)) {
                    //Remove the old letter
                    if (oldFestival.getLetter() != null) {
                        changed = true;
                        letters.remove(oldFestival.getLetter());
                    }

                    //Add the new letter
                    if (newFestival.getLetter() != null) {
                        changed = true;
                        letters.add(newFestival.getLetter());
                    }
                }
            }

            if (changed) sync(null, new PacketSyncLetters(letters.getLetters()));
            deadVillagers = new HashMap<>(); //Reset the dead villagers
        }
    }

    public Rotation getFacingFor(ResourceLocation resource) {
        TownBuilding building = buildings.get(resource);
        if (building == null) return null;
        return building.getFacing();
    }

    //Called to sync the data about this town to the client
    public void writePacketNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        letters.readFromNBT(nbt);
        quests.readFromNBT(nbt);
        gathering.readFromNBT(nbt);
        dimension = nbt.getInteger("Dimension");
        //TODO: Remove in 0.7+
        if (nbt.hasKey("DeadVillagers")) {
            Set<ResourceLocation> dead = NBTHelper.readResourceSet(nbt, "DeadVillagers");
            dead.stream().forEach((r) -> deadVillagers.put(r, townCentre));
        } else if (nbt.hasKey("DeadNPCs")) {
            NBTTagList list = nbt.getTagList("DeadNPCs", 10);
            for (int i = 0; i < list.tagCount(); i++) {
                NBTTagCompound tag = list.getCompoundTagAt(i);
                ResourceLocation resource = new ResourceLocation(tag.getString("Resource"));
                BlockPos pos = BlockPos.fromLong(tag.getLong("Position"));
                deadVillagers.put(resource, pos);
            }
        }

        //TODO: Remove in 0.7+
        if (!nbt.hasKey("CurrentQuests") && !nbt.hasKey("FinishedQuests")) {
            if (buildings.containsKey(HFBuildings.CAFE.getRegistryName())) quests.getFinished().add(Quests.BUILDING_CAFE);
            if (buildings.containsKey(HFBuildings.FISHING_HUT.getRegistryName())) quests.getFinished().add(Quests.BUILDING_FISHER);
            if (buildings.containsKey(HFBuildings.BLACKSMITH.getRegistryName())) quests.getFinished().add(Quests.BUILDING_BLACKSMITH);
            if (buildings.containsKey(HFBuildings.CARPENTER.getRegistryName())) quests.getFinished().add(Quests.BUILDING_CARPENTER);
            if (buildings.containsKey(HFBuildings.SUPERMARKET.getRegistryName())) quests.getFinished().add(Quests.BUILDING_SUPERMARKET);
        }

        //Target festival
        if (nbt.hasKey("FestivalTarget")) {
            targetFestival = Festival.REGISTRY.get(new ResourceLocation(nbt.getString("FestivalTarget")));
            targetFestivalDays = nbt.getInteger("FestivalTargetDays");
        } else festival = Festival.NONE;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        letters.writeToNBT(nbt);
        quests.writeToNBT(nbt);
        gathering.writeToNBT(nbt);
        nbt.setInteger("Dimension", dimension);
        NBTTagList list = new NBTTagList();
        for (Entry<ResourceLocation, BlockPos> entry: deadVillagers.entrySet()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("Resource", entry.getKey().toString());
            tag.setLong("Position", entry.getValue().toLong());
            list.appendTag(tag);
        }

        nbt.setTag("DeadNPCs", list);

        //Target festival
        if (targetFestival != null) {
            nbt.setString("FestivalTarget", targetFestival.getResource().toString());
            nbt.setInteger("FestivalTargetDays", targetFestivalDays);
        }

        return nbt;
    }
}
