package joshie.harvest.api.core;

import java.util.Map;

import net.minecraft.util.ResourceLocation;

public abstract class HFRegistry<T extends HFRegistry<T>> {
	private final ResourceLocation resource;

	@SuppressWarnings("unchecked")
	public HFRegistry(ResourceLocation resource) {
		this.resource = resource;
		getRegistry().put(resource, (T) this);
	}

	public abstract Map<ResourceLocation, T> getRegistry();

	public ResourceLocation getResource() {
		return resource;
	}
}