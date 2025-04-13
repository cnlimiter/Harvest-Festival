package joshie.harvest.core.tile;

import javax.annotation.Nonnull;
import joshie.harvest.core.base.tile.TileStand;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class TilePlate extends TileStand {
	@Override
	public boolean isItemValid(@Nonnull ItemStack held) {
		return held.getItem() instanceof ItemFood;
	}
}
