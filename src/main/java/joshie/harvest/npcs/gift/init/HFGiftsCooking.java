package joshie.harvest.npcs.gift.init;

import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.item.ItemIngredients.Ingredient;
import joshie.harvest.core.util.annotations.HFLoader;

@HFLoader(priority = 0)
public class HFGiftsCooking extends HFGiftsAbstract {
	public static void init() {
		assignGeneric(HFCooking.MEAL, GiftCategory.COOKING); //Register all the meals the same
		assignGeneric(
				HFCooking.INGREDIENTS.getStackFromEnum(Ingredient.CHOCOLATE),
				GiftCategory.COOKING);
		assignGeneric(
				HFCooking.INGREDIENTS.getStackFromEnum(Ingredient.DUMPLING_POWDER),
				GiftCategory.JUNK);
		assignGeneric(
				HFCooking.INGREDIENTS.getStackFromEnum(Ingredient.CURRY_POWDER),
				GiftCategory.JUNK);
		assignGeneric(
				HFCooking.INGREDIENTS.getStackFromEnum(Ingredient.WINE),
				GiftCategory.COOKING);
		assignGeneric(
				HFCooking.INGREDIENTS.getStackFromEnum(Ingredient.FLOUR),
				GiftCategory.JUNK);
		assignGeneric(
				HFCooking.INGREDIENTS.getStackFromEnum(Ingredient.OIL),
				GiftCategory.JUNK);
		assignGeneric(
				HFCooking.INGREDIENTS.getStackFromEnum(Ingredient.RICEBALL),
				GiftCategory.JUNK);
		assignGeneric(
				HFCooking.INGREDIENTS.getStackFromEnum(Ingredient.SALT),
				GiftCategory.JUNK);
	}
}