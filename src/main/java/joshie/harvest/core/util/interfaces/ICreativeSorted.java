package joshie.harvest.core.util.interfaces;

import javax.annotation.Nonnull;
import joshie.harvest.core.lib.CreativeSort;
import net.minecraft.item.ItemStack;

public interface ICreativeSorted {
	default int getSortValue(@Nonnull ItemStack stack) {
		return CreativeSort.NONE;
	}
}