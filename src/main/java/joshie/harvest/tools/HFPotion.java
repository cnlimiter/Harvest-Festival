package joshie.harvest.tools;

import joshie.harvest.HarvestFestival;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HFPotion extends Potion {
	private static final ResourceLocation TEXTURE = HarvestFestival.id("textures/gui/potions.png");

	public HFPotion(String name, int color, int x, int y) {
		super(true, color);
		this.setPotionName(name);
		this.setIconIndex(x, y);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
		return super.getStatusIconIndex();
	}
}
