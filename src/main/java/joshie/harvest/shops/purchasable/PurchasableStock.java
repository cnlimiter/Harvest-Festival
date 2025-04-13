package joshie.harvest.shops.purchasable;

import javax.annotation.Nonnull;
import joshie.harvest.api.HFApi;
import net.minecraft.item.ItemStack;

public class PurchasableStock extends Purchasable {
	public PurchasableStock(@Nonnull ItemStack stack) {
		super((long) -(HFApi.shipping.getSellValue(stack) * 1.25), stack);
	}
}