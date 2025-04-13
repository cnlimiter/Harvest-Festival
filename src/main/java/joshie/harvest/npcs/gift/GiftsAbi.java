package joshie.harvest.npcs.gift;

import joshie.harvest.api.core.Ore;
import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.item.ItemIngredients.Ingredient;
import joshie.harvest.cooking.item.ItemMeal.Meal;
import net.minecraft.init.Items;

@SuppressWarnings("unused")
public class GiftsAbi extends Gifts {
	public GiftsAbi() {
		stackRegistry.register(Items.SUGAR, Quality.AWESOME);
		stackRegistry.register(Items.COOKIE, Quality.AWESOME);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.COOKIES), Quality.AWESOME);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.COOKIES_CHOCOLATE), Quality.AWESOME);
		stackRegistry.register(Items.SADDLE, Quality.GOOD);
		stackRegistry.register(Ore.of("cropApple"), Quality.GOOD);
		stackRegistry.register(Ore.of("cropGrape"), Quality.GOOD);
		stackRegistry.register(Ore.of("cropPotato"), Quality.DECENT);
		stackRegistry.register(Ore.of("cropCarrot"), Quality.DECENT);
		stackRegistry.register(Ore.of("cropCabbage"), Quality.DECENT);
		stackRegistry.register(Ore.of("cropSweetPotato"), Quality.DECENT);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.RICE_MATSUTAKE), Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.RICE_MUSHROOM), Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.MUSHROOM, Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.VEGETABLE, Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.TURNIP_PICKLED), Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.SALAD), Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.SPINACH_BOILED), Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.CORN_BAKED), Quality.DISLIKE);
		stackRegistry.register(Items.RABBIT_FOOT, Quality.DISLIKE);
		stackRegistry.register(HFCooking.INGREDIENTS.getStackFromEnum(Ingredient.WINE), Quality.DISLIKE);
		stackRegistry.register(Ore.of("leather"), Quality.BAD);
		stackRegistry.register(Items.RABBIT_HIDE, Quality.BAD);
		stackRegistry.register(Items.ROTTEN_FLESH, Quality.TERRIBLE);
		stackRegistry.register(Ore.of("bone"), Quality.TERRIBLE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.PORRIDGE), Quality.TERRIBLE);
	}
}