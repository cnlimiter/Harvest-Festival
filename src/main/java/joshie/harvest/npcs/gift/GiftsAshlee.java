package joshie.harvest.npcs.gift;

import joshie.harvest.animals.HFAnimals;
import joshie.harvest.animals.item.ItemAnimalTool.Tool;
import joshie.harvest.api.core.Ore;
import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.item.ItemMeal.Meal;
import net.minecraft.init.Items;

@SuppressWarnings("unused")
public class GiftsAshlee extends Gifts {
	public GiftsAshlee() {
		stackRegistry.register(Ore.of("cropBanana"), Quality.AWESOME);
		stackRegistry.register(Ore.of("cropCorn"), Quality.AWESOME);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.CORN_BAKED), Quality.AWESOME);
		categoryRegistry.put(GiftCategory.EGG, Quality.GOOD);
		categoryRegistry.put(GiftCategory.FRUIT, Quality.GOOD);
		stackRegistry.register(HFAnimals.TOOLS.getStackFromEnum(Tool.CHICKEN_FEED), Quality.DECENT);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.KETCHUP), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.FRIES_FRENCH), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.DOUGHNUT), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.POPCORN), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.ICE_CREAM), Quality.BAD);
		stackRegistry.register(Items.RABBIT_FOOT, Quality.BAD);
		categoryRegistry.put(GiftCategory.MEAT, Quality.BAD);
		stackRegistry.register(Items.BEEF, Quality.TERRIBLE);
		stackRegistry.register(Items.PORKCHOP, Quality.TERRIBLE);
		stackRegistry.register(Items.RABBIT, Quality.TERRIBLE);
		stackRegistry.register(Items.MUTTON, Quality.TERRIBLE);
	}
}
