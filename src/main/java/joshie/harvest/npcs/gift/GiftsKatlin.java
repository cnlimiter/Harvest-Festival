package joshie.harvest.npcs.gift;

import static joshie.harvest.api.npc.gift.GiftCategory.HERB;
import static joshie.harvest.api.npc.gift.GiftCategory.MAGIC;
import static joshie.harvest.api.npc.gift.GiftCategory.MINERAL;
import static joshie.harvest.api.npc.gift.GiftCategory.MONEY;
import static joshie.harvest.api.npc.gift.GiftCategory.MUSHROOM;
import static joshie.harvest.api.npc.gift.GiftCategory.WOOL;

import joshie.harvest.animals.HFAnimals;
import joshie.harvest.animals.item.ItemAnimalProduct.Sizeable;
import joshie.harvest.api.core.Ore;
import joshie.harvest.api.core.Size;
import net.minecraft.init.Items;

@SuppressWarnings("unused")
public class GiftsKatlin extends Gifts {
	public GiftsKatlin() {
		stackRegistry.register(Ore.of("string"), Quality.AWESOME);
		stackRegistry.register(HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.WOOL, Size.SMALL), Quality.AWESOME);
		stackRegistry.register(HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.WOOL, Size.MEDIUM), Quality.AWESOME);
		stackRegistry.register(HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.WOOL, Size.LARGE), Quality.AWESOME);
		stackRegistry.register(Ore.of("cropBeetroot"), Quality.GOOD);
		categoryRegistry.put(WOOL, Quality.GOOD);
		categoryRegistry.put(HERB, Quality.GOOD);
		categoryRegistry.put(MUSHROOM, Quality.GOOD);
		categoryRegistry.put(MAGIC, Quality.DISLIKE);
		categoryRegistry.put(MINERAL, Quality.BAD);
		categoryRegistry.put(MONEY, Quality.DISLIKE);
		stackRegistry.register(Items.COAL, Quality.TERRIBLE);
		stackRegistry.register(Ore.of("dustRedstone"), Quality.TERRIBLE);
		stackRegistry.register(Ore.of("gemQuartz"), Quality.TERRIBLE);
	}
}