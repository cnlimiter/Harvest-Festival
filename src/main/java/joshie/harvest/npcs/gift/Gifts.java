package joshie.harvest.npcs.gift;

import java.util.EnumMap;

import javax.annotation.Nonnull;
import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.api.npc.gift.IGiftHandler;
import joshie.harvest.core.util.holders.HolderRegistry;
import joshie.harvest.npcs.NPCHelper;
import net.minecraft.item.ItemStack;

public class Gifts implements IGiftHandler {
	final HolderRegistry<Quality> stackRegistry = new HolderRegistry<>();
	final EnumMap<GiftCategory, Quality> categoryRegistry = new EnumMap<>(GiftCategory.class);

	public Gifts() {
		categoryRegistry.put(GiftCategory.GEM, Quality.GOOD);
		categoryRegistry.put(GiftCategory.FLOWER, Quality.GOOD);
		categoryRegistry.put(GiftCategory.COOKING, Quality.GOOD);
		categoryRegistry.put(GiftCategory.MONEY, Quality.GOOD);
		categoryRegistry.put(GiftCategory.MEAT, Quality.DECENT);
		categoryRegistry.put(GiftCategory.VEGETABLE, Quality.DECENT);
		categoryRegistry.put(GiftCategory.FRUIT, Quality.DECENT);
		categoryRegistry.put(GiftCategory.HERB, Quality.DECENT);
		categoryRegistry.put(GiftCategory.MILK, Quality.DECENT);
		categoryRegistry.put(GiftCategory.EGG, Quality.DECENT);
		categoryRegistry.put(GiftCategory.WOOL, Quality.DECENT);
		categoryRegistry.put(GiftCategory.ART, Quality.DECENT);
		categoryRegistry.put(GiftCategory.KNOWLEDGE, Quality.DECENT);
		categoryRegistry.put(GiftCategory.MUSHROOM, Quality.DECENT);
		categoryRegistry.put(GiftCategory.MAGIC, Quality.DECENT);
		categoryRegistry.put(GiftCategory.FISH, Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.PLANT, Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.MINERAL, Quality.BAD);
		categoryRegistry.put(GiftCategory.BUILDING, Quality.BAD);
		categoryRegistry.put(GiftCategory.MONSTER, Quality.BAD);
		categoryRegistry.put(GiftCategory.JUNK, Quality.BAD);
	}

	@Override
	public Quality getQuality(@Nonnull ItemStack stack) {
		Quality itemQuality = stackRegistry.getValueOf(stack);
		if (itemQuality != null) {
			return itemQuality;
		}
		GiftCategory category = NPCHelper.INSTANCE.getGifts().getRegistry().getValueOf(stack);
		return category == null ? Quality.DECENT : categoryRegistry.get(category);
	}
}