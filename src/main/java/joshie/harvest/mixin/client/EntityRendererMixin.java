package joshie.harvest.mixin.client;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import joshie.harvest.api.HFApi;
import joshie.harvest.api.calendar.Weather;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {
	@Final
	@Shadow
	private Minecraft mc;

	@Inject(method = "addRainParticles", at = @At("HEAD"), cancellable = true)
	private void addRainParticles(CallbackInfo ci) {
		Weather weather = HFApi.calendar.getWeather(mc.world);
		if (!weather.isRain()) {
			ci.cancel();
		}
	}
}
