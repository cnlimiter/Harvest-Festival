package joshie.harvest.cooking.render;

import joshie.harvest.HarvestFestival;
import joshie.harvest.api.cooking.Ingredient;
import joshie.harvest.core.util.annotations.HFEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@HFEvents(Side.CLIENT)
public class IngredientMappingEvent {
	public static final ResourceLocation OIL = HarvestFestival.id("fluids/oil_cooking");
	public static final ResourceLocation MILK = HarvestFestival.id("fluids/milk");
	public static final ResourceLocation WINE = HarvestFestival.id("fluids/wine");

	@SubscribeEvent
	public void onMapping(TextureStitchEvent.Pre event) {
		Ingredient.INGREDIENTS.values().stream().filter(component -> component.getFluid() != null).forEach(component -> event.getMap()
				.registerSprite(component.getFluid()));
	}
}
