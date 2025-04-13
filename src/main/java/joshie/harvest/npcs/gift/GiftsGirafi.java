package joshie.harvest.npcs.gift;

import joshie.harvest.animals.HFAnimals;
import joshie.harvest.animals.item.ItemAnimalProduct.Sizeable;
import joshie.harvest.api.core.Ore;
import joshie.harvest.api.core.Size;
import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.item.ItemIngredients.Ingredient;
import joshie.harvest.cooking.item.ItemMeal.Meal;
import net.minecraft.init.Items;

@SuppressWarnings("unused")
public class GiftsGirafi extends Gifts {
	public GiftsGirafi() {
		stackRegistry.register(Ore.of("cropPotato"), Quality.AWESOME);
		stackRegistry.register(Items.BAKED_POTATO, Quality.AWESOME);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.FRIES_FRENCH), Quality.AWESOME);
		stackRegistry.register(Ore.of("cropTomato"), Quality.GOOD);
		categoryRegistry.put(GiftCategory.WOOL, Quality.GOOD);
		categoryRegistry.put(GiftCategory.FLOWER, Quality.DECENT);
		categoryRegistry.put(GiftCategory.EGG, Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.EGG_BOILED), Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.EGG_SCRAMBLED), Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.EGG_OVERRICE), Quality.DISLIKE);
		stackRegistry.register(HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.MAYONNAISE, Size.SMALL), Quality.DISLIKE);
		stackRegistry.register(HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.MAYONNAISE, Size.MEDIUM), Quality.DISLIKE);
		stackRegistry.register(HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.MAYONNAISE, Size.LARGE), Quality.DISLIKE);
		stackRegistry.register(HFCooking.INGREDIENTS.getStackFromEnum(Ingredient.CHOCOLATE), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.CAKE_CHOCOLATE), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.COOKIES_CHOCOLATE), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.CHOCOLATE_HOT), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.MILK_HOT), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.MILK_STRAWBERRY), Quality.BAD);
		categoryRegistry.put(GiftCategory.MILK, Quality.BAD);
		stackRegistry.register(Ore.of("cropPumpkin"), Quality.TERRIBLE);
		stackRegistry.register(Items.PUMPKIN_PIE, Quality.TERRIBLE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.STEW_PUMPKIN), Quality.TERRIBLE);
	}
}