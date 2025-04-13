package joshie.harvest.npcs.gift;

import joshie.harvest.api.core.Ore;
import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.item.ItemMeal.Meal;
import net.minecraft.init.Items;

@SuppressWarnings("unused")
public class GiftsCloe extends Gifts {
	public GiftsCloe() {
		stackRegistry.register(Items.BOOK, Quality.AWESOME);
		stackRegistry.register(Items.ENCHANTED_BOOK, Quality.AWESOME);
		stackRegistry.register(Items.WRITABLE_BOOK, Quality.AWESOME);
		stackRegistry.register(Items.WRITTEN_BOOK, Quality.AWESOME);
		categoryRegistry.put(GiftCategory.MONSTER, Quality.GOOD);
		categoryRegistry.put(GiftCategory.KNOWLEDGE, Quality.GOOD);
		stackRegistry.register(Ore.of("cropCarrot"), Quality.GOOD);
		stackRegistry.register(Ore.of("cropPotato"), Quality.GOOD);
		categoryRegistry.put(GiftCategory.HERB, Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.FLOWER, Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.SALAD_HERB), Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.SANDWICH_HERB), Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.SOUP_HERB), Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.RICE_MATSUTAKE), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.RICE_MUSHROOM), Quality.BAD);
		categoryRegistry.put(GiftCategory.MUSHROOM, Quality.BAD);
		stackRegistry.register(Ore.of("cropStrawberry"), Quality.BAD);
		stackRegistry.register(Ore.of("cropSweetPotato"), Quality.BAD);
		stackRegistry.register(Ore.of("cropTomato"), Quality.BAD);
		stackRegistry.register(Ore.of("cropCabbage"), Quality.TERRIBLE);
		stackRegistry.register(Ore.of("cropGreenPepper"), Quality.TERRIBLE);
		stackRegistry.register(Ore.of("cropSpinach"), Quality.TERRIBLE);
	}
}