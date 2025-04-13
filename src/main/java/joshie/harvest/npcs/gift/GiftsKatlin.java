package joshie.harvest.npcs.gift;

import joshie.harvest.animals.HFAnimals;
import joshie.harvest.animals.item.ItemAnimalProduct.Sizeable;
import joshie.harvest.api.core.Ore;
import joshie.harvest.api.core.Size;
import joshie.harvest.api.npc.gift.GiftCategory;
import net.minecraft.init.Items;

@SuppressWarnings("unused")
public class GiftsKatlin extends Gifts {
	public GiftsKatlin() {
		stackRegistry.register(Ore.of("string"), Quality.AWESOME);
		stackRegistry.register(HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.WOOL, Size.SMALL), Quality.AWESOME);
		stackRegistry.register(HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.WOOL, Size.MEDIUM), Quality.AWESOME);
		stackRegistry.register(HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.WOOL, Size.LARGE), Quality.AWESOME);
		stackRegistry.register(Ore.of("cropBeetroot"), Quality.GOOD);
		categoryRegistry.put(GiftCategory.WOOL, Quality.GOOD);
		categoryRegistry.put(GiftCategory.HERB, Quality.GOOD);
		categoryRegistry.put(GiftCategory.MUSHROOM, Quality.GOOD);
		categoryRegistry.put(GiftCategory.MAGIC, Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.MINERAL, Quality.BAD);
		categoryRegistry.put(GiftCategory.MONEY, Quality.DISLIKE);
		stackRegistry.register(Items.COAL, Quality.TERRIBLE);
		stackRegistry.register(Ore.of("dustRedstone"), Quality.TERRIBLE);
		stackRegistry.register(Ore.of("gemQuartz"), Quality.TERRIBLE);
	}
}