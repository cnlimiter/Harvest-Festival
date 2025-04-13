package joshie.harvest.core.util.interfaces;

import javax.annotation.Nonnull;
import net.minecraft.item.ItemStack;

public interface ILength {
	double getLengthFromSizeOfFish(@Nonnull ItemStack stack, int size);
}