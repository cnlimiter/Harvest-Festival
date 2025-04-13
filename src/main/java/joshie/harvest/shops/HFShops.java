package joshie.harvest.shops;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import joshie.harvest.HarvestFestival;
import joshie.harvest.animals.HFAnimals;
import joshie.harvest.animals.block.BlockSizedStorage;
import joshie.harvest.animals.block.BlockTray;
import joshie.harvest.animals.block.BlockTrough.Trough;
import joshie.harvest.animals.entity.EntityHarvestChicken;
import joshie.harvest.animals.entity.EntityHarvestCow;
import joshie.harvest.animals.entity.EntityHarvestSheep;
import joshie.harvest.animals.item.ItemAnimalProduct.Sizeable;
import joshie.harvest.animals.item.ItemAnimalSpawner;
import joshie.harvest.animals.item.ItemAnimalTool.Tool;
import joshie.harvest.animals.item.ItemAnimalTreat.Treat;
import joshie.harvest.api.HFApi;
import joshie.harvest.api.calendar.CalendarDate;
import joshie.harvest.api.calendar.Festival;
import joshie.harvest.api.calendar.Season;
import joshie.harvest.api.calendar.Weekday;
import joshie.harvest.api.core.ITiered.ToolTier;
import joshie.harvest.api.core.Size;
import joshie.harvest.api.crops.Crop;
import joshie.harvest.api.npc.NPC;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.api.shops.Shop;
import joshie.harvest.buildings.HFBuildings;
import joshie.harvest.calendar.HFFestivals;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.block.BlockCookware.Cookware;
import joshie.harvest.cooking.item.ItemIngredients;
import joshie.harvest.cooking.item.ItemMeal.Meal;
import joshie.harvest.cooking.item.ItemUtensil;
import joshie.harvest.core.HFCore;
import joshie.harvest.core.block.BlockStorage.Storage;
import joshie.harvest.core.helpers.ConfigHelper;
import joshie.harvest.core.registry.ShippingRegistry;
import joshie.harvest.core.util.annotations.HFLoader;
import joshie.harvest.crops.HFCrops;
import joshie.harvest.crops.block.BlockSprinkler.Sprinkler;
import joshie.harvest.fishing.HFFishing;
import joshie.harvest.fishing.block.BlockFishTrap.Aquatic;
import joshie.harvest.fishing.block.BlockFloating.Floating;
import joshie.harvest.fishing.item.ItemFish;
import joshie.harvest.fishing.item.ItemFish.Fish;
import joshie.harvest.fishing.item.ItemJunk.Junk;
import joshie.harvest.gathering.HFGathering;
import joshie.harvest.gathering.block.BlockNature.NaturalBlock;
import joshie.harvest.knowledge.HFKnowledge;
import joshie.harvest.knowledge.HFNotes;
import joshie.harvest.knowledge.item.ItemBook.Book;
import joshie.harvest.mining.HFMining;
import joshie.harvest.mining.block.BlockElevator.Elevator;
import joshie.harvest.mining.block.BlockLadder.Ladder;
import joshie.harvest.mining.item.ItemMaterial.Material;
import joshie.harvest.mining.item.ItemMiningTool.MiningTool;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.npcs.npc.NPCHolidayStore;
import joshie.harvest.npcs.npc.NPCHolidayStoreSpecial;
import joshie.harvest.quests.HFQuests;
import joshie.harvest.quests.Quests;
import joshie.harvest.quests.base.QuestRecipe;
import joshie.harvest.quests.block.BlockQuestBoard.QuestBlock;
import joshie.harvest.shops.purchasable.Purchasable;
import joshie.harvest.shops.purchasable.PurchasableBuilding;
import joshie.harvest.shops.purchasable.PurchasableCrop;
import joshie.harvest.shops.purchasable.PurchasableCropSeeds;
import joshie.harvest.shops.purchasable.PurchasableDestroy;
import joshie.harvest.shops.purchasable.PurchasableEntity;
import joshie.harvest.shops.purchasable.PurchasableMaterials;
import joshie.harvest.shops.purchasable.PurchasableMeal;
import joshie.harvest.shops.purchasable.PurchasableObtained;
import joshie.harvest.shops.purchasable.PurchasableObtainedMaterial;
import joshie.harvest.shops.purchasable.PurchasableOre;
import joshie.harvest.shops.purchasable.PurchasableRandomMeal;
import joshie.harvest.shops.purchasable.PurchasableRecipe;
import joshie.harvest.shops.purchasable.PurchasableRecipeShipped;
import joshie.harvest.shops.purchasable.PurchasableStock;
import joshie.harvest.shops.purchasable.PurchasableTrade;
import joshie.harvest.shops.purchasable.PurchasableWeekend;
import joshie.harvest.shops.requirement.Adamantite;
import joshie.harvest.shops.requirement.Bricks;
import joshie.harvest.shops.requirement.Copper;
import joshie.harvest.shops.requirement.Glass;
import joshie.harvest.shops.requirement.Gold;
import joshie.harvest.shops.requirement.Iron;
import joshie.harvest.shops.requirement.Logs;
import joshie.harvest.shops.requirement.Planks;
import joshie.harvest.shops.requirement.Saplings;
import joshie.harvest.shops.requirement.Silver;
import joshie.harvest.shops.requirement.Stone;
import joshie.harvest.shops.requirement.String;
import joshie.harvest.shops.rules.SpecialRulesFriendship;
import joshie.harvest.shops.rules.SpecialRulesQuest;
import joshie.harvest.tools.HFTools;
import joshie.harvest.town.TownHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@HFLoader
public class HFShops {
	public static final Shop BARN = newShop(HarvestFestival.id("barn"), HFNPCs.BARN_OWNER);
	public static final Shop CAFE = newShop(HarvestFestival.id("cafe"), HFNPCs.CAFE_OWNER);
	public static final Shop CARPENTER = newShop(HarvestFestival.id("carpenter"), HFNPCs.CARPENTER);
	public static final Shop POULTRY = newShop(HarvestFestival.id("poultry"), HFNPCs.POULTRY);
	public static final Shop SUPERMARKET = newShop(HarvestFestival.id("general"), HFNPCs.GS_OWNER);
	public static final Shop MINER = newShop(HarvestFestival.id("miner"), HFNPCs.MINER);
	//Added in 0.6+
	public static final Shop BAITSHOP = newShop(HarvestFestival.id("baitshop"), HFNPCs.FISHERMAN);
	public static final Shop CLOCKMAKER = newShop(HarvestFestival.id("clockmaker"), HFNPCs.CLOCKMAKER);
	public static final Shop BLOODMAGE = newShop(
			HarvestFestival.id("bloodmage"),
			null).setSpecialSellingRules(new SpecialRulesFriendship(HFNPCs.CLOCKMAKER, 15000)).setOpensOnHolidays();
	public static final Shop KITCHEN = newShop(
			HarvestFestival.id("kitchen"),
			HFNPCs.CAFE_GRANNY).setSpecialSellingRules(new SpecialRulesFriendship(HFNPCs.CAFE_GRANNY, 15000));
	public static final Shop TRADER = newShop(
			HarvestFestival.id("trader"),
			HFNPCs.TRADER).setSpecialSellingRules(new SpecialRulesFriendship(HFNPCs.TRADER, 15000));
	public static final Shop COOKING_FESTIVAL_FOOD = newHolidayShop(
			HarvestFestival.id("cooking"),
			HFNPCs.CAFE_GRANNY,
			HFFestivals.COOKING_CONTEST);
	public static final Shop COOKING_FESTIVAL_RECIPES = newHolidayShop(
			HarvestFestival.id("recipes"),
			HFNPCs.CAFE_OWNER,
			HFFestivals.COOKING_CONTEST);
	public static final Shop COW_FESTIVAL_BARGAINS = newHolidayShop(
			HarvestFestival.id("cow"),
			HFNPCs.BARN_OWNER,
			HFFestivals.COW_FESTIVAL);
	public static final Shop COW_FESTIVAL_DAIRY_QUEEN = newHolidayShop(
			HarvestFestival.id("dairy"),
			HFNPCs.CAFE_OWNER,
			HFFestivals.COW_FESTIVAL);
	public static final Shop SHEEP_FESTIVAL_SALE = newHolidayShop(
			HarvestFestival.id("sheep"),
			HFNPCs.MILKMAID,
			HFFestivals.SHEEP_FESTIVAL);
	public static final Shop SHEEP_FESTIVAL_KNITTNG = newHolidayShop(
			HarvestFestival.id("knitting"),
			HFNPCs.CAFE_GRANNY,
			HFFestivals.SHEEP_FESTIVAL);
	public static final Shop CHICKEN_FESTIVAL_MFC = newHolidayShop(
			HarvestFestival.id("mfc"),
			HFNPCs.CAFE_OWNER,
			HFFestivals.CHICKEN_FESTIVAL);

	@SuppressWarnings("unused")
	public static void postInit() {
		registerBarn();
		registerBloodmage();
		registerCafe();
		registerCafeKitchen();
		registerCarpenter();
		registerClockmaker();
		registerMiner();
		registerPoultry();
		registerSupermarket();
		registerTackleshop();
		registerTrader();

		//Event specific
		registerCooking();
		registerRecipes();
		registerCowBargains();
		registerDairyQueen();
		registerSaleSheep();
		registerGrannysKnits();
		registerMinecraftFriedChicken();
	}

	private static void registerBarn() {
		BARN.addPurchasable(new Purchasable(100, HFCrops.GRASS.getCropStack(1)).addTooltip("crop.grass.item"));
		BARN.addPurchasable(10, HFAnimals.TREATS.getStackFromEnum(Treat.GENERIC));
		BARN.addPurchasable(30, HFAnimals.TREATS.getStackFromEnum(Treat.COW));
		BARN.addPurchasable(30, HFAnimals.TREATS.getStackFromEnum(Treat.SHEEP));
		BARN.addPurchasable(new PurchasableEntity(
				EntityHarvestCow.class,
				3000,
				HFAnimals.ANIMAL.getStackFromEnum(ItemAnimalSpawner.Spawner.COW)).setNote(HFNotes.COW_CARE));
		BARN.addPurchasable(new PurchasableEntity(
				EntityHarvestSheep.class,
				2500,
				HFAnimals.ANIMAL.getStackFromEnum(ItemAnimalSpawner.Spawner.SHEEP)).setNote(HFNotes.SHEEP_CARE));
		BARN.addPurchasable(500, HFAnimals.TROUGH.getStackFromEnum(Trough.WOOD));
		BARN.addPurchasable(1000, HFAnimals.TOOLS.getStackFromEnum(Tool.MEDICINE));
		BARN.addPurchasable(1500, HFAnimals.TOOLS.getStackFromEnum(Tool.MIRACLE_POTION));
		BARN.addPurchasable(250, new ItemStack(Items.NAME_TAG));
		BARN.addPurchasable(150, new ItemStack(Items.LEAD));
		BARN.addPurchasable(1000, new ItemStack(Items.SADDLE));
		BARN.addPurchasable(1000, HFAnimals.TOOLS.getStackFromEnum(Tool.BRUSH), 1);
		BARN.addPurchasable(2000, HFAnimals.TOOLS.getStackFromEnum(Tool.MILKER), 1);
		BARN.addPurchasable(2000, new ItemStack(Items.SHEARS), 1);
		BARN.setSpecialRules(new SpecialRulesQuest(Quests.JIM_MEET));
		BARN.addOpening(Weekday.MONDAY, 10000, 15000).addOpening(Weekday.TUESDAY, 10000, 15000).addOpening(Weekday.WEDNESDAY, 10000, 15000);
		BARN.addOpening(Weekday.THURSDAY, 10000, 15000).addOpening(Weekday.FRIDAY, 10000, 15000).addOpening(Weekday.SATURDAY, 10000, 15000);
	}

	private static void registerBloodmage() {
		BLOODMAGE.addPurchasable(-100, new ItemStack(Items.SLIME_BALL), 3);
		BLOODMAGE.addPurchasable(-150, new ItemStack(Items.BONE), 2);
		BLOODMAGE.addPurchasable(-800, new ItemStack(Items.ENDER_PEARL), 4);
		BLOODMAGE.addPurchasable(-300, new ItemStack(Items.GOLDEN_APPLE), 3);
		BLOODMAGE.addPurchasable(-50, new ItemStack(Items.ROTTEN_FLESH), 5);
		BLOODMAGE.addPurchasable(-100, new ItemStack(Items.SPIDER_EYE), 4);
		BLOODMAGE.addPurchasable(-30, new ItemStack(Items.POISONOUS_POTATO), 5);
		BLOODMAGE.addPurchasable(-600, new ItemStack(Items.GHAST_TEAR), 6);
		BLOODMAGE.addPurchasable(-150, new ItemStack(Items.FERMENTED_SPIDER_EYE), 5);
		BLOODMAGE.addPurchasable(-700, new ItemStack(Items.BLAZE_ROD), 7);
		BLOODMAGE.addPurchasable(-350, new ItemStack(Items.MAGMA_CREAM), 7);
		BLOODMAGE.addPurchasable(-50, new ItemStack(Items.SPECKLED_MELON), 5);
		BLOODMAGE.addPurchasable(-150, new ItemStack(Items.GOLDEN_CARROT), 5);
		BLOODMAGE.addPurchasable(-100, new ItemStack(Items.RABBIT_FOOT), 2);
		BLOODMAGE.addPurchasable(-200, new ItemStack(Items.GUNPOWDER), 4);
		BLOODMAGE.addPurchasable(-30, new ItemStack(Items.REDSTONE), 10);
		BLOODMAGE.addPurchasable(-50, new ItemStack(Items.GLOWSTONE_DUST), 5);
		BLOODMAGE.addOpening(Weekday.WEDNESDAY, 19000, 24000).addOpening(Weekday.WEDNESDAY, 0, 5000).addOpening(
				Weekday.SATURDAY,
				18000,
				24000).addOpening(
				Weekday.SATURDAY,
				0,
				3500);
	}

	private static void registerCafe() {
		CAFE.addPurchasable(0, new ItemStack(Items.POTIONITEM));
		CAFE.addPurchasable(new PurchasableMeal(200, HarvestFestival.id("salad")).setStock(10));
		CAFE.addPurchasable(new PurchasableMeal(100, HarvestFestival.id("cookies")).setStock(3));
		CAFE.addPurchasable(new PurchasableMeal(250, HarvestFestival.id("juice_pineapple")).setStock(5));
		CAFE.addPurchasable(new PurchasableMeal(250, HarvestFestival.id("corn_baked")).setStock(5));
		CAFE.addPurchasable(new PurchasableMeal(300, HarvestFestival.id("ice_cream")).setStock(10));
		//Add three random meals
		CAFE.addPurchasable(new PurchasableRandomMeal(5));
		CAFE.addPurchasable(new PurchasableRandomMeal(13));
		CAFE.addPurchasable(new PurchasableRandomMeal(17));

		//Allow the purchasing of cookware at the weekends
		CAFE.addPurchasable(new PurchasableWeekend(25, new ItemStack(HFCooking.COOKBOOK)).setStock(1).setNote(HFNotes.RECIPE_BOOK));
		CAFE.addPurchasable(new PurchasableWeekend(50, HFCooking.UTENSILS.getStackFromEnum(ItemUtensil.Utensil.KNIFE)).setStock(1)
				.setNote(HFNotes.KITCHEN_COUNTER));
		CAFE.addPurchasable(new PurchasableWeekend(250, HFCooking.COOKWARE.getStackFromEnum(Cookware.COUNTER)).setStock(5)
				.setNote(HFNotes.KITCHEN_COUNTER));
		CAFE.addPurchasable(new PurchasableWeekend(3000, HFCooking.COOKWARE.getStackFromEnum(Cookware.FRIDGE)).setStock(1)
				.setNote(HFNotes.FRIDGE));
		CAFE.addPurchasable(new PurchasableWeekend(2500, HFCooking.COOKWARE.getStackFromEnum(Cookware.OVEN_OFF)).setStock(1)
				.setNote(HFNotes.OVEN));
		CAFE.addPurchasable(new PurchasableWeekend(
				1500,
				HFCooking.COOKWARE.getStackFromEnum(Cookware.FRYING_PAN),
				HFCooking.COOKWARE.getStackFromEnum(Cookware.OVEN_OFF)).setStock(1).setNote(HFNotes.POTPAN));
		CAFE.addPurchasable(new PurchasableWeekend(
				1000,
				HFCooking.COOKWARE.getStackFromEnum(Cookware.POT),
				HFCooking.COOKWARE.getStackFromEnum(Cookware.OVEN_OFF)).setStock(1).setNote(HFNotes.POTPAN));
		CAFE.addPurchasable(new PurchasableWeekend(1200, HFCooking.COOKWARE.getStackFromEnum(Cookware.MIXER)).setStock(1)
				.setNote(HFNotes.MIXER));

		//Add recipes for purchase
		CAFE.addPurchasable(new PurchasableRecipe(Season.SPRING, Weekday.MONDAY, HarvestFestival.id("juice_vegetable")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.SPRING, Weekday.TUESDAY, HarvestFestival.id("sushi")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.SPRING, Weekday.WEDNESDAY, HarvestFestival.id("sashimi")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.SPRING, Weekday.THURSDAY, HarvestFestival.id("sashimi_chirashi")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.SPRING, Weekday.FRIDAY, HarvestFestival.id("cucumber_pickled")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.SUMMER, Weekday.SATURDAY, HarvestFestival.id("juice_tomato")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.SUMMER, Weekday.SUNDAY, HarvestFestival.id("cornflakes")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.SUMMER, Weekday.MONDAY, HarvestFestival.id("ketchup")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.SUMMER, Weekday.TUESDAY, HarvestFestival.id("stew_pumpkin")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.SUMMER, Weekday.WEDNESDAY, HarvestFestival.id("rice_fried")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.SUMMER, Weekday.THURSDAY, HarvestFestival.id("doria")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.SUMMER, Weekday.FRIDAY, HarvestFestival.id("juice_fruit")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.SUMMER, Weekday.SATURDAY, HarvestFestival.id("salad_herb")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.SUMMER, Weekday.SUNDAY, HarvestFestival.id("soup_herb")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.SUMMER, Weekday.SUNDAY, HarvestFestival.id("sandwich_herb")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.AUTUMN, Weekday.MONDAY, HarvestFestival.id("sweet_potatoes")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.AUTUMN, Weekday.TUESDAY, HarvestFestival.id("eggplant_happy")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.AUTUMN, Weekday.WEDNESDAY, HarvestFestival.id("sandwich")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.AUTUMN, Weekday.THURSDAY, HarvestFestival.id("sandwich_fruit")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.AUTUMN, Weekday.FRIDAY, HarvestFestival.id("latte_fruit")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.AUTUMN, Weekday.SATURDAY, HarvestFestival.id("spinach_boiled")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.AUTUMN, Weekday.SUNDAY, HarvestFestival.id("riceballs_toasted")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.WINTER, Weekday.MONDAY, HarvestFestival.id("omelet")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.WINTER, Weekday.TUESDAY, HarvestFestival.id("egg_boiled")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.WINTER, Weekday.WEDNESDAY, HarvestFestival.id("egg_overrice")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.WINTER, Weekday.THURSDAY, HarvestFestival.id("juice_mix")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.WINTER, Weekday.FRIDAY, HarvestFestival.id("pancake")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.WINTER, Weekday.SATURDAY, HarvestFestival.id("rice_matsutake")));
		CAFE.addPurchasable(new PurchasableRecipe(Season.WINTER, Weekday.SUNDAY, HarvestFestival.id("rice_mushroom")));
		CAFE.addPurchasable(new PurchasableRecipeShipped(HarvestFestival.id("juice_grape"), HFCrops.GRAPE));
		CAFE.addPurchasable(new PurchasableRecipeShipped(HarvestFestival.id("juice_apple"), HFCrops.APPLE));
		CAFE.addPurchasable(new PurchasableRecipeShipped(HarvestFestival.id("juice_orange"), HFCrops.ORANGE));
		CAFE.addPurchasable(new PurchasableRecipeShipped(HarvestFestival.id("juice_banana"), HFCrops.BANANA));
		CAFE.addPurchasable(new PurchasableRecipeShipped(HarvestFestival.id("juice_peach"), HFCrops.PEACH));
		CAFE.setSpecialRules(new SpecialRulesQuest(Quests.LIARA_MEET));
		CAFE.addOpening(Weekday.MONDAY, 9500, 17000)
				.addOpening(Weekday.TUESDAY, 9500, 17000)
				.addOpening(Weekday.WEDNESDAY, 9500, 17000)
				.addOpening(
						Weekday.THURSDAY,
						9500,
						17000);
		CAFE.addOpening(Weekday.FRIDAY, 9500, 17000).addOpening(Weekday.SATURDAY, 9500, 17000).addOpening(Weekday.SUNDAY, 9500, 17000);
	}

	@SuppressWarnings("ConstantConditions")
	private static void registerCafeKitchen() {
		KITCHEN.addPurchasable(-50, new ItemStack(Blocks.BROWN_MUSHROOM), 5);
		KITCHEN.addPurchasable(-10, new ItemStack(Items.SUGAR), 20);
		KITCHEN.addPurchasable(-25, new ItemStack(Items.CHORUS_FRUIT), 15);
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.BREAD)).setStock(4));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.BAKED_POTATO)).setStock(5));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.PUMPKIN_PIE)).setStock(2));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.MUTTON)).setStock(8));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.COOKED_MUTTON)).setStock(4));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.PORKCHOP)).setStock(3));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.COOKED_PORKCHOP)).setStock(4));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.BEEF)).setStock(3));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.COOKED_BEEF)).setStock(4));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.CHICKEN)).setStock(5));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.COOKED_CHICKEN)).setStock(5));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.RABBIT)).setStock(4));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.COOKED_RABBIT)).setStock(5));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.RABBIT_STEW)).setStock(3));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.MUSHROOM_STEW)).setStock(8));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.BEETROOT_SOUP)).setStock(4));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.CAKE)).setStock(4));
		KITCHEN.addPurchasable(new PurchasableStock(new ItemStack(Items.COOKIE)).setStock(5));
		for (Meal meal : Meal.values()) {
			if (meal.getUtensil() == null) {
				ItemStack stack = HFCooking.MEAL.getCreativeStack(meal);
				if (stack.hasTagCompound() && stack.getTagCompound().hasKey(ShippingRegistry.SELL_VALUE)) {
					KITCHEN.addPurchasable(new PurchasableStock(stack).setStock(2));
				}
			}
		}

		KITCHEN.addOpening(Weekday.FRIDAY, 6000, 9500).addOpening(Weekday.FRIDAY, 17000, 20000);
		HFNPCs.CAFE_GRANNY.setHasInfo(null); //Remove the opening times from the granny, it's a bonus
	}

	private static void registerCarpenter() {
		CARPENTER.addPurchasable(new PurchasableBuilding(0L, HFBuildings.CARPENTER, Logs.of(24)));
		CARPENTER.addPurchasable(new PurchasableBuilding(5000L, HFBuildings.SUPERMARKET, Logs.of(96), Stone.of(32)));
		CARPENTER.addPurchasable(new PurchasableBuilding(4000L, HFBuildings.BARN, Logs.of(96)));
		CARPENTER.addPurchasable(new PurchasableBuilding(3000L, HFBuildings.POULTRY_FARM, Logs.of(96)));
		CARPENTER.addPurchasable(new PurchasableBuilding(1000L, HFBuildings.FESTIVAL_GROUNDS, Logs.of(16), Saplings.of(4)));
		CARPENTER.addPurchasable(new PurchasableBuilding(3000L, HFBuildings.MINING_HILL, Logs.of(16)));
		CARPENTER.addPurchasable(new PurchasableBuilding(9000L, HFBuildings.BLACKSMITH, Logs.of(32), Stone.of(248)));
		CARPENTER.addPurchasable(new PurchasableBuilding(12000L, HFBuildings.FISHING_HUT, Logs.of(96), Glass.of(16)));
		CARPENTER.addPurchasable(new PurchasableBuilding(3000L, HFBuildings.FISHING_HOLE, Logs.of(16)));
		CARPENTER.addPurchasable(new PurchasableBuilding(
				10000L,
				HFBuildings.CAFE,
				Logs.of(200),
				Stone.of(48),
				Glass.of(32),
				Bricks.of(32)));
		CARPENTER.addPurchasable(new PurchasableBuilding(15000L, HFBuildings.CLOCKMAKER, Logs.of(128), Stone.of(64)));
		CARPENTER.addPurchasable(new PurchasableBuilding(7000L, HFBuildings.GODDESS_POND, Logs.of(32), Stone.of(64)));
		CARPENTER.addPurchasable(new PurchasableBuilding(25000L, HFBuildings.CHURCH, Logs.of(160), Stone.of(128), Glass.of(8)));
		CARPENTER.addPurchasable(new PurchasableBuilding(
				50000L,
				HFBuildings.TOWNHALL,
				Logs.of(640),
				Stone.of(256),
				Glass.of(48),
				Bricks.of(32)));
		CARPENTER.addPurchasable(new PurchasableMaterials(0L, HFCore.STORAGE.getStackFromEnum(Storage.SHIPPING), Logs.of(8)).addTooltip(
				"storage.shipping").setNote(HFNotes.SHIPPING));
		CARPENTER.addPurchasable(new PurchasableMaterials(0L, HFCore.STORAGE.getStackFromEnum(Storage.MAILBOX), Logs.of(4)).addTooltip(
				"storage.mailbox").setNote(HFNotes.MAILBOX));
		CARPENTER.addPurchasable(new PurchasableMaterials(0L, HFCrops.SPRINKLER.getStackFromEnum(Sprinkler.OLD), Iron.of(1), Copper.of(5)) {
			@Override
			public boolean canList(@Nonnull World world, @Nonnull EntityPlayer player) {
				CalendarDate date = HFApi.calendar.getDate(world);
				return (date.getYear() >= 1 || date.getSeason().ordinal() >= 1);
			}
		}.addTooltip("sprinkler.old"));

		CARPENTER.addPurchasable(new PurchasableMaterials(
				0L,
				HFCrops.SPRINKLER.getStackFromEnum(Sprinkler.IRON),
				Adamantite.of(1),
				Silver.of(5)) {
			@Override
			public boolean canList(@Nonnull World world, @Nonnull EntityPlayer player) {
				return HFApi.quests.hasCompleted(Quests.SELL_SPRINKLER, player);
			}
		}.addTooltip("sprinkler.iron"));

		CARPENTER.addPurchasable(new PurchasableMaterials(
				500L,
				HFQuests.QUEST_BLOCK.getStackFromEnum(QuestBlock.BOARD),
				Logs.of(4),
				Gold.of(1)) {
			@Override
			public boolean canList(@Nonnull World world, @Nonnull EntityPlayer player) {
				return TownHelper.getClosestTownToEntity(player, false).hasBuilding(HFBuildings.TOWNHALL);
			}
		}.setStock(1));

		CARPENTER.addPurchasable(new PurchasableMaterials(10, new ItemStack(Blocks.LOG)));
		CARPENTER.addPurchasable(new PurchasableMaterials(20, new ItemStack(Blocks.STONE)));
		if (!HFAnimals.CAN_SPAWN) {
			CARPENTER.addPurchasable(new PurchasableMaterials(500, new ItemStack(Items.BED), Planks.of(3)).setStock(1));
		}

		//Selling things to the carpenter
		CARPENTER.addPurchasable(-1, new ItemStack(Blocks.LOG));
		CARPENTER.addPurchasable(-1, new ItemStack(Blocks.STONE));
		if (HFBuildings.ENABLE_DEMOLITION) {
			//Selling buildings
			CARPENTER.addPurchasable(new PurchasableDestroy(-10L, HFBuildings.CARPENTER));
			CARPENTER.addPurchasable(new PurchasableDestroy(-3000L, HFBuildings.SUPERMARKET));
			CARPENTER.addPurchasable(new PurchasableDestroy(-2000L, HFBuildings.BARN));
			CARPENTER.addPurchasable(new PurchasableDestroy(-2000L, HFBuildings.POULTRY_FARM));
			CARPENTER.addPurchasable(new PurchasableDestroy(-500L, HFBuildings.FESTIVAL_GROUNDS));
			CARPENTER.addPurchasable(new PurchasableDestroy(-2000L, HFBuildings.MINING_HILL));
			CARPENTER.addPurchasable(new PurchasableDestroy(-5000L, HFBuildings.BLACKSMITH));
			CARPENTER.addPurchasable(new PurchasableDestroy(-6000L, HFBuildings.FISHING_HUT));
			CARPENTER.addPurchasable(new PurchasableDestroy(-2000L, HFBuildings.FISHING_HOLE));
			CARPENTER.addPurchasable(new PurchasableDestroy(-5000L, HFBuildings.CAFE));
			CARPENTER.addPurchasable(new PurchasableDestroy(-10000L, HFBuildings.CLOCKMAKER));
			CARPENTER.addPurchasable(new PurchasableDestroy(-5000L, HFBuildings.GODDESS_POND));
			CARPENTER.addPurchasable(new PurchasableDestroy(-15000L, HFBuildings.CHURCH));
			CARPENTER.addPurchasable(new PurchasableDestroy(-30000L, HFBuildings.TOWNHALL));
		}

		CARPENTER.setSpecialRules(new SpecialRulesQuest(Quests.YULIF_MEET));
		CARPENTER.addOpening(Weekday.MONDAY, 9000, 17500).addOpening(Weekday.TUESDAY, 9000, 17500).addOpening(
				Weekday.WEDNESDAY,
				9000,
				17500);
		CARPENTER.addOpening(Weekday.THURSDAY, 9000, 17500).addOpening(Weekday.FRIDAY, 9000, 17500).addOpening(Weekday.SUNDAY, 9000, 17500);
	}

	private static void registerClockmaker() {
		CLOCKMAKER.addPurchasable(500, new ItemStack(Items.CLOCK));
		CLOCKMAKER.addPurchasable(150, new ItemStack(Items.COMPASS));
		CLOCKMAKER.addPurchasable(100, new ItemStack(Items.MAP));
		CLOCKMAKER.addPurchasable(750, HFKnowledge.BOOK.getStackFromEnum(Book.CALENDAR));
		CLOCKMAKER.addOpening(Weekday.MONDAY, 8000, 15000).addOpening(Weekday.TUESDAY, 8000, 15000).addOpening(
						Weekday.WEDNESDAY,
						8000,
						15000)
				.addOpening(Weekday.THURSDAY, 8000, 15000).addOpening(Weekday.FRIDAY, 8000, 15000);
	}

	private static void registerMiner() {
		MINER.addPurchasable(40, new ItemStack(Blocks.TORCH), 160);
		MINER.addPurchasable(250, HFMining.MINING_TOOL.getStackFromEnum(MiningTool.ESCAPE_ROPE), 10);
		MINER.addPurchasable(1000, HFMining.LADDER.getStackFromEnum(Ladder.DECORATIVE), 3);
		MINER.addPurchasable(new PurchasableObtainedMaterial(
				200,
				HFMining.MINING_TOOL.getStackFromEnum(MiningTool.ELEVATOR_CABLE, 8),
				Copper.of(1)));
		MINER.addPurchasable(new PurchasableObtainedMaterial(
				1000,
				HFMining.ELEVATOR.getStackFromEnum(Elevator.JUNK),
				Logs.of(3),
				Copper.of(2),
				Adamantite.of(1)).setNote(HFNotes.ELEVATOR));
		MINER.addPurchasable(new PurchasableObtained(100, HFMining.MATERIALS.getStackFromEnum(Material.COPPER)));
		MINER.addPurchasable(new PurchasableObtained(200, HFMining.MATERIALS.getStackFromEnum(Material.SILVER)));
		MINER.addPurchasable(new PurchasableObtained(400, HFMining.MATERIALS.getStackFromEnum(Material.GOLD)));
		MINER.addPurchasable(new PurchasableOre(150, new ItemStack(Items.COAL)));
		MINER.addPurchasable(new PurchasableOre(300, new ItemStack(Items.IRON_INGOT)));
		MINER.addPurchasable(new PurchasableOre(500, new ItemStack(Items.GOLD_INGOT)));
		//Selling things to the mine
		MINER.addPurchasable(-10, new ItemStack(Items.COAL, 1, 1), 10);
		MINER.addPurchasable(-6, new ItemStack(Items.GOLD_NUGGET), 10);
		MINER.addPurchasable(-10, new ItemStack(Items.COAL), 10);
		MINER.addPurchasable(-20, new ItemStack(Items.QUARTZ), 10);
		MINER.addPurchasable(-40, new ItemStack(Items.IRON_INGOT), 8);
		MINER.addPurchasable(-60, new ItemStack(Items.GOLD_INGOT), 5);
		MINER.setSpecialRules(new SpecialRulesQuest(Quests.BRANDON_MEET));
		MINER.addOpening(Weekday.MONDAY, 11000, 16000).addOpening(Weekday.TUESDAY, 11000, 16000).addOpening(
				Weekday.WEDNESDAY,
				11000,
				16000);
		MINER.addOpening(Weekday.THURSDAY, 11000, 16000).addOpening(Weekday.FRIDAY, 11000, 16000).addOpening(
				Weekday.SATURDAY,
				11000,
				16000);
	}

	private static void registerPoultry() {
		POULTRY.addPurchasable(new Purchasable(50, HFAnimals.TOOLS.getStackFromEnum(Tool.CHICKEN_FEED)).addTooltip("tool.chicken.feed"));
		POULTRY.addPurchasable(10, HFAnimals.TREATS.getStackFromEnum(Treat.GENERIC));
		POULTRY.addPurchasable(30, HFAnimals.TREATS.getStackFromEnum(Treat.CHICKEN));
		POULTRY.addPurchasable(new PurchasableEntity(
				EntityHarvestChicken.class,
				1500,
				HFAnimals.ANIMAL.getStackFromEnum(ItemAnimalSpawner.Spawner.CHICKEN)).setNote(
				HFNotes.CHICKEN_CARE));
		POULTRY.addPurchasable(1000, HFAnimals.TOOLS.getStackFromEnum(Tool.MEDICINE));
		POULTRY.addPurchasable(250, new ItemStack(Items.NAME_TAG));
		POULTRY.addPurchasable(500, HFAnimals.TRAY.getStackFromEnum(BlockTray.Tray.FEEDER_EMPTY));
		POULTRY.addPurchasable(1000, HFAnimals.TRAY.getStackFromEnum(BlockTray.Tray.NEST_EMPTY));
		POULTRY.addPurchasable(5000, HFAnimals.SIZED.getStackFromEnum(BlockSizedStorage.SizedStorage.INCUBATOR));
		POULTRY.setSpecialRules(new SpecialRulesQuest(Quests.ASHLEE_MEET));
		POULTRY.addOpening(Weekday.MONDAY, 6000, 12000).addOpening(Weekday.TUESDAY, 6000, 12000).addOpening(Weekday.WEDNESDAY, 6000, 12000);
		POULTRY.addOpening(Weekday.THURSDAY, 6000, 12000).addOpening(Weekday.FRIDAY, 6000, 12000).addOpening(Weekday.SATURDAY, 6000, 12000);
	}

	private static void registerSupermarket() {
		Crop.REGISTRY.values().stream().filter(crop -> crop != Crop.NULL_CROP && crop.getSeedCost() > 0)
				.forEachOrdered(crop -> SUPERMARKET.addPurchasable(new PurchasableCropSeeds(crop)));

		SUPERMARKET.addPurchasable(500, HFTools.HOES.get(ToolTier.BASIC).getStack(), 1);
		SUPERMARKET.addPurchasable(500, HFTools.SICKLES.get(ToolTier.BASIC).getStack(), 1);
		SUPERMARKET.addPurchasable(750, HFTools.WATERING_CANS.get(ToolTier.BASIC).getStack(), 1);
		SUPERMARKET.addPurchasable(new Purchasable(1000, HFTools.AXES.get(ToolTier.BASIC).getStack()).setStock(1).setNote(HFNotes.AXE));
		SUPERMARKET.addPurchasable(new Purchasable(1000, HFTools.HAMMERS.get(ToolTier.BASIC).getStack()).setStock(1)
				.setNote(HFNotes.HAMMER));

		//TODO: Reenable in 1.0 when I readd marriage
		//SUPERMARKET.addPurchasable(new PurchasableBlueFeather(1000, HFNPCs.TOOLS.getStackFromEnum(BLUE_FEATHER)));
		SUPERMARKET.addPurchasable(
				ItemIngredients.Ingredient.RICEBALL.getCost(),
				HFCooking.INGREDIENTS.getStackFromEnum(ItemIngredients.Ingredient.RICEBALL));
		SUPERMARKET.addPurchasable(
				ItemIngredients.Ingredient.OIL.getCost(),
				HFCooking.INGREDIENTS.getStackFromEnum(ItemIngredients.Ingredient.OIL));
		SUPERMARKET.addPurchasable(
				ItemIngredients.Ingredient.FLOUR.getCost(),
				HFCooking.INGREDIENTS.getStackFromEnum(ItemIngredients.Ingredient.FLOUR));
		SUPERMARKET.addPurchasable(
				ItemIngredients.Ingredient.CURRY_POWDER.getCost(),
				HFCooking.INGREDIENTS.getStackFromEnum(ItemIngredients.Ingredient.CURRY_POWDER));
		//SUPERMARKET.addPurchasable(DUMPLING_POWDER.getCost(), HFCooking.INGREDIENTS.getStackFromEnum(DUMPLING_POWDER));
		SUPERMARKET.addPurchasable(
				ItemIngredients.Ingredient.CHOCOLATE.getCost(),
				HFCooking.INGREDIENTS.getStackFromEnum(ItemIngredients.Ingredient.CHOCOLATE));
		SUPERMARKET.addPurchasable(
				ItemIngredients.Ingredient.WINE.getCost(),
				HFCooking.INGREDIENTS.getStackFromEnum(ItemIngredients.Ingredient.WINE));
		SUPERMARKET.addPurchasable(
				ItemIngredients.Ingredient.SALT.getCost(),
				HFCooking.INGREDIENTS.getStackFromEnum(ItemIngredients.Ingredient.SALT));
		SUPERMARKET.addPurchasable(ItemIngredients.Ingredient.SALT.getCost(), new ItemStack(Items.SUGAR));
		SUPERMARKET.setSpecialRules(new SpecialRulesQuest(Quests.JENNI_MEET));
		SUPERMARKET.addOpening(Weekday.MONDAY, 9000, 17000).addOpening(Weekday.TUESDAY, 9000, 17000).addOpening(
				Weekday.THURSDAY,
				9000,
				17000);
		SUPERMARKET.addOpening(Weekday.FRIDAY, 9000, 17000).addOpening(Weekday.SATURDAY, 11000, 15000);
		SUPERMARKET.addConditionalOpening(
				(w, e, i) -> TownHelper.getClosestTownToEntity(e, false)
						.getQuests()
						.getFinished()
						.contains(Quests.OPEN_WEDNESDAYS), Weekday.WEDNESDAY, 9000, 17000);
	}

	private static void registerTackleshop() {
		BAITSHOP.addPurchasable(new Purchasable(Junk.BAIT.getCost(), HFFishing.JUNK.getStackFromEnum(Junk.BAIT)).addTooltip("junk.bait"));
		BAITSHOP.addPurchasable(1000L, HFFishing.FISHING_RODS.get(ToolTier.BASIC).getStack(), 1);
		BAITSHOP.addPurchasable(new Purchasable(500L, HFFishing.AQUATIC_BLOCKS.getStackFromEnum(Aquatic.TRAP)).setStock(10)
				.addTooltip("aquatic.trap"));
		BAITSHOP.addPurchasable(new PurchasableMaterials(
				0L,
				HFFishing.FLOATING_BLOCKS.getStackFromEnum(Floating.HATCHERY),
				Logs.of(8),
				String.of(6)) {
			@Override
			public boolean canList(@Nonnull World world, @Nonnull EntityPlayer player) {
				return HFApi.quests.hasCompleted(Quests.SELL_HATCHERY, player);
			}
		}.setStock(1).addTooltip("floating.hatchery"));


		//Selling things to the carpenter
		BAITSHOP.addPurchasable(-100, new ItemStack(Items.PRISMARINE_SHARD), 5);
		BAITSHOP.addPurchasable(-150, new ItemStack(Items.PRISMARINE_CRYSTALS), 3);
		BAITSHOP.addPurchasable(-10, new ItemStack(Items.FISH, 1, 0), 20);
		BAITSHOP.addPurchasable(-30, new ItemStack(Items.FISH, 1, 1), 15);
		BAITSHOP.addPurchasable(-50, new ItemStack(Items.FISH, 1, 2), 10);
		BAITSHOP.addPurchasable(-100, new ItemStack(Items.FISH, 1, 3), 5);
		for (Fish fish : Fish.values()) {
			long sell = (fish.getSellValue(fish.getLengthFromSizeOfFish(ItemFish.MEDIUM_FISH))) -
					fish.getSellValue(fish.getLengthFromSizeOfFish(
							ItemFish.MEDIUM_FISH)) % 10;
			BAITSHOP.addPurchasable(new PurchasableObtained(-sell, HFFishing.FISH.getStackFromEnum(fish)).setStock(100));
		}

		BAITSHOP.setSpecialRules(new SpecialRulesQuest(Quests.JACOB_MEET));
		BAITSHOP.addOpening(Weekday.TUESDAY, 13000, 19000).addOpening(Weekday.WEDNESDAY, 13000, 19000).addOpening(
				Weekday.THURSDAY,
				13000,
				19000).addOpening(
				Weekday.FRIDAY,
				13000,
				19000);
	}

	private static void registerTrader() {
		TRADER.addPurchasable(Material.COIN_GOLD.getSellValue(), HFMining.MATERIALS.getStackFromEnum(Material.COIN_GOLD));
		TRADER.addPurchasable(Material.COIN_SILVER.getSellValue(), HFMining.MATERIALS.getStackFromEnum(Material.COIN_SILVER));
		TRADER.addPurchasable(Material.COIN_COPPER.getSellValue(), HFMining.MATERIALS.getStackFromEnum(Material.COIN_COPPER));
		TRADER.addPurchasable(new PurchasableTrade(new ItemStack(Items.EGG), Sizeable.EGG));
		TRADER.addPurchasable(new PurchasableTrade(new ItemStack(Blocks.WOOL), Sizeable.WOOL));
		TRADER.addPurchasable(new PurchasableTrade(new ItemStack(Items.MILK_BUCKET), Sizeable.MILK));

		TRADER.addPurchasable(-60, new ItemStack(Items.MILK_BUCKET), 3);
		TRADER.addPurchasable(-30, new ItemStack(Items.EGG), 10);
		TRADER.addPurchasable(-150, new ItemStack(Items.LEATHER), 3);
		TRADER.addPurchasable(-50, new ItemStack(Items.FEATHER), 8);
		TRADER.addPurchasable(-80, new ItemStack(Items.RABBIT_HIDE), 5);
		TRADER.addPurchasable(-20, new ItemStack(Items.STRING), 10);
		for (int i = 0; i < 16; i++) {
			TRADER.addPurchasable(-150, new ItemStack(Blocks.WOOL, 1, 0), 3);
		}

		TRADER.addOpening(Weekday.MONDAY, 6000, 10000)
				.addOpening(Weekday.TUESDAY, 6000, 10000)
				.addOpening(Weekday.WEDNESDAY, 6000, 10000)
				.addOpening(
						Weekday.THURSDAY,
						6000,
						10000);
		TRADER.addOpening(Weekday.FRIDAY, 6000, 10000).addOpening(Weekday.SATURDAY, 6000, 10000).addOpening(Weekday.SUNDAY, 6000, 10000);
	}

	private static void registerCooking() {
		COOKING_FESTIVAL_FOOD.addPurchasable(new Purchasable(
				5000L,
				HFCore.STORAGE.getStackFromEnum(Storage.BASKET)).setNote(HFNotes.SHIPPING_BASKET));
		COOKING_FESTIVAL_FOOD.addPurchasable(50, new ItemStack(Items.FISH));
		COOKING_FESTIVAL_FOOD.addPurchasable(100, HFGathering.NATURE.getStackFromEnum(NaturalBlock.MINT));
		COOKING_FESTIVAL_FOOD.addPurchasable(150, HFGathering.NATURE.getStackFromEnum(NaturalBlock.CHAMOMILE));
		COOKING_FESTIVAL_FOOD.addPurchasable(200, HFGathering.NATURE.getStackFromEnum(NaturalBlock.LAVENDER));
		COOKING_FESTIVAL_FOOD.addPurchasable(700, HFGathering.NATURE.getStackFromEnum(NaturalBlock.MATSUTAKE));
		COOKING_FESTIVAL_FOOD.addPurchasable(150, new ItemStack(Blocks.BROWN_MUSHROOM));
		COOKING_FESTIVAL_FOOD.addPurchasable(300, HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.EGG, Size.SMALL));
		COOKING_FESTIVAL_FOOD.addPurchasable(500, HFAnimals.ANIMAL_PRODUCT.getStack(Sizeable.MILK, Size.SMALL));
		COOKING_FESTIVAL_FOOD.addPurchasable(HFCrops.WHEAT.getSellValue() * 4, new ItemStack(Items.BREAD));
		COOKING_FESTIVAL_FOOD.addPurchasable(new PurchasableCrop(HFCrops.CUCUMBER));
		COOKING_FESTIVAL_FOOD.addPurchasable(new PurchasableCrop(HFCrops.TOMATO));
		COOKING_FESTIVAL_FOOD.addPurchasable(new PurchasableCrop(HFCrops.ONION));
		COOKING_FESTIVAL_FOOD.addPurchasable(new PurchasableCrop(HFCrops.CORN));
		COOKING_FESTIVAL_FOOD.addPurchasable(new PurchasableCrop(HFCrops.EGGPLANT));
		COOKING_FESTIVAL_FOOD.addPurchasable(new PurchasableCrop(HFCrops.SPINACH));
		COOKING_FESTIVAL_FOOD.addPurchasable(new PurchasableCrop(HFCrops.SWEET_POTATO));
	}

	private static void registerRecipes() {
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("juice_vegetable")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("sushi")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("sashimi")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("sashimi_chirashi")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("cucumber_pickled")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("juice_tomato")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("cornflakes")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("ketchup")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("stew_pumpkin")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("rice_fried")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("doria")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("juice_fruit")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("salad_herb")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("soup_herb")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("sandwich_herb")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("sweet_potatoes")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("eggplant_happy")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("sandwich")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("sandwich_fruit")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("latte_fruit")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("spinach_boiled")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("riceballs_toasted")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("omelet")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("egg_boiled")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("egg_overrice")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("juice_mix")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("pancake")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("rice_matsutake")));
		COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id("rice_mushroom")));

		//Add all the recipes the player has learnt from friendship to the list
		for (Quest quest : Quest.REGISTRY) {
			if (quest instanceof QuestRecipe) {
				for (java.lang.String recipe : ((QuestRecipe) quest).recipe) {
					COOKING_FESTIVAL_RECIPES.addPurchasable(new PurchasableRecipe(HarvestFestival.id(recipe)) {
						@Override
						public boolean canList(@Nonnull World world, @Nonnull EntityPlayer player) {
							return HFApi.quests.hasCompleted(quest, player);
						}
					}.setStock(1));
				}
			}
		}
	}

	private static void registerCowBargains() {
		COW_FESTIVAL_BARGAINS.addPurchasable(100, HFAnimals.TREATS.getStackFromEnum(Treat.COW, 16));
		COW_FESTIVAL_BARGAINS.addPurchasable(500, HFCrops.GRASS.getCropStack(16));
		COW_FESTIVAL_BARGAINS.addPurchasable(1000, HFAnimals.TROUGH.getStackFromEnum(Trough.WOOD, 3));
		COW_FESTIVAL_BARGAINS.addPurchasable(1000, HFAnimals.TOOLS.getStackFromEnum(Tool.MEDICINE, 2));
		COW_FESTIVAL_BARGAINS.addPurchasable(1000, HFAnimals.TOOLS.getStackFromEnum(Tool.MIRACLE_POTION));
		COW_FESTIVAL_BARGAINS.addPurchasable(500, HFAnimals.TOOLS.getStackFromEnum(Tool.BRUSH));
		COW_FESTIVAL_BARGAINS.addPurchasable(1000, HFAnimals.TOOLS.getStackFromEnum(Tool.MILKER));
		COW_FESTIVAL_BARGAINS.addPurchasable(100, new ItemStack(Items.NAME_TAG));
	}

	private static void registerDairyQueen() {
		COW_FESTIVAL_DAIRY_QUEEN.addPurchasable(new PurchasableMeal(150, HarvestFestival.id("ice_cream")).setStock(20));
		COW_FESTIVAL_DAIRY_QUEEN.addPurchasable(new PurchasableMeal(100, HarvestFestival.id("milk_hot")).setStock(20));
		COW_FESTIVAL_DAIRY_QUEEN.addPurchasable(new PurchasableMeal(120, HarvestFestival.id("chocolate_hot")).setStock(10));
		COW_FESTIVAL_DAIRY_QUEEN.addPurchasable(new PurchasableMeal(50, HarvestFestival.id("cornflakes")).setStock(10));
		COW_FESTIVAL_DAIRY_QUEEN.addPurchasable(new PurchasableMeal(200, HarvestFestival.id("dinnerroll")).setStock(5));
		COW_FESTIVAL_DAIRY_QUEEN.addPurchasable(new PurchasableMeal(150, HarvestFestival.id("bun_jam")).setStock(5));
		COW_FESTIVAL_DAIRY_QUEEN.addPurchasable(new PurchasableMeal(300, HarvestFestival.id("doughnut")).setStock(5));
		COW_FESTIVAL_DAIRY_QUEEN.addPurchasable(new PurchasableMeal(140, HarvestFestival.id("milk_strawberry")).setStock(8));
	}

	private static void registerSaleSheep() {
		SHEEP_FESTIVAL_SALE.addPurchasable(100, HFAnimals.TREATS.getStackFromEnum(Treat.SHEEP, 16));
		SHEEP_FESTIVAL_SALE.addPurchasable(500, HFCrops.GRASS.getCropStack(16));
		SHEEP_FESTIVAL_SALE.addPurchasable(1000, HFAnimals.TROUGH.getStackFromEnum(Trough.WOOD, 3));
		SHEEP_FESTIVAL_SALE.addPurchasable(1000, HFAnimals.TOOLS.getStackFromEnum(Tool.MEDICINE, 2));
		SHEEP_FESTIVAL_SALE.addPurchasable(1000, HFAnimals.TOOLS.getStackFromEnum(Tool.MIRACLE_POTION));
		SHEEP_FESTIVAL_SALE.addPurchasable(500, HFAnimals.TOOLS.getStackFromEnum(Tool.BRUSH));
		SHEEP_FESTIVAL_SALE.addPurchasable(1000, new ItemStack(Items.SHEARS));
		SHEEP_FESTIVAL_SALE.addPurchasable(100, new ItemStack(Items.NAME_TAG));
	}

	@Nonnull
	public static ItemStack getWoolyArmor(Item item, java.lang.String name) {
		ItemStack stack = new ItemStack(item);
		stack.setStackDisplayName(name);
		((ItemArmor) item).setColor(stack, 0xFFFFFF);
		return stack;
	}

	private static void registerGrannysKnits() {
		SHEEP_FESTIVAL_KNITTNG.addPurchasable(50, new ItemStack(Items.STRING), 16);
		SHEEP_FESTIVAL_KNITTNG.addPurchasable(200, new ItemStack(Blocks.WOOL), 16);
		SHEEP_FESTIVAL_KNITTNG.addPurchasable(500, getWoolyArmor(Items.LEATHER_HELMET, "Wooly Hat"), 1);
		SHEEP_FESTIVAL_KNITTNG.addPurchasable(800, getWoolyArmor(Items.LEATHER_CHESTPLATE, "Wooly Coat"), 1);
		SHEEP_FESTIVAL_KNITTNG.addPurchasable(700, getWoolyArmor(Items.LEATHER_LEGGINGS, "Wooly Pants"), 1);
		SHEEP_FESTIVAL_KNITTNG.addPurchasable(400, getWoolyArmor(Items.LEATHER_BOOTS, "Wooly Boots"), 1);
		SHEEP_FESTIVAL_KNITTNG.addPurchasable(300, new ItemStack(Items.COOKED_MUTTON), 20);
	}

	private static void registerMinecraftFriedChicken() {
		CHICKEN_FESTIVAL_MFC.addPurchasable(250, new ItemStack(Items.COOKED_CHICKEN), 20);
		CHICKEN_FESTIVAL_MFC.addPurchasable(new PurchasableMeal(150, HarvestFestival.id("fries_french")).setStock(20));
		CHICKEN_FESTIVAL_MFC.addPurchasable(new PurchasableMeal(0, HarvestFestival.id("ketchup")).setStock(20));
		CHICKEN_FESTIVAL_MFC.addPurchasable(new PurchasableMeal(50, HarvestFestival.id("egg_boiled")).setStock(5));
		CHICKEN_FESTIVAL_MFC.addPurchasable(new PurchasableMeal(100, HarvestFestival.id("egg_scrambled")).setStock(5));
		CHICKEN_FESTIVAL_MFC.addPurchasable(new PurchasableMeal(200, HarvestFestival.id("juice_orange")).setStock(8));
		CHICKEN_FESTIVAL_MFC.addPurchasable(new PurchasableMeal(100, HarvestFestival.id("juice_apple")).setStock(8));
	}

	private static Shop newHolidayShop(ResourceLocation resource, @Nullable NPC npc, Festival festival) {
		Shop shop = new Shop(resource).setOpensOnHolidays();
		if (npc != null) {
			if (npc instanceof NPCHolidayStore) {
				((NPCHolidayStore) npc).addHolidayShop(festival, shop);
			} else if (npc instanceof NPCHolidayStoreSpecial) {
				((NPCHolidayStoreSpecial) npc).addHolidayShop(festival, shop);
			}
		}

		return shop.addOpening(Weekday.MONDAY, 6000, 18000).addOpening(Weekday.TUESDAY, 6000, 18000).addOpening(
				Weekday.WEDNESDAY,
				6000,
				18000).addOpening(
				Weekday.THURSDAY,
				6000,
				18000).addOpening(Weekday.FRIDAY, 6000, 18000).addOpening(Weekday.SATURDAY, 6000, 18000).addOpening(
				Weekday.SUNDAY,
				6000,
				18000);
	}

	private static Shop newShop(ResourceLocation resource, @Nullable NPC npc) {
		Shop shop = new Shop(resource);
		if (npc != null) {
			npc.setShop(shop);
		}

		return shop;
	}

	public static boolean TWENTY_FOUR_HOUR_SHOPPING;

	public static void configure() {
		TWENTY_FOUR_HOUR_SHOPPING = ConfigHelper.getBoolean("Shops are open all the time", false);
	}
}