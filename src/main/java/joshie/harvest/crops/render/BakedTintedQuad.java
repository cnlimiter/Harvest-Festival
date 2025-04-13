package joshie.harvest.crops.render;

import java.util.Arrays;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BakedTintedQuad extends BakedQuad {
	public BakedTintedQuad(BakedQuad quad) {
		super(
				Arrays.copyOf(quad.getVertexData(), quad.getVertexData().length),
				1,
				FaceBakery.getFacingFromVertexData(quad.getVertexData()),
				quad.getSprite(),
				quad.shouldApplyDiffuseLighting(),
				quad.getFormat());
	}
}
