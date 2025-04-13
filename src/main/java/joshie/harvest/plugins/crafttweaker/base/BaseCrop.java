package joshie.harvest.plugins.crafttweaker.base;

import joshie.harvest.api.crops.Crop;
import joshie.harvest.core.lib.HFModInfo;
import net.minecraft.util.ResourceLocation;

public abstract class BaseCrop extends BaseOnce {
	protected final ResourceLocation resource;

	public BaseCrop(String name) {
		if (name.contains(":")) {
			this.resource = new ResourceLocation(name);
		} else {
			this.resource = new ResourceLocation(HFModInfo.MODID, name);
		}
	}

	@Override
	public boolean isApplied() {
		return false;
	}

	@Override
	public void applyOnce() {
		Crop crop = Crop.REGISTRY.get(resource);
		if (crop != null) {
			applyToCrop(crop);
		}
	}

	protected abstract void applyToCrop(Crop crop);
}
