package joshie.harvest.buildings.render;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import joshie.harvest.api.buildings.Building;
import joshie.harvest.buildings.HFBuildings;
import joshie.harvest.buildings.render.BuildingItemRenderer.BuildingTile;
import joshie.harvest.core.base.render.FakeEntityRenderer.EntityItemRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rotation;
import net.minecraftforge.client.resource.IResourceType;
import net.minecraftforge.client.resource.ISelectiveResourceReloadListener;
import net.minecraftforge.client.resource.VanillaResourceType;

import javax.annotation.Nullable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class BuildingItemRenderer extends TileEntitySpecialRenderer<BuildingTile> implements ISelectiveResourceReloadListener {

	private final BuildingVertexUploader vertexUploader = new BuildingVertexUploader();
    private Cache<Building, BuildingRenderer> cache = CacheBuilder.newBuilder().expireAfterAccess(2, TimeUnit.MINUTES).build();
    private BuildingRenderer getRenderer(Building building) throws ExecutionException {
        return cache.get(building, () -> building == HFBuildings.FESTIVAL_GROUNDS ?
                  new BuildingRenderer(new BuildingAccess(building, Rotation.NONE), new BuildingKey(Rotation.NONE, building)):
                  new BuildingRendererNoFloor(new BuildingAccess(building, Rotation.NONE), new BuildingKey(Rotation.NONE, building)));
    }

    public BuildingItemRenderer() {
    	((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(this);
	}

    @Override
    public void render(@Nullable BuildingTile fake, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        try {
            if (fake != null) {
                Building building = HFBuildings.TOWNHALL;
                RenderHelper.disableStandardItemLighting();
                GlStateManager.color(1F, 1F, 1F);
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.8F, 0.6F, 0.5F);
                float scale = 0.068F;
                GlStateManager.scale(scale, scale, scale);
                GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(180F, 1.0F, 0.0F, 0.0F);
                GlStateManager.pushMatrix();
                GlStateManager.disableCull();
                GlStateManager.enableRescaleNormal();
                GlStateManager.scale(-1.0F, -1.0F, 1.0F);
                GlStateManager.translate(0.0F, -1.501F, 0.0F);
                getRenderer(building).draw(vertexUploader);
                GlStateManager.disableRescaleNormal();
                GlStateManager.enableCull();
                GlStateManager.popMatrix();
                GlStateManager.popMatrix();
            }
        } catch (ExecutionException ex) { /**/}
    }

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
		if (resourcePredicate.test(VanillaResourceType.MODELS) || resourcePredicate.test(VanillaResourceType.TEXTURES)) {
			cache.invalidateAll();
		}
	}

    public static class BuildingTile extends EntityItemRenderer {
        public static final BuildingTile INSTANCE = new BuildingTile();
    }
    
    public static class TEISR extends TileEntityItemStackRenderer {
        private final BuildingItemRenderer renderer;
        
        public TEISR(BuildingItemRenderer renderer) {
            this.renderer = renderer;
        }

        @Override
        public void renderByItem(ItemStack itemStackIn, float partialTicks) {
        	BuildingTile.INSTANCE.setStack(itemStackIn);
        	renderer.render(BuildingTile.INSTANCE, 0, 0, 0, partialTicks, 0, 0);
        }

	}
}
