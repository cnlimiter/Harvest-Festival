package joshie.harvest.npcs.gift;

import static joshie.harvest.api.npc.gift.GiftCategory.EGG;
import static joshie.harvest.api.npc.gift.GiftCategory.FLOWER;
import static joshie.harvest.api.npc.gift.GiftCategory.FRUIT;
import static joshie.harvest.api.npc.gift.GiftCategory.GEM;
import static joshie.harvest.api.npc.gift.GiftCategory.HERB;
import static joshie.harvest.api.npc.gift.GiftCategory.MEAT;
import static joshie.harvest.api.npc.gift.GiftCategory.MILK;
import static joshie.harvest.api.npc.gift.GiftCategory.MONEY;
import static joshie.harvest.api.npc.gift.GiftCategory.MUSHROOM;
import static joshie.harvest.api.npc.gift.GiftCategory.VEGETABLE;
import static joshie.harvest.api.npc.gift.GiftCategory.WOOL;

import joshie.harvest.api.core.Ore;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.item.ItemIngredients.Ingredient;
import joshie.harvest.cooking.item.ItemMeal.Meal;
import net.minecraft.init.Items;

@SuppressWarnings("unused")
public class GiftsLiara extends Gifts {
	public GiftsLiara() {
		stackRegistry.register(HFCooking.INGREDIENTS.getStackFromEnum(Ingredient.CHOCOLATE), Quality.AWESOME);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.CAKE_CHOCOLATE), Quality.AWESOME);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.CHOCOLATE_HOT), Quality.AWESOME);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.COOKIES_CHOCOLATE), Quality.AWESOME);
		categoryRegistry.put(HERB, Quality.GOOD);
		categoryRegistry.put(FRUIT, Quality.GOOD);
		categoryRegistry.put(VEGETABLE, Quality.GOOD);
		categoryRegistry.put(MUSHROOM, Quality.GOOD);
		categoryRegistry.put(MEAT, Quality.GOOD);
		categoryRegistry.put(EGG, Quality.GOOD);
		categoryRegistry.put(MILK, Quality.GOOD);
		categoryRegistry.put(MONEY, Quality.DECENT);
		stackRegistry.register(Ore.of("dyeBrown"), Quality.GOOD);
		categoryRegistry.put(FLOWER, Quality.DECENT);
		categoryRegistry.put(GEM, Quality.DISLIKE);
		categoryRegistry.put(WOOL, Quality.BAD);
		stackRegistry.register(Ore.of("string"), Quality.TERRIBLE);
		stackRegistry.register(Items.SPIDER_EYE, Quality.TERRIBLE);
		stackRegistry.register(Items.FERMENTED_SPIDER_EYE, Quality.TERRIBLE);
	}
}