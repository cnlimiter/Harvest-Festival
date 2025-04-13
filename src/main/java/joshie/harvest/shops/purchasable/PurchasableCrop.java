package joshie.harvest.shops.purchasable;

import javax.annotation.Nonnull;
import joshie.harvest.api.crops.Crop;
import net.minecraft.item.ItemStack;

public class PurchasableCrop extends Purchasable {
	private final Crop crop;

	public PurchasableCrop(Crop crop) {
		this.crop = crop;
		this.cost = 20 + crop.getSellValue() * 3;
		this.resource = ((cost >= 0) ? "buy:" : "sell:") + crop.getResource().toString().replace(":", "_");
	}

	@Override
	@Nonnull
	public ItemStack getDisplayStack() {
		if (stack.isEmpty()) {
			stack = crop.getCropStack(1);
		}

		return stack;
	}
}