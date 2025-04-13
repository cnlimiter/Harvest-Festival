package joshie.harvest.npcs.entity;

import java.util.Objects;
import java.util.UUID;

import io.netty.buffer.ByteBuf;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import joshie.harvest.HarvestFestival;
import joshie.harvest.api.npc.INPCHelper;
import joshie.harvest.api.npc.NPC;
import joshie.harvest.api.npc.NPCEntity;
import joshie.harvest.api.npc.task.TaskElement;
import joshie.harvest.api.town.Town;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.npcs.NPCHelper;
import joshie.harvest.town.TownHelper;
import joshie.harvest.town.data.TownData;
import joshie.harvest.town.data.TownDataServer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public abstract class EntityNPC<E extends EntityNPC> extends EntityAgeable implements IEntityAdditionalSpawnData, NPCEntity {
	public static final UUID NIL_UUID = new UUID(0, 0);
	@Nullable
	protected UUID town;
	protected NPC npc;
	private EntityPlayer talkingTo;
	private Mode mode = Mode.DEFAULT;
	EntityNPC lover;

	public enum Mode {
		DEFAULT, GIFT
	}

	public EntityNPC(World world) {
		this(world, HFNPCs.MAYOR);
	}

	public EntityNPC(World world, NPC npc) {
		super(world);
		this.npc = npc;
		this.enablePersistence();
		setSize(0.6F, (1.6F * npc.getHeight()));
		setPathPriority(PathNodeType.WATER, -1.0F);
	}

	public EntityNPC(E entity) {
		this(entity.world, entity.npc);
		npc = entity.getNPC();
		lover = entity.lover;
	}

	protected abstract E getNewEntity(E entity);

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
	}

	public boolean isBusy() {
		return false;
	}

	public UUID getTownUUID() {
		if (town != null) {
			return town;
		}
		if (world.isRemote) {
			return NIL_UUID;
		}
		Entity entity = NPCHelper.getNPCIfExists((WorldServer) world, new BlockPos(this), npc);
		if (entity != null) {
			if (npc != HFNPCs.GODDESS) {
				setDead();
			}
			return NIL_UUID;
		}
		TownDataServer townInstance = TownHelper.getClosestTownToBlockPos(world, new BlockPos(this), true);
		townInstance.addInhabitant(this);
		return town = townInstance.getID();
	}

	@Override
	public NPC getNPC() {
		return npc;
	}

	public EntityNPC getLover() {
		return lover;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Mode getMode() {
		return mode;
	}

	@Override
	public BlockPos getPos() {
		return new BlockPos(this);
	}

	@Override
	public Town getTown() {
		return TownHelper.getTownByID(world, Objects.requireNonNull(getTownUUID()));
	}

	public TownData<?, ?> getTownData() {
		return (TownData<?, ?>) getTown();
	}

	@Override
	public void setPath(TaskElement... tasks) {}

	@Override
	public EntityAgeable getAsEntity() {
		return this;
	}

	@Override
	@Nonnull
	public String getName() {
		return npc.getLocalizedName();
	}

	public boolean isTalking() {
		return talkingTo != null;
	}

	public void setTalking(EntityPlayer player) {
		talkingTo = player;
	}

	@Nullable
	public EntityPlayer getTalkingTo() {
		return talkingTo;
	}

	@Override
	public boolean isChild() {
		return npc.getAge() == INPCHelper.Age.CHILD;
	}

	@Override
	public boolean canDespawn() {
		return false;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack held = player.getHeldItem(hand);
		boolean flag = held.getItem() == Items.SPAWN_EGG;
		if (!flag && isEntityAlive()) {
			if (!world.isRemote) {
				int guiID = NPCHelper.getGuiIDForNPC(this, world, player);
				player.openGui(HarvestFestival.instance, guiID, world, getEntityId(), -1, -1);
			}

			return true;
		} else {
			return super.processInteract(player, hand);
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		npc = NPC.REGISTRY.get(new ResourceLocation(nbt.getString("NPC")));
		if (nbt.hasKey("Town")) {
			town = UUID.fromString(nbt.getString("Town"));
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		if (npc != null) {
			nbt.setString("NPC", npc.getResource().toString());
		}
		if (town != null) {
			nbt.setString("Town", town.toString());
		}
	}

	@Override
	public void writeSpawnData(ByteBuf buf) {
		if (npc == null) {
			npc = HFNPCs.MAYOR;
		}
		ByteBufUtils.writeUTF8String(buf, npc.getResource().toString());
		ByteBufUtils.writeUTF8String(buf, getTownUUID().toString());
	}

	@Override
	public void readSpawnData(ByteBuf buf) {
		String name = ByteBufUtils.readUTF8String(buf);
		npc = NPC.REGISTRY.get(new ResourceLocation(name));
		town = UUID.fromString(ByteBufUtils.readUTF8String(buf));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Nonnull
	public EntityAgeable createChild(EntityAgeable ageable) {
		return getNewEntity((E) ageable);
	}
}