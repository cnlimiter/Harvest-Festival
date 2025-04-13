package joshie.harvest.animals.render;

import joshie.harvest.HarvestFestival;
import joshie.harvest.core.util.annotations.HFEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@HFEvents(Side.CLIENT)
public class AnimalMappingEvent {
	public static final ResourceLocation FODDER = HarvestFestival.id("blocks/fodder");

	@SubscribeEvent
	public void onMapping(TextureStitchEvent.Pre event) {
		event.getMap().registerSprite(FODDER);
	}
}
