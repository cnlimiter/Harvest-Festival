package joshie.harvest.npcs.gift;


import joshie.harvest.api.core.Ore;
import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.item.ItemMeal.Meal;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

@SuppressWarnings("unused")
public class GiftsThomas extends Gifts {
	public GiftsThomas() {
		stackRegistry.register(Blocks.TNT, Quality.AWESOME);
		stackRegistry.register(Ore.of("gunpowder"), Quality.AWESOME);
		stackRegistry.register(Items.TNT_MINECART, Quality.AWESOME);
		categoryRegistry.put(GiftCategory.MEAT, Quality.GOOD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.DOUGHNUT), Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.ICE_CREAM), Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.FLOWER, Quality.DECENT);
		categoryRegistry.put(GiftCategory.MUSHROOM, Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.RICE_MATSUTAKE), Quality.DISLIKE);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.RICE_MUSHROOM), Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.MAGIC, Quality.BAD);
		categoryRegistry.put(GiftCategory.KNOWLEDGE, Quality.BAD);
		stackRegistry.register(Items.POTIONITEM, Quality.TERRIBLE);
		stackRegistry.register(Items.LINGERING_POTION, Quality.TERRIBLE);
		stackRegistry.register(Items.SPLASH_POTION, Quality.TERRIBLE);
		stackRegistry.register(Items.EXPERIENCE_BOTTLE, Quality.TERRIBLE);
		stackRegistry.register(Items.ENCHANTED_BOOK, Quality.TERRIBLE);
	}
}