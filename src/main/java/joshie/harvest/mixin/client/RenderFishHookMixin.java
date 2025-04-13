package joshie.harvest.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import joshie.harvest.fishing.item.ItemFishingRod;
import net.minecraft.client.renderer.entity.RenderFish;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mixin(RenderFish.class)
public class RenderFishHookMixin {
	@WrapOperation(
			method = "doRender(Lnet/minecraft/entity/projectile/EntityFishHook;DDDFF)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
	private Item wrapFishingRod(ItemStack itemStack, Operation<Item> original) {
		Item item = original.call(itemStack);
		if (item instanceof ItemFishingRod) {
			return Items.FISHING_ROD;
		}
		return item;
	}
}
