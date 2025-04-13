package joshie.harvest.crops.handlers.drop;

import java.util.Random;

import javax.annotation.Nonnull;
import joshie.harvest.api.crops.Crop;
import joshie.harvest.api.crops.DropHandler;
import net.minecraft.item.ItemStack;

@SuppressWarnings("unused")
public class DropHandlerGrass extends DropHandler {
	@Override
	@Nonnull
	public ItemStack getDrop(Crop crop, int stage, Random rand) {
		return stage >= crop.getMinimumCut() ? crop.getCropStack(1 + (stage - crop.getMinimumCut())) : ItemStack.EMPTY;
	}
}