package joshie.harvest.knowledge;

import joshie.harvest.animals.HFAnimals;
import joshie.harvest.animals.item.ItemAnimalSpawner.Spawner;
import joshie.harvest.animals.item.ItemAnimalTreat.Treat;
import joshie.harvest.api.core.ITiered.ToolTier;
import joshie.harvest.api.knowledge.Category;
import joshie.harvest.api.knowledge.Note;
import joshie.harvest.buildings.HFBuildings;
import joshie.harvest.cooking.CookingHelper;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.block.BlockCookware.Cookware;
import joshie.harvest.cooking.item.ItemMeal.Meal;
import joshie.harvest.core.HFCore;
import joshie.harvest.core.block.BlockStorage.Storage;
import joshie.harvest.core.lib.HFModInfo;
import joshie.harvest.core.util.annotations.HFLoader;
import joshie.harvest.knowledge.gui.stats.notes.render.NoteRenderCursedTools;
import joshie.harvest.knowledge.gui.stats.notes.render.NoteRenderRepairing;
import joshie.harvest.knowledge.gui.stats.notes.render.NoteRenderUpgrading;
import joshie.harvest.mining.HFMining;
import joshie.harvest.mining.block.BlockElevator.Elevator;
import joshie.harvest.mining.block.BlockOre.Ore;
import joshie.harvest.tools.HFTools;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@HFLoader
@SuppressWarnings("unused")
public class HFNotes {
	public static final Note BLUEPRINTS = registerNote(Category.TOWNSHIP, "blueprints");
	public static final Note SHOPPING = registerNote(Category.TOWNSHIP, "shops");
	public static final Note CROP_FARMING = registerNote(Category.FARMING, "farming");
	public static final Note SICKLE = registerNote(Category.FARMING, "sickle");
	public static final Note SHIPPING = registerNote(Category.TOWNSHIP, "shipping");
	public static final Note MAILBOX = registerNote(Category.TOWNSHIP, "mailbox");
	public static final Note SUPERMARKET = registerNote(Category.TOWNSHIP, "supermarket");
	public static final Note CHICKEN_CARE = registerNote(Category.FARMING, "care.chickens");
	public static final Note COW_CARE = registerNote(Category.FARMING, "care.cows");
	public static final Note SHEEP_CARE = registerNote(Category.FARMING, "care.sheep");
	public static final Note ANIMAL_HAPPINESS = registerNote(Category.FARMING, "care.happiness");
	public static final Note ANIMAL_STRESS = registerNote(Category.FARMING, "care.stress");

	public static final Note HAMMER = registerNote(Category.ACTIVITIES, "hammer");
	public static final Note AXE = registerNote(Category.ACTIVITIES, "axe");
	public static final Note MINING = registerNote(Category.ACTIVITIES, "mining");
	public static final Note UPGRADING = registerNote(Category.TOWNSHIP, "upgrading");
	public static final Note COOKING = registerNote(Category.ACTIVITIES, "cooking");
	public static final Note RECIPES = registerNote(Category.ACTIVITIES, "recipes");
	public static final Note RECIPE_BOOK = registerNote(Category.ACTIVITIES, "recipebook");
	public static final Note KITCHEN_COUNTER = registerNote(Category.ACTIVITIES, "counter");
	public static final Note FRIDGE = registerNote(Category.ACTIVITIES, "fridge");
	public static final Note OVEN = registerNote(Category.ACTIVITIES, "oven");
	public static final Note MIXER = registerNote(Category.ACTIVITIES, "mixer");
	public static final Note POTPAN = registerNote(Category.ACTIVITIES, "potpan");
	public static final Note ELEVATOR = registerNote(Category.ACTIVITIES, "elevator");
	public static final Note SHIPPING_BASKET = registerNote(Category.TOWNSHIP, "shipping.basket");
	public static final Note TREES = registerNote(Category.FARMING, "trees");

	public static final Note REPAIRING = registerNote(Category.TOWNSHIP, "repairing");
	public static final Note SECRET_CURSED_TOOLS = registerNote(Category.ACTIVITIES, "secret.cursed").setSecretNote();
	public static final Note SECRET_CHICKENS = registerNote(Category.FARMING, "secret.chickens").setSecretNote();
	public static final Note SECRET_RELATIONSHIPS = registerNote(Category.FARMING, "secret.relationships").setSecretNote();
	public static final Note SECRET_LIVESTOCK = registerNote(Category.FARMING, "secret.livestock").setSecretNote();

	@SideOnly(Side.CLIENT)
	public static void preInitClient() {
		UPGRADING.setRender(new NoteRenderUpgrading());
		REPAIRING.setRender(new NoteRenderRepairing());
		SECRET_CURSED_TOOLS.setRender(new NoteRenderCursedTools());
	}

	@SideOnly(Side.CLIENT)
	public static void postInitClient() {
		BLUEPRINTS.setIcon(HFBuildings.BLUEPRINTS.getStackFromObject(HFBuildings.CARPENTER));
		CROP_FARMING.setIcon(new ItemStack(Items.CARROT));
		SHIPPING.setIcon(HFCore.STORAGE.getStackFromEnum(Storage.SHIPPING));
		MAILBOX.setIcon(HFCore.STORAGE.getStackFromEnum(Storage.MAILBOX));
		CHICKEN_CARE.setIcon(HFAnimals.ANIMAL.getStackFromEnum(Spawner.CHICKEN));
		COW_CARE.setIcon(HFAnimals.ANIMAL.getStackFromEnum(Spawner.COW));
		SHEEP_CARE.setIcon(HFAnimals.ANIMAL.getStackFromEnum(Spawner.SHEEP));
		HAMMER.setIcon(HFTools.HAMMERS.get(ToolTier.BASIC).getStack());
		AXE.setIcon(HFTools.AXES.get(ToolTier.BASIC).getStack());
		SICKLE.setIcon(HFTools.SICKLES.get(ToolTier.BASIC).getStack());
		MINING.setIcon(HFMining.ORE.getStackFromEnum(Ore.COPPER));
		COOKING.setIcon(HFCooking.MEAL.getStackFromEnum(Meal.SALAD));
		RECIPES.setIcon(CookingHelper.getRecipe("salad"));
		RECIPE_BOOK.setIcon(new ItemStack(HFCooking.COOKBOOK));
		KITCHEN_COUNTER.setIcon(HFCooking.COOKWARE.getStackFromEnum(Cookware.COUNTER));
		FRIDGE.setIcon(HFCooking.COOKWARE.getStackFromEnum(Cookware.FRIDGE));
		OVEN.setIcon(HFCooking.COOKWARE.getStackFromEnum(Cookware.OVEN_OFF));
		MIXER.setIcon(HFCooking.COOKWARE.getStackFromEnum(Cookware.MIXER));
		POTPAN.setIcon(HFCooking.COOKWARE.getStackFromEnum(Cookware.POT));
		ELEVATOR.setIcon(HFMining.ELEVATOR.getStackFromEnum(Elevator.JUNK));
		SUPERMARKET.setIcon(HFBuildings.STRUCTURES.getStackFromObject(HFBuildings.SUPERMARKET));
		REPAIRING.setIcon(new ItemStack(Blocks.ANVIL));
		UPGRADING.setIcon(HFModInfo.ICONS, 0, 48);
		SECRET_CURSED_TOOLS.setIcon(HFTools.SICKLES.get(ToolTier.CURSED).getStack());
		SECRET_LIVESTOCK.setIcon(HFAnimals.TREATS.getStackFromEnum(Treat.COW));
		SECRET_CHICKENS.setIcon(HFAnimals.TREATS.getStackFromEnum(Treat.CHICKEN));
		SHIPPING_BASKET.setIcon(HFCore.STORAGE.getStackFromEnum(Storage.BASKET));
		TREES.setIcon(HFModInfo.ICONS, 0, 64);
	}

	public static Note registerNote(Category category, String name) {
		return new Note(category, new ResourceLocation(HFModInfo.MODID, name));
	}
}
