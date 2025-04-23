package joshie.harvest.town.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.google.common.cache.Cache;
import com.google.common.collect.ImmutableSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
import joshie.harvest.core.network.PacketHandler;
import joshie.harvest.core.util.interfaces.ISyncMaster;
import joshie.harvest.gathering.GatheringData;
import joshie.harvest.knowledge.letter.LetterDataServer;
import joshie.harvest.knowledge.packet.PacketSyncLetters;
import joshie.harvest.mining.HFMining;
import joshie.harvest.mining.gen.MineManager;
import joshie.harvest.mining.gen.MiningProvider;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.npcs.NPCHelper;
import joshie.harvest.npcs.entity.EntityNPC;
import joshie.harvest.npcs.entity.EntityNPCBuilder;
import joshie.harvest.npcs.entity.EntityNPCMiner;
import joshie.harvest.npcs.item.ItemNPCSpawner;
import joshie.harvest.quests.data.QuestDataServer;
import joshie.harvest.quests.packet.PacketSharedSync;
import joshie.harvest.town.packet.PacketDailyQuest;
import joshie.harvest.town.packet.PacketNewBuilding;
import joshie.harvest.town.packet.PacketRemoveBuilding;
import joshie.harvest.town.packet.PacketSyncBuilding;
import joshie.harvest.town.packet.PacketSyncCentre;
import joshie.harvest.town.packet.PacketSyncFestival;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class TownDataServer extends TownData<QuestDataServer, LetterDataServer> implements ISyncMaster {
	public final GatheringData gathering = new GatheringData();
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

	public void removeBuilding(TownBuilding theBuilding) {
		buildings.remove(theBuilding.building.getResource());
		for (NPC inhabitant : theBuilding.building.getInhabitants()) {
			inhabitants.remove(inhabitant);
		}

		HFTrackers.markTownsDirty();
		PacketHandler.sendToEveryone(new PacketRemoveBuilding(getID(), theBuilding.building));
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
		if (player != null) {
			PacketHandler.sendToClient(packet.setUUID(getID()), player);
		} else {
			PacketHandler.sendToEveryone(packet.setUUID(getID()));
		}
	}

	private boolean isDead(WorldServer world, NPC npc) {
		if (!hasNPC(npc)) {
			return false;
		}
		UUID uuid = inhabitants.get(npc);
		if (uuid == null) {
			return true;
		} else {
			Entity entity = world.getEntityFromUuid(uuid);
			return entity == null || entity.isDead;
		}
	}

	public void createOrUpdateBuilder(WorldServer world, BlockPos pos) {
		Entity builder = NPCHelper.getNPCIfExists(world, pos, HFNPCs.CARPENTER);
		if (!(builder instanceof EntityNPCBuilder) && !isDead(world, HFNPCs.CARPENTER)) {
			EntityNPCBuilder creator = new EntityNPCBuilder(world);
			creator.setPositionAndUpdate(pos.getX(), pos.getY() + 1.5D, pos.getZ());
			world.spawnEntity(creator); //Towns owner now spawned
		}
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
	}

	public void addBuilding(World world, Building building, Rotation rotation, BlockPos pos) {
		TownBuilding newBuilding = new TownBuilding(building, rotation, pos);
		buildings.put(building.getResource(), newBuilding);
		for (NPC inhabitant : building.getInhabitants()) { //Add all the inhabitants
			inhabitants.computeIfAbsent(inhabitant, $ -> null);
		}
		PacketHandler.sendToEveryone(new PacketNewBuilding(uuid, newBuilding));
		building.onBuilt(world, pos, rotation);
		HFTrackers.markTownsDirty();
	}

	private boolean isRepeatable(World world, Quest quest) {
		if (!quest.isRepeatable()) {
			return false;
		}
		if (quest.getDaysBetween() == 0) {
			return true;
		} else {
			CalendarDate date = getQuests().getLastCompletionOfQuest(quest);
			return date == null || CalendarHelper.getDays(date, HFApi.calendar.getDate(world)) >= quest.getDaysBetween();
		}
	}

	private void generateNewDailyQuest(World world) {
		List<Quest> quests = new ArrayList<>();
		for (Quest quest : Quest.REGISTRY) {
			if (isRepeatable(world, quest) || !getQuests().getFinished().contains(quest)) {
				if (!getQuests().getCurrent().contains(quest)) {
					if (quest.canStartDailyQuest(this, world, townCentre)) {
						quests.add(quest);
					}
				}
			}
		}

		if (!quests.isEmpty()) {
			dailyQuest = quests.get(world.rand.nextInt(quests.size()));
			dailyQuest.onSelectedAsDailyQuest(this, world, townCentre);
		} else {
			dailyQuest = null;
		}

		PacketHandler.sendToDimension(world.provider.getDimension(), new PacketDailyQuest(uuid, dailyQuest));
	}

	public void clearDailyQuest(World world) {
		dailyQuest = null; //Remove the current daily quest as it has been started in the town
		PacketHandler.sendToDimension(world.provider.getDimension(), new PacketDailyQuest(uuid, dailyQuest));
	}

	public void startFestival(Festival festival) {
		this.targetFestival = festival;
		this.targetFestivalDays = festival.getFestivalLength();
	}

	public void newDay(World world, Cache<BlockPos, Boolean> isFar, CalendarDate yesterday, CalendarDate today) {
		if (!world.isBlockLoaded(getTownCentre())) {
			return;
		}
		shops.newDay(world, uuid);
		//TODO: Disable this again
		gathering.newDay(world, townCentre, buildings.values(), isFar);
		generateNewDailyQuest(world);
		WorldServer worldServer = (WorldServer) world;
		if (isBuilding(null) && !hasNPC(HFNPCs.CARPENTER)) {
			inhabitants.put(HFNPCs.CARPENTER, null);
		}
		Set<Map.Entry<NPC, UUID>> entries = ImmutableSet.copyOf(inhabitants.entrySet());
		for (Map.Entry<NPC, UUID> entry : entries) {
			if (entry.getValue() != null && worldServer.getEntityFromUuid(entry.getValue()) != null) {
				continue;
			}
			NPC npc = entry.getKey();
			if (npc == HFNPCs.MINER) {
				WorldServer server = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(HFMining.MINING_ID);
				EntityNPCMiner entity = NPCHelper.getEntityForNPC(server, HFNPCs.MINER);
				int id = HFTrackers.getTowns(entity.world).getMineIDFromCoordinates(getTownCentre());
				MiningProvider provider = ((MiningProvider) server.provider);
				BlockPos pos = MineManager.modifyNPCPosition(server, provider.getSpawnCoordinateForMine(id, 1), entity);
				entity.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
				entity.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Blocks.TORCH));
				entity.setHeldItem(EnumHand.OFF_HAND, new ItemStack(Items.IRON_PICKAXE));
				server.spawnEntity(entity);
			} else if (npc == HFNPCs.GODDESS) {
				inhabitants.remove(npc);
			} else {
				Entity theEntity = NPCHelper.getNPCIfExists((WorldServer) world, townCentre, npc);
				if (theEntity != null && !theEntity.isDead) {
					continue;
				}

				EntityNPC<?> entity = NPCHelper.getEntityForNPC(world, npc);
				entity.setPosition(townCentre.getX(), townCentre.getY(), townCentre.getZ());
				BlockPos home = NPCHelper.getHomeForEntity(entity);
				BlockPos pos = home != null ? home : townCentre;
				int attempts = 0;
				if (pos == null) {
					pos = new BlockPos(0, 70, 0);
				}

				while (!EntityHelper.isSpawnable(world, pos) && attempts < 64) {
					pos = pos.add(world.rand.nextInt(16) - 8, world.rand.nextInt(8), world.rand.nextInt(16) - 8);
					attempts++;
				}
				ItemNPCSpawner.spawnNPC(world, pos, npc);
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
			if (festivalDays <= 0 &&
					(festival != HFFestivals.NEW_YEARS_EVE || (today.getDay() != 1 && festival == HFFestivals.NEW_YEARS_EVE)) ||
					quests.getFinished().contains(festival.getQuest())) { //If we have no days left then v
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
		Festival newFestival = CalendarAPI.INSTANCE.getFestivalFromDate(this, today);
		if (!newFestival.equals(Festival.NONE)) {
			Festival oldFestival = CalendarAPI.INSTANCE.getFestivalFromDate(this, yesterday);
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

		if (changed) {
			sync(null, new PacketSyncLetters(letters.getLetters()));
		}
	}

	public Rotation getFacingFor(ResourceLocation resource) {
		TownBuilding building = buildings.get(resource);
		if (building == null) {
			return null;
		}
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

		//Target festival
		if (nbt.hasKey("FestivalTarget")) {
			targetFestival = Festival.REGISTRY.get(new ResourceLocation(nbt.getString("FestivalTarget")));
			targetFestivalDays = nbt.getInteger("FestivalTargetDays");
		} else {
			festival = Festival.NONE;
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		letters.writeToNBT(nbt);
		quests.writeToNBT(nbt);
		gathering.writeToNBT(nbt);
		nbt.setInteger("Dimension", dimension);

		//Target festival
		if (targetFestival != null) {
			nbt.setString("FestivalTarget", targetFestival.getResource().toString());
			nbt.setInteger("FestivalTargetDays", targetFestivalDays);
		}

		return nbt;
	}
}
