package joshie.harvest.animals.item;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import joshie.harvest.animals.item.ItemAnimalTool.Tool;
import joshie.harvest.api.animals.AnimalAction;
import joshie.harvest.api.animals.AnimalStats;
import joshie.harvest.api.animals.AnimalTest;
import joshie.harvest.core.base.item.ItemHFEnum;
import joshie.harvest.core.entity.EntityBasket;
import joshie.harvest.core.helpers.EntityHelper;
import joshie.harvest.core.helpers.SpawnItemHelper;
import joshie.harvest.core.lib.CreativeSort;
import joshie.harvest.core.lib.HFSounds;
import joshie.harvest.core.util.interfaces.ISellable;
import joshie.harvest.tools.ToolHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAnimalTool extends ItemHFEnum<ItemAnimalTool, Tool> {
    private static final double MAX_DAMAGE = 512;

    public enum Tool implements IStringSerializable, ISellable {
        MILKER(true), BRUSH(true), MEDICINE(false), CHICKEN_FEED(false), MIRACLE_POTION(false);

        private final boolean isDamageable;

        Tool(boolean isDamageable) {
            this.isDamageable = isDamageable;
        }

        @Override
        public long getSellValue() {
            return this == CHICKEN_FEED ? 1L : 0L;
        }

        @Override
        public String getName() {
            return name().toLowerCase(Locale.ENGLISH);
        }
    }

    public ItemAnimalTool() {
        super(Tool.class);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean isFull3D() {
        return true;
    }

    @Override
    public int getItemStackLimit(@Nonnull ItemStack stack) {
        return getEnumFromStack(stack).isDamageable ? 1 : 64;
    }

    @Override
    public int getMaxItemUseDuration(@Nonnull ItemStack stack) {
        return getEnumFromStack(stack) == Tool.MILKER ? 32 : 0;
    }

    @Override
    @Nonnull
    public EnumAction getItemUseAction(@Nonnull ItemStack stack) {
        return getEnumFromStack(stack) == Tool.MILKER ? EnumAction.BOW : EnumAction.NONE;
    }

    private final Cache<EntityPlayer, AnimalStats> milkables = CacheBuilder.newBuilder().weakKeys().expireAfterWrite(1, TimeUnit.MINUTES).build();

    @Override
    @Nonnull
    public ItemStack onItemUseFinish(@Nonnull ItemStack held, World world, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer && getEnumFromStack(held) == Tool.MILKER) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            AnimalStats stats = milkables.getIfPresent(player);
            if (stats != null) {
                if (stats.performAction(world, held, AnimalAction.CLAIM_PRODUCT)) {
                    ItemStack product = stats.getType().getProduct(stats);

                    if (!EntityBasket.findBasketAndShip(player, NonNullList.withSize(1, product))) {
                        SpawnItemHelper.addToPlayerInventory(player, product);
                    }

                    int damage = getDamageForDisplay(held) + 1;
                    if (damage >= MAX_DAMAGE) {
                        held.splitStack(1);
                    } else {
                        held.getOrCreateSubCompound("Data").setInteger("Damage", damage);
                    }

                    ToolHelper.consumeHunger(player, 4F);
                }
            }
        }

        return held;
    }

    private boolean milk(EntityPlayer player, EnumHand hand, AnimalStats stats) {
        if (stats.performTest(AnimalTest.CAN_MILK)) {
            milkables.put(player, stats);
            player.setActiveHand(hand);
            return true;
        }

        return false;
    }

    private boolean impregnate(EntityPlayer player, AnimalStats stats, @Nonnull ItemStack stack) {
        if (stats.performAction(player.world, stack, AnimalAction.IMPREGNATE)) {
            stack.splitStack(1);
            return true;
        } else
            return false;
    }

    private boolean heal(EntityPlayer player, AnimalStats stats, @Nonnull ItemStack stack) {
        if (stats.performAction(player.world, stack, AnimalAction.HEAL)) {
            stack.splitStack(1);
            ToolHelper.consumeHunger(player, 5F);
            return true;
        } else
            return false;
    }

    private boolean clean(EntityPlayer player, ItemStack held, EntityLivingBase animal, AnimalStats stats) {
        if (stats.performTest(AnimalTest.CAN_CLEAN)) {
            boolean cleaned = stats.performAction(player.world, held, AnimalAction.CLEAN);
            if (cleaned) {
                if (player.world.isRemote) {
                    for (int j = 0; j < 30D; j++) {
                        double d7 = (animal.posY - 0.5D) + animal.world.rand.nextFloat();
                        double d8 = (animal.posX - 0.5D) + animal.world.rand.nextFloat();
                        double d9 = (animal.posZ - 0.5D) + animal.world.rand.nextFloat();
                        animal.world.spawnParticle(EnumParticleTypes.TOWN_AURA, d8, 1.0D + d7 - 0.125D, d9, 0, 0, 0);
                    }
                }

                player.world.playSound(player, player.posX, player.posY, player.posZ, HFSounds.BRUSH, SoundCategory.PLAYERS, 1.5F, 1F);
                if (stats.performTest(AnimalTest.IS_CLEAN)) {
                    int damage = getDamageForDisplay(held) + 1;
                    if (damage >= MAX_DAMAGE) {
                        held.splitStack(1);
                    } else {
                        held.getOrCreateSubCompound("Data").setInteger("Damage", damage);
                    }
                }

                ToolHelper.consumeHunger(player, 1F);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase living, EnumHand hand) {
        AnimalStats stats = EntityHelper.getStats(living);
        if (stats != null) {
            Tool tool = getEnumFromStack(stack);
            if (tool == Tool.BRUSH) {
                return clean(player, stack, living, stats);
            } else if (tool == Tool.MEDICINE) {
                return heal(player, stats, stack);
            } else if (tool == Tool.MIRACLE_POTION) {
                return impregnate(player, stats, stack);
            } else if (tool == Tool.MILKER) {
                return milk(player, hand, stats);
            }
        }

        return false;
    }

    @Override
    public boolean showDurabilityBar(@Nonnull ItemStack stack) {
        return getDurabilityForDisplay(stack) > 0D;
    }

    @Override
    public double getDurabilityForDisplay(@Nonnull ItemStack stack) {
        return canBeDamaged(stack) ? (getDamageForDisplay(stack) / MAX_DAMAGE) : 0;
    }

    private int getDamageForDisplay(@Nonnull ItemStack stack) {
        return stack.getOrCreateSubCompound("Data").getInteger("Damage");
    }

    private boolean canBeDamaged(@Nonnull ItemStack stack) {
        return getEnumFromStack(stack).isDamageable;
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return (getEnumFromStack(stack) == Tool.MIRACLE_POTION ? TextFormatting.AQUA : "") + super.getItemStackDisplayName(stack);
    }

    @Override
    protected String getPrefix(Tool tool) {
        return "animal_tool";
    }

    @Override
    public int getSortValue(@Nonnull ItemStack stack) {
        return CreativeSort.GENERAL_ITEM;
    }
}
