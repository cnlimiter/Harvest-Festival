package joshie.harvest.npcs.gift;

import joshie.harvest.animals.HFAnimals;
import joshie.harvest.animals.item.ItemAnimalProduct.Sizeable;
import joshie.harvest.api.core.Ore;
import joshie.harvest.api.core.Size;
import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.item.ItemMeal.Meal;
import joshie.harvest.gathering.HFGathering;
import joshie.harvest.gathering.block.BlockNature.NaturalBlock;
import net.minecraft.init.Items;

@SuppressWarnings("unused")
public class GiftsCandice extends Gifts {
	public GiftsCandice() {
		stackRegistry.register(HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.MILK, Size.SMALL), Quality.AWESOME);
		stackRegistry.register(HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.MILK, Size.MEDIUM), Quality.AWESOME);
		stackRegistry.register(HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.MILK, Size.LARGE), Quality.AWESOME);
		categoryRegistry.put(GiftCategory.MILK, Quality.GOOD);
		stackRegistry.register(Ore.of("cropStrawberry"), Quality.DECENT);
		stackRegistry.register(Ore.of("cropTomato"), Quality.DECENT);
		categoryRegistry.put(GiftCategory.FLOWER, Quality.DECENT);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.BUN_JAM), Quality.DECENT);
		stackRegistry.register(HFGathering.NATURE.getStackFromEnum(NaturalBlock.LAVENDER), Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.JAM_APPLE), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.PIE_APPLE), Quality.BAD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.SOUFFLE_APPLE), Quality.BAD);
		categoryRegistry.put(GiftCategory.FRUIT, Quality.BAD);
		stackRegistry.register(Items.GOLDEN_APPLE, Quality.TERRIBLE);
		stackRegistry.register(Ore.of("cropApple"), Quality.TERRIBLE);
		stackRegistry.register(Ore.of("cropWatermelon"), Quality.TERRIBLE);
	}
}
