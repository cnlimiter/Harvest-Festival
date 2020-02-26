package joshie.harvest.core.render;

import javax.annotation.Nonnull;

import joshie.harvest.core.base.render.TileSpecialRendererItem;
import joshie.harvest.core.tile.TileBasket;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SpecialRendererBasket extends TileSpecialRendererItem<TileBasket> {
    @Override
    public void render(@Nonnull TileBasket tile, double x, double y, double z, float tick, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        ItemStack itemstack = tile.getStack();
        if (!itemstack.isEmpty()) {
            GlStateManager.translate(0.55F, 0.3F, 0.2F);
            GlStateManager.rotate(90, 0F, 0F, 1F);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.rotate(-90, 0F, 0F, 1F);
            int i = Math.min(3, itemstack.getCount());
            for (int j = 0; j < i; j++) {
                GlStateManager.translate(0, 0, 0.3);
                renderItem(itemstack, j, 0F, 0F, 0F);
            }
        }

        GlStateManager.popMatrix();
    }

    @Override
    protected void translateItem(boolean isBlock, float position, float rotation, float offset1, float offset2) {}
}
