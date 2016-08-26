package joshie.harvest.player.quests;

import joshie.harvest.npc.entity.EntityNPC;
import joshie.harvest.api.quests.Quest;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashSet;

public abstract class QuestData {
    protected HashSet<Quest> current = new HashSet<>(100);

    public HashSet<Quest> getCurrent() {
        return current;
    }

    public Quest getAQuest(Quest quest) {
        if (current != null) {
            //Create the quest if it doesn't exist
            if (!quest.isRealQuest() && !current.contains(quest)) {
                startQuest(quest);
            }

            //Search the quests for the real version
            for (Quest q : current) {
                if (q.equals(quest)) {
                    return q;
                }
            }
        }

        return null;

    }

    public String getScript(EntityPlayer player, EntityNPC npc) {
        return "";
    }

    public abstract void markCompleted(Quest quest, boolean sendPacket);
    public void setAvailable(Quest quest) {}
    public void addAsCurrent(Quest quest) {}
    public abstract void setStage(Quest theQuest, int stage);
    public boolean startQuest(Quest quest) { return false; }
}