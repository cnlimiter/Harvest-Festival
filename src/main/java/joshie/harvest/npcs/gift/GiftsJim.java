package joshie.harvest.npcs.gift;

import joshie.harvest.animals.HFAnimals;
import joshie.harvest.animals.item.ItemAnimalProduct.Sizeable;
import joshie.harvest.api.core.Ore;
import joshie.harvest.api.core.Size;
import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.item.ItemMeal.Meal;
import joshie.harvest.mining.HFMining;
import joshie.harvest.mining.item.ItemMaterial.Material;
import net.minecraft.init.Items;

@SuppressWarnings("unused")
public class GiftsJim extends Gifts {
	public GiftsJim() {
		stackRegistry.register(Items.SADDLE, Quality.AWESOME);
		stackRegistry.register(Items.LEAD, Quality.AWESOME);
		stackRegistry.register(Items.NAME_TAG, Quality.AWESOME);
		categoryRegistry.put(GiftCategory.MILK, Quality.GOOD);
		categoryRegistry.put(GiftCategory.WOOL, Quality.GOOD);
		categoryRegistry.put(GiftCategory.MEAT, Quality.GOOD);
		stackRegistry.register(Ore.of("cropWheat"), Quality.GOOD);
		stackRegistry.register(Items.RABBIT_FOOT, Quality.GOOD);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.EGG_BOILED), Quality.DECENT);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.EGG_SCRAMBLED), Quality.DECENT);
		stackRegistry.register(HFCooking.MEAL.getStackFromEnum(Meal.EGG_OVERRICE), Quality.DECENT);
		categoryRegistry.put(GiftCategory.JUNK, Quality.DECENT);
		categoryRegistry.put(GiftCategory.MONEY, Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.MAGIC, Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.EGG, Quality.DISLIKE);
		stackRegistry.register(HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.MAYONNAISE, Size.SMALL), Quality.DISLIKE);
		stackRegistry.register(HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.MAYONNAISE, Size.MEDIUM), Quality.DISLIKE);
		stackRegistry.register(HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.MAYONNAISE, Size.LARGE), Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.FISH, Quality.BAD);
		categoryRegistry.put(GiftCategory.GEM, Quality.BAD);
		stackRegistry.register(Ore.of("gemEmerald"), Quality.TERRIBLE);
		stackRegistry.register(Ore.of("gemDiamond"), Quality.TERRIBLE);
		stackRegistry.register(HFMining.MATERIALS.getStackFromEnum(Material.ALEXANDRITE), Quality.TERRIBLE);
		stackRegistry.register(HFMining.MATERIALS.getStackFromEnum(Material.PINK_DIAMOND), Quality.TERRIBLE);
	}
}
