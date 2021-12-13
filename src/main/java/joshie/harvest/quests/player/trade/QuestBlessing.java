package joshie.harvest.quests.player.trade;

import static joshie.harvest.api.core.ITiered.ToolTier.CURSED;
import static joshie.harvest.core.helpers.SpawnItemHelper.spawnXP;
import static joshie.harvest.quests.Quests.TOMAS_MEET;

import java.util.Set;

import javax.annotation.Nonnull;

import joshie.harvest.api.HFApi;
import joshie.harvest.api.calendar.CalendarDate;
import joshie.harvest.api.core.ITiered.ToolTier;
import joshie.harvest.api.npc.NPCEntity;
import joshie.harvest.api.quests.HFQuest;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.calendar.CalendarHelper;
import joshie.harvest.core.HFTrackers;
import joshie.harvest.core.base.item.ItemTool;
import joshie.harvest.core.lib.HFSounds;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.quests.Quests;
import joshie.harvest.quests.base.QuestTrade;
import joshie.harvest.tools.HFTools;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@HFQuest("trade.cursed")
public class QuestBlessing extends QuestTrade {
    private static final int TEST = 0;
    private CalendarDate date;
    @Nonnull
    private ItemStack tool = ItemStack.EMPTY;

    @Override
    public boolean canStartQuest(Set<Quest> active, Set<Quest> finished) {
        return finished.contains(TOMAS_MEET);
    }

    @Override
    public boolean isNPCUsed(EntityPlayer player, NPCEntity entity) {
        return entity.getNPC() == HFNPCs.PRIEST && (isHolding(player) || !tool.isEmpty());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getLocalizedScript(EntityPlayer player, NPCEntity entity) {
        if (quest_stage == TEST) {
            long cost = HFApi.quests.hasCompleted(Quests.TOMAS_15K, player) ? 25000 : 50000;
            boolean hasGold = HFTrackers.getPlayerTrackerFromPlayer(player).getStats().getGold() >= cost;
            boolean hasTool = isHolding(player);
            if (hasGold && hasTool) {
                return getLocalized("accept");
            } else if (hasTool) {
                return getLocalized("gold", cost);
            } else
                return player.world.rand.nextDouble() <= 0.05D ? getLocalized("reminder", cost) : null;
        } else {
            CalendarDate today = HFApi.calendar.getDate(player.world);
            if (CalendarHelper.getDays(date, today) >= 3) {
                return getLocalized("done", tool.getDisplayName());
            }

            return getLocalized("wait", 3 - (CalendarHelper.getDays(date, today)));
        }
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onChatClosed(EntityPlayer player, NPCEntity npc, boolean isSneaking) {
        if (quest_stage == TEST) {
            long cost = HFApi.quests.hasCompleted(Quests.TOMAS_15K, player) ? 25000 : 50000;
            boolean hasGold = HFTrackers.getPlayerTrackerFromPlayer(player).getStats().getGold() >= cost;
            boolean hasTool = isHolding(player);
            if (hasGold && hasTool) {
                date = HFApi.calendar.getDate(player.world).copy();
                ItemStack stack = player.getHeldItemMainhand();
                ItemTool item = (ItemTool) stack.getItem();
                tool = new ItemStack(HFTools.TOOLTYPE_MAP.get(item.toolClass).get(ToolTier.BLESSED));
                tool.setTagCompound(stack.getTagCompound().copy());
                rewardGold(player, -cost);
                takeHeldStack(player, 1);
                increaseStage(player);
            }
        } else {
            CalendarDate today = HFApi.calendar.getDate(player.world);
            if (CalendarHelper.getDays(date, today) >= 3) {
                complete(player);
                player.world.playSound(player, player.posX, player.posY, player.posZ, HFSounds.BLESS_TOOL, SoundCategory.NEUTRAL, 0.25F, 1F);
                EntityLiving entity = npc.getAsEntity();
                for (int i = 0; i < 32; i++) {
                    player.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, entity.posX + player.world.rand.nextFloat() + player.world.rand.nextFloat() - 1F, entity.posY + 0.25D + entity.world.rand.nextFloat() + entity.world.rand.nextFloat(), entity.posZ + player.world.rand.nextFloat() + player.world.rand.nextFloat() - 1F, 0, 0, 0);
                }
            }
        }
    }

    @Override
    public void onQuestCompleted(EntityPlayer player) {
        rewardItem(player, tool);
        HFTrackers.getPlayerTrackerFromPlayer(player).getTracking().addAsObtained(tool);
        spawnXP(player.world, (int) player.posX, (int) player.posY, (int) player.posZ, 5);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("Date")) {
            date = CalendarDate.fromNBT(nbt.getCompoundTag("Date"));
            tool = new ItemStack(nbt.getCompoundTag("Item"));
        }
    }

    /** Called to write data about this quest
     * @param nbt the nbt tag to write to
     * @return the nbt tag**/
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        if (date != null) {
            nbt.setTag("Date", date.toNBT());
            nbt.setTag("Item", tool.writeToNBT(new NBTTagCompound()));
        }

        return nbt;
    }

    private boolean isHolding(EntityPlayer player) {
        ItemStack held = player.getHeldItemMainhand();
        if (!held.isEmpty()) {
            if (held.getItem() instanceof ItemTool) {
                ItemTool tool = ((ItemTool) held.getItem());
                ToolTier tier = tool.getTier(held);
                return tier == CURSED;
            }
        }

        return false;
    }
}
