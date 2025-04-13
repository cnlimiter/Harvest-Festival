package joshie.harvest.npcs.gift.init;

import org.apache.commons.lang3.text.WordUtils;

import joshie.harvest.animals.HFAnimals;
import joshie.harvest.animals.item.ItemAnimalProduct.Sizeable;
import joshie.harvest.api.animals.AnimalFoodType;
import joshie.harvest.api.core.Ore;
import joshie.harvest.api.core.Size;
import joshie.harvest.api.crops.Crop;
import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.core.util.annotations.HFLoader;

@HFLoader(priority = 0)
@SuppressWarnings("unused")
public class HFGiftsFarming extends HFGiftsAbstract {
	public static void init() {
		assignGeneric(HFAnimals.TREATS, GiftCategory.JUNK);
		registerAllSizes(Sizeable.EGG, GiftCategory.EGG);
		registerAllSizes(Sizeable.WOOL, GiftCategory.WOOL);
		registerAllSizes(Sizeable.MILK, GiftCategory.MILK);
		registerAllSizes(Sizeable.MAYONNAISE, GiftCategory.COOKING);
	}

	private static void registerAllSizes(Sizeable sizeable, GiftCategory category) {
		assignGeneric(HFAnimals.ANIMAL_PRODUCT.getStack(sizeable, Size.SMALL), category);
		assignGeneric(HFAnimals.ANIMAL_PRODUCT.getStack(sizeable, Size.MEDIUM), category);
		assignGeneric(HFAnimals.ANIMAL_PRODUCT.getStack(sizeable, Size.LARGE), category);
	}

	public static void postInit() {
		Crop.REGISTRY.values().stream().filter(crop -> crop != Crop.NULL_CROP)
				.forEachOrdered(crop -> assignGeneric(
						Ore.of("crop" + WordUtils.capitalizeFully(crop.getResource().getResourcePath(), '_').replace("_", "")),
						crop.getFoodType() == AnimalFoodType.FRUIT ? GiftCategory.FRUIT :
								crop.getFoodType() == AnimalFoodType.VEGETABLE ? GiftCategory.VEGETABLE :
										GiftCategory.PLANT));
	}
}