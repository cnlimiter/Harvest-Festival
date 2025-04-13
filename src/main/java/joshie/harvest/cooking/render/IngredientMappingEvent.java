package joshie.harvest.cooking.render;

import joshie.harvest.api.cooking.Ingredient;
import joshie.harvest.core.lib.HFModInfo;
import joshie.harvest.core.util.annotations.HFEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@HFEvents(Side.CLIENT)
public class IngredientMappingEvent {
	public static final ResourceLocation OIL = new ResourceLocation(HFModInfo.MODID, "fluids/oil_cooking");
	public static final ResourceLocation MILK = new ResourceLocation(HFModInfo.MODID, "fluids/milk");
	public static final ResourceLocation WINE = new ResourceLocation(HFModInfo.MODID, "fluids/wine");

	@SubscribeEvent
	public void onMapping(TextureStitchEvent.Pre event) {
		Ingredient.INGREDIENTS.values().stream().filter(component -> component.getFluid() != null).forEach(component -> event.getMap()
				.registerSprite(component.getFluid()));
	}
}
