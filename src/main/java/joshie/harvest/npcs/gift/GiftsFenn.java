package joshie.harvest.npcs.gift;

import joshie.harvest.api.core.Ore;
import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.item.ItemIngredients.Ingredient;
import joshie.harvest.cooking.item.ItemMeal.Meal;
import net.minecraft.init.Blocks;

@SuppressWarnings("unused")
public class GiftsFenn extends Gifts {
	public GiftsFenn() {
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.SALAD), Quality.AWESOME);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.SPINACH_BOILED), Quality.AWESOME);
		stackRegistry.register(Ore.of("blockCactus"), Quality.AWESOME);
		stackRegistry.register(Ore.of("cropSpinach"), Quality.GOOD);
		stackRegistry.register(Ore.of("cropCucumber"), Quality.GOOD);
		stackRegistry.register(Ore.of("vine"), Quality.GOOD);
		stackRegistry.register(Blocks.WATERLILY, Quality.GOOD);
		categoryRegistry.put(GiftCategory.MUSHROOM, Quality.GOOD);
		categoryRegistry.put(GiftCategory.HERB, Quality.GOOD);
		categoryRegistry.put(GiftCategory.PLANT, Quality.DECENT);
		categoryRegistry.put(GiftCategory.MONEY, Quality.DECENT);
		categoryRegistry.put(GiftCategory.GEM, Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.MONSTER, Quality.DISLIKE);
		stackRegistry.register(HFCooking.INGREDIENTS.getStackFromEnum(Ingredient.CHOCOLATE), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.BREAD_RAISIN), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.JUICE_GRAPE), Quality.TERRIBLE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.JAM_GRAPE), Quality.TERRIBLE);
		stackRegistry.register(Ore.of("cropGrape"), Quality.TERRIBLE);
		stackRegistry.register(HFCooking.INGREDIENTS.getStackFromEnum(Ingredient.WINE), Quality.TERRIBLE);
	}
}
