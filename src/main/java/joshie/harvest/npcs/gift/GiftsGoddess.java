package joshie.harvest.npcs.gift;

import joshie.harvest.api.core.Ore;
import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.item.ItemIngredients.Ingredient;
import joshie.harvest.cooking.item.ItemMeal.Meal;
import joshie.harvest.fishing.HFFishing;
import joshie.harvest.fishing.item.ItemJunk.Junk;
import net.minecraft.init.Items;

@SuppressWarnings("unused")
public class GiftsGoddess extends Gifts {
	public GiftsGoddess() {
		stackRegistry.register(Ore.of("cropStrawberry"), Quality.AWESOME);
		stackRegistry.register(Ore.of("cropPineapple"), Quality.GOOD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.JAM_STRAWBERRY), Quality.DECENT);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.MILK_STRAWBERRY), Quality.DECENT);
		categoryRegistry.put(GiftCategory.FLOWER, Quality.DECENT);
		categoryRegistry.put(GiftCategory.PLANT, Quality.DECENT);
		categoryRegistry.put(GiftCategory.MUSHROOM, Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.COOKING, Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.KNOWLEDGE, Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.GEM, Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.MINERAL, Quality.BAD);
		categoryRegistry.put(GiftCategory.MEAT, Quality.BAD);
		categoryRegistry.put(GiftCategory.FISH, Quality.BAD);
		categoryRegistry.put(GiftCategory.ART, Quality.BAD);
		categoryRegistry.put(GiftCategory.MONEY, Quality.BAD);
		stackRegistry.register(Items.RABBIT_FOOT, Quality.BAD);
		stackRegistry.register(HFCooking.INGREDIENTS.getStackFromEnum(Ingredient.WINE), Quality.BAD);
		stackRegistry.register(HFFishing.JUNK.getStackFromEnum(Junk.BONES), Quality.TERRIBLE);
		stackRegistry.register(HFFishing.JUNK.getStackFromEnum(Junk.CAN), Quality.TERRIBLE);
		stackRegistry.register(HFFishing.JUNK.getStackFromEnum(Junk.BOOT), Quality.TERRIBLE);
	}
}