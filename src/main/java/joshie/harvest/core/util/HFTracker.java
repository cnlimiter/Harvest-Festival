package joshie.harvest.core.util;

import java.lang.ref.WeakReference;

import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public abstract class HFTracker {
	private WeakReference<World> world;
	private int id;

	protected int getDimension() {
		return id;
	}

	protected World getWorld() {
		if (world == null || world.get() == null) {
			world = new WeakReference<>(DimensionManager.getWorld(id));
		}

		return world.get();
	}

	public void setWorld(World world) {
		this.world = new WeakReference<>(world);
		this.id = world.provider.getDimension();
	}
}
