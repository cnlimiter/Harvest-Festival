package joshie.harvest.npcs.gift;

import joshie.harvest.api.core.Ore;
import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.item.ItemMeal.Meal;
import net.minecraft.init.Blocks;

@SuppressWarnings("unused")
public class GiftsYulif extends Gifts {
	public GiftsYulif() {
		stackRegistry.register(Ore.of("cropWatermelon"), Quality.AWESOME);
		stackRegistry.register(Blocks.MELON_BLOCK, Quality.AWESOME);
		stackRegistry.register(Ore.of("gemQuartz"), Quality.GOOD);
		stackRegistry.register(Ore.of("cropCorn"), Quality.GOOD);
		stackRegistry.register(Ore.of("cropPineapple"), Quality.GOOD);
		categoryRegistry.put(GiftCategory.BUILDING, Quality.GOOD);
		categoryRegistry.put(GiftCategory.FLOWER, Quality.DECENT);
		categoryRegistry.put(GiftCategory.MINERAL, Quality.DECENT);
		stackRegistry.register(Ore.of("cropGreenPepper"), Quality.DECENT);
		categoryRegistry.put(GiftCategory.MONSTER, Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.SALAD), Quality.DECENT);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.TURNIP_PICKLED), Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.SPINACH_BOILED), Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.CORN_BAKED), Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.JUICE_TOMATO), Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.VEGETABLE, Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.JUICE_VEGETABLE), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.LATTE_VEGETABLE), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.JUICE_MIX), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.LATTE_MIX), Quality.BAD);
		categoryRegistry.put(GiftCategory.PLANT, Quality.BAD);
		stackRegistry.register(Ore.of("cropCarrot"), Quality.TERRIBLE);
		stackRegistry.register(Ore.of("cropPotato"), Quality.TERRIBLE);
		stackRegistry.register(Ore.of("cropWheat"), Quality.TERRIBLE);
	}
}