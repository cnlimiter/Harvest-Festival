package joshie.harvest.npcs.render;

import joshie.harvest.api.npc.INPCHelper;
import joshie.harvest.api.npc.NPC;
import joshie.harvest.core.base.render.FakeEntityRenderer.EntityItemRenderer;
import joshie.harvest.core.helpers.MCClientHelper;
import joshie.harvest.core.helpers.StackRenderHelper;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.npcs.entity.EntityNPC;
import joshie.harvest.npcs.entity.EntityNPCVillager;
import joshie.harvest.npcs.render.NPCItemRenderer.NPCTile;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static joshie.harvest.core.base.render.FakeEntityRenderer.SHADOW;

public class NPCItemRenderer extends TileEntitySpecialRenderer<NPCTile> {

    private final ModelNPC modelAlex;
    private final ModelNPC modelSteve;
    private EntityNPC fake;

    public NPCItemRenderer() {
        this.modelAlex = new ModelNPC(true);
        this.modelSteve = new ModelNPC(false);
    }

    @Override
    public void render(@Nullable NPCTile fake, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (fake != null) { //DEBUG
            /*GlStateManager.pushMatrix();
            GlStateManager.enableLighting();
            GlStateManager.translate(0.5F, -0.05F, 0.5F);
            GlStateManager.scale(-5F, 5F, 5F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(180F, 1.0F, 0.0F, 0.0F);
            GlStateManager.pushMatrix();
            GlStateManager.disableCull();
            GlStateManager.enableRescaleNormal();
            GlStateManager.scale(-1.0F, -1.0F, 1.0F);
            GlStateManager.translate(0.0F, -1.501F, 0.0F);
            if (renderShadow) bindTexture(SHADOW);
            else bindTexture(fake.npc.getSkin());
            ModelNPC model = fake.npc.isAlexSkin() ? modelAlex : modelSteve;
            model.isChild = fake.npc.getAge() == INPCHelper.Age.CHILD;
            model.render(getNPC(), 0F, 0F, 0F, 0F, 0F, 0.0625F);
            GlStateManager.disableRescaleNormal();
            GlStateManager.enableCull();
            GlStateManager.popMatrix();
            GlStateManager.popMatrix(); */

            GlStateManager.pushMatrix();
            GlStateManager.translate(0.5F, -0.05F, 0.5F);
            GlStateManager.scale(-0.75F, 0.75F, 0.75F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(180F, 1.0F, 0.0F, 0.0F);
            GlStateManager.pushMatrix();
            GlStateManager.disableCull();
            GlStateManager.enableRescaleNormal();
            GlStateManager.scale(-1.0F, -1.0F, 1.0F);
            GlStateManager.translate(0.0F, -1.501F, 0.0F);
            if (StackRenderHelper.renderShadow) {
                bindTexture(SHADOW);
                GlStateManager.disableLighting();
            } else bindTexture(fake.npc.getSkin());
            ModelNPC model = fake.npc.isAlexSkin() ? modelAlex : modelSteve;
            model.isChild = fake.npc.getAge() == INPCHelper.Age.CHILD;
            model.render(getNPC(), 0F, 0F, 0F, 0F, 0F, 0.0625F);
            if (StackRenderHelper.renderShadow) GlStateManager.enableLighting();
            GlStateManager.disableRescaleNormal();
            GlStateManager.enableCull();
            GlStateManager.popMatrix();
            GlStateManager.popMatrix();
        }
    }

    private EntityNPC getNPC() {
        if (fake == null) fake = new EntityNPCVillager(MCClientHelper.getWorld());
        return fake;
    }

    public static class NPCTile extends EntityItemRenderer {
        public static final NPCTile INSTANCE = new NPCTile();
        public NPC npc;

        @Override
        public void setStack(@Nonnull ItemStack stack) {
            this.npc = HFNPCs.SPAWNER_NPC.getObjectFromStack(stack);
        }
    }

    public static class TEISR extends TileEntityItemStackRenderer
    {
        private final NPCTile instance;
        private final NPCItemRenderer renderer;
        
        public TEISR(NPCTile instance, NPCItemRenderer renderer) {
            this.instance = instance;
            this.renderer = renderer;
        }

        @Override
        public void renderByItem(ItemStack itemStackIn, float partialTicks) {
        	instance.setStack(itemStackIn);
        	renderer.render(instance, 0, 0, 0, partialTicks, 0, 0);
        }
    }
}
