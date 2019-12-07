package joshie.harvest.core.entity;

import static joshie.harvest.core.tile.TileBasket.BASKET_INVENTORY;

import java.util.Iterator;

import javax.annotation.Nonnull;

import joshie.harvest.api.HFApi;
import joshie.harvest.core.HFCore;
import joshie.harvest.core.block.BlockStorage.Storage;
import joshie.harvest.core.handlers.BasketHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class EntityBasket extends Entity {
    public static final DataParameter<ItemStack> ITEM = EntityDataManager.createKey(EntityItem.class, DataSerializers.ITEM_STACK);
    public final ItemStackHandler handler = new ItemStackHandler(BASKET_INVENTORY);

    public EntityBasket(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        getDataManager().register(ITEM, ItemStack.EMPTY);
    }

    public void setAppearanceAndContents(@Nonnull ItemStack stack, ItemStackHandler handler) {
        getDataManager().set(ITEM, stack);
        getDataManager().setDirty(ITEM);
        for (int i = 0; i < handler.getSlots(); i++) {
            this.handler.setStackInSlot(i, handler.getStackInSlot(i));
        }
    }

    @Nonnull
    public ItemStack getEntityItem() {
        return getDataManager().get(ITEM);
    }

    @Override
    public void dismountRidingEntity() {
        super.dismountRidingEntity();
        if (isEntityAlive() && !this.isRiding()) {
            boolean placed = false;
            BlockPos pos = new BlockPos(this).down();
            if (world.isAirBlock(pos)) {
                BasketHandler.setBasket(world, pos, handler);
                placed = true;
            } else {
                int attempts = 0;
                while (!placed && attempts < 512) {
                    BlockPos placing = pos.add(world.rand.nextInt(10) - 5, world.rand.nextInt(3), world.rand.nextInt(10) - 5);
                    if (world.isAirBlock(placing)) {
                        BasketHandler.setBasket(world, placing, handler);
                        placed = true;
                    }
                    attempts++;
                }
            }

            if (placed) {
                setDead();
            } else {
                drop();
            }
        }
    }

    public void drop() {
        setDead();
        if (world.isRemote) {
            return;
        }
        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack stack = handler.getStackInSlot(i);
            InventoryHelper.spawnItemStack(world, posX, posY, posZ, stack);
        }
        InventoryHelper.spawnItemStack(world, posX, posY, posZ, HFCore.STORAGE.getStackFromEnum(Storage.BASKET));
    }

    @Override
    protected void readEntityFromNBT(@Nonnull NBTTagCompound compound) {
        handler.deserializeNBT(compound.getCompoundTag("inventory"));
    }

    @Override
    protected void writeEntityToNBT(@Nonnull NBTTagCompound compound) {
        compound.setTag("inventory", handler.serializeNBT());
    }

    /* Autoshipping some items **/
    private boolean autoship(NonNullList<ItemStack> list) {
        boolean empty = true;
        Iterator<ItemStack> it = list.iterator();
        while (it.hasNext()) {
            ItemStack stack = it.next();
            if (HFApi.shipping.getSellValue(stack) > 0) {
                ItemStack remainder = ItemHandlerHelper.insertItemStacked(handler, stack.copy(), false);
                stack.setCount(remainder.getCount());
                if (!remainder.isEmpty())
                    empty = false;
            } else {
                empty = false;
            }
        }
        return empty;
    }

    public static boolean findBasketAndShip(EntityPlayer player, NonNullList<ItemStack> list) {
        for (Entity entity : player.getPassengers()) {
            if (entity instanceof EntityBasket) {
                EntityBasket basket = (EntityBasket) entity;
                if (list.size() > 0) {
                    basket.getDataManager().set(ITEM, list.get(list.size() - 1).copy());
                    basket.getDataManager().setDirty(ITEM);
                }

                return basket.autoship(list);
            }
        }

        return false;
    }

    public static boolean isWearingBasket(EntityPlayer player) {
        for (Entity entity : player.getPassengers()) {
            if (entity instanceof EntityBasket) {
                return true;
            }
        }

        return false;
    }
}
