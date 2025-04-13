package joshie.harvest.npcs.gift;

import joshie.harvest.api.core.Ore;
import joshie.harvest.api.npc.gift.GiftCategory;
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
		categoryRegistry.put(GiftCategory.HERB, Quality.GOOD);
		categoryRegistry.put(GiftCategory.FRUIT, Quality.GOOD);
		categoryRegistry.put(GiftCategory.VEGETABLE, Quality.GOOD);
		categoryRegistry.put(GiftCategory.MUSHROOM, Quality.GOOD);
		categoryRegistry.put(GiftCategory.MEAT, Quality.GOOD);
		categoryRegistry.put(GiftCategory.EGG, Quality.GOOD);
		categoryRegistry.put(GiftCategory.MILK, Quality.GOOD);
		categoryRegistry.put(GiftCategory.MONEY, Quality.DECENT);
		stackRegistry.register(Ore.of("dyeBrown"), Quality.GOOD);
		categoryRegistry.put(GiftCategory.FLOWER, Quality.DECENT);
		categoryRegistry.put(GiftCategory.GEM, Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.WOOL, Quality.BAD);
		stackRegistry.register(Ore.of("string"), Quality.TERRIBLE);
		stackRegistry.register(Items.SPIDER_EYE, Quality.TERRIBLE);
		stackRegistry.register(Items.FERMENTED_SPIDER_EYE, Quality.TERRIBLE);
	}
}