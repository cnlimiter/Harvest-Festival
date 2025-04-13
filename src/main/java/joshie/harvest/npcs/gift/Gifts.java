package joshie.harvest.npcs.gift;

import static joshie.harvest.api.npc.gift.GiftCategory.ART;
import static joshie.harvest.api.npc.gift.GiftCategory.BUILDING;
import static joshie.harvest.api.npc.gift.GiftCategory.COOKING;
import static joshie.harvest.api.npc.gift.GiftCategory.EGG;
import static joshie.harvest.api.npc.gift.GiftCategory.FISH;
import static joshie.harvest.api.npc.gift.GiftCategory.FLOWER;
import static joshie.harvest.api.npc.gift.GiftCategory.FRUIT;
import static joshie.harvest.api.npc.gift.GiftCategory.GEM;
import static joshie.harvest.api.npc.gift.GiftCategory.HERB;
import static joshie.harvest.api.npc.gift.GiftCategory.JUNK;
import static joshie.harvest.api.npc.gift.GiftCategory.KNOWLEDGE;
import static joshie.harvest.api.npc.gift.GiftCategory.MAGIC;
import static joshie.harvest.api.npc.gift.GiftCategory.MEAT;
import static joshie.harvest.api.npc.gift.GiftCategory.MILK;
import static joshie.harvest.api.npc.gift.GiftCategory.MINERAL;
import static joshie.harvest.api.npc.gift.GiftCategory.MONEY;
import static joshie.harvest.api.npc.gift.GiftCategory.MONSTER;
import static joshie.harvest.api.npc.gift.GiftCategory.MUSHROOM;
import static joshie.harvest.api.npc.gift.GiftCategory.PLANT;
import static joshie.harvest.api.npc.gift.GiftCategory.VEGETABLE;
import static joshie.harvest.api.npc.gift.GiftCategory.WOOL;

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
		categoryRegistry.put(GEM, Quality.GOOD);
		categoryRegistry.put(FLOWER, Quality.GOOD);
		categoryRegistry.put(COOKING, Quality.GOOD);
		categoryRegistry.put(MONEY, Quality.GOOD);
		categoryRegistry.put(MEAT, Quality.DECENT);
		categoryRegistry.put(VEGETABLE, Quality.DECENT);
		categoryRegistry.put(FRUIT, Quality.DECENT);
		categoryRegistry.put(HERB, Quality.DECENT);
		categoryRegistry.put(MILK, Quality.DECENT);
		categoryRegistry.put(EGG, Quality.DECENT);
		categoryRegistry.put(WOOL, Quality.DECENT);
		categoryRegistry.put(ART, Quality.DECENT);
		categoryRegistry.put(KNOWLEDGE, Quality.DECENT);
		categoryRegistry.put(MUSHROOM, Quality.DECENT);
		categoryRegistry.put(MAGIC, Quality.DECENT);
		categoryRegistry.put(FISH, Quality.DISLIKE);
		categoryRegistry.put(PLANT, Quality.DISLIKE);
		categoryRegistry.put(MINERAL, Quality.BAD);
		categoryRegistry.put(BUILDING, Quality.BAD);
		categoryRegistry.put(MONSTER, Quality.BAD);
		categoryRegistry.put(JUNK, Quality.BAD);
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