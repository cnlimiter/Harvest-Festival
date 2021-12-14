package joshie.harvest.core.handlers;

import joshie.harvest.core.HFCore;
import joshie.harvest.core.util.annotations.HFEvents;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@HFEvents
@SuppressWarnings("unused")
public class MobHandler {
    public static boolean register() {
        return HFCore.MOBS_ONLY_SPAWN_UNDERGROUND_IN_OVERWORLD < 256;
    }

    @SubscribeEvent
    public void onEntitySpawnsGenerated(WorldEvent.PotentialSpawns event) {
        if (event.getType() == EnumCreatureType.MONSTER && event.getWorld().provider.getDimension() == 0 && event.getPos().getY() > HFCore.MOBS_ONLY_SPAWN_UNDERGROUND_IN_OVERWORLD) {
            event.setCanceled(true);
        }
    }
}
