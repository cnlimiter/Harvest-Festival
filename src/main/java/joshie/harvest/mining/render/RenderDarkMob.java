package joshie.harvest.mining.render;

import javax.annotation.Nonnull;
import joshie.harvest.HarvestFestival;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderDarkMob<T extends EntityLiving> extends RenderLiving<T> {
	protected final ResourceLocation texture;

	public RenderDarkMob(RenderManager manager, ModelBase model, String animal) {
		super(manager, model, 1F);
		texture = HarvestFestival.id("textures/entity/" + animal + ".png");
	}

	@Override
	protected ResourceLocation getEntityTexture(@Nonnull EntityLiving entityLiving) {
		return texture;
	}
}
