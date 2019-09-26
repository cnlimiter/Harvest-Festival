package joshie.harvest.gathering;

//TODO: Re-enable this
/*
@HFEvents
@SuppressWarnings("unused")
public class GatheringDaily {
    @SubscribeEvent
    public void onChunkLoad(ChunkDataEvent.Load event) {
        if (event.getWorld().provider.getDimension() == 0) {
            NBTTagCompound tag = NBTHelper.getLastTickData(event.getData());
            long chunk = ChunkPos.asLong(event.getChunk().x, event.getChunk().z);
            if (tag.hasKey("" + chunk)) {
                int days = CalendarHelper.getElapsedDays(event.getWorld().getWorldTime());
                int lastDay = tag.getInteger("" + chunk);
                if (days - lastDay > 0) {
                    TownDataServer data = TownHelper.getClosestTownToBlockPos(event.getWorld(), new BlockPos(event.getChunk().x * 16, 64, event.getChunk().z * 16), false);
                    if (data != HFTrackers.getTowns(event.getWorld()).getNullTown()) {
                        data.gathering.newDay(event.getWorld(), data.getTownCentre(), data.getBuildings(), CacheBuilder.newBuilder().build());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onChunkSave(ChunkDataEvent.Save event) {
        if (event.getWorld().provider.getDimension() == 0) {
            NBTTagCompound tag = NBTHelper.getLastTickData(event.getData());
            long chunk = ChunkPos.asLong(event.getChunk().x, event.getChunk().z);
            int days = CalendarHelper.getElapsedDays(event.getWorld().getWorldTime());
            tag.setInteger("" + chunk, days);
        }
    }
} */
