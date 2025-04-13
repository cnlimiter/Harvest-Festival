package joshie.harvest.animals.render;

import joshie.harvest.core.lib.HFModInfo;
import joshie.harvest.core.util.annotations.HFEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@HFEvents(Side.CLIENT)
public class AnimalMappingEvent {
	public static final ResourceLocation FODDER = new ResourceLocation(HFModInfo.MODID, "blocks/fodder");

	@SubscribeEvent
	public void onMapping(TextureStitchEvent.Pre event) {
		event.getMap().registerSprite(FODDER);
	}
}
