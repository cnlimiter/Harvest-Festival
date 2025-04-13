package joshie.harvest.quests.player.trade;

import java.util.Set;

import joshie.harvest.api.core.ITiered;
import joshie.harvest.api.npc.NPCEntity;
import joshie.harvest.api.quests.HFQuest;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.core.helpers.InventoryHelper;
import joshie.harvest.core.helpers.InventoryHelper.SearchType;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.quests.Quests;
import joshie.harvest.quests.base.QuestTrade;
import joshie.harvest.tools.HFTools;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@HFQuest("trade.tools")
public class QuestToolTrader extends QuestTrade {
	@Override
	public boolean canStartQuest(Set<Quest> active, Set<Quest> finished) {
		return finished.contains(Quests.JADE_MEET) && !finished.contains(Quests.JENNI_MEET);
	}

	@Override
	public boolean isNPCUsed(EntityPlayer player, NPCEntity entity) {
		return entity.getNPC() == HFNPCs.FLOWER_GIRL && hasHeldType(player, SearchType.HOE, SearchType.BUCKET, SearchType.SHEARS);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getLocalizedScript(EntityPlayer player, NPCEntity entity) {
		if (hasHeldType(player, SearchType.HOE)) {
			return getLocalized("hoe");
		} else if (hasHeldType(player, SearchType.BUCKET)) {
			return getLocalized("wateringcan");
		} else if (hasHeldType(player, SearchType.SHEARS)) {
			return getLocalized("sickle");
		} else {
			return null;
		}
	}

	@Override
	public void onChatClosed(EntityPlayer player, NPCEntity entity, boolean isSneaking) {
		complete(player);
	}

	@Override
	public void onQuestCompleted(EntityPlayer player) {
		if (takeHeldType(player, SearchType.HOE)) {
			rewardItem(player, HFTools.HOES.get(ITiered.ToolTier.BASIC).getStack());
		} else if (takeHeldType(player, SearchType.BUCKET)) {
			rewardItem(player, HFTools.WATERING_CANS.get(ITiered.ToolTier.BASIC).getStack());
		} else if (takeHeldType(player, SearchType.SHEARS)) {
			rewardItem(player, HFTools.SICKLES.get(ITiered.ToolTier.BASIC).getStack());
		}
	}

	private boolean hasHeldType(EntityPlayer player, SearchType... searches) {
		for (SearchType search : searches) {
			if (InventoryHelper.getHandItemIsIn(player, InventoryHelper.SPECIAL, search) != null) {
				return true;
			}
		}

		return false;
	}

	private boolean takeHeldType(EntityPlayer player, SearchType... searches) {
		for (SearchType search : searches) {
			if (InventoryHelper.takeItemsIfHeld(player, InventoryHelper.SPECIAL, search) != null) {
				return true;
			}
		}

		return false;
	}
}
