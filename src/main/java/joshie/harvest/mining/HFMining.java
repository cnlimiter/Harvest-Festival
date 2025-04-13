package joshie.harvest.mining;

import joshie.harvest.HarvestFestival;
import joshie.harvest.animals.render.ModelHarvestChicken;
import joshie.harvest.animals.render.ModelHarvestCow;
import joshie.harvest.animals.render.ModelHarvestSheep;
import joshie.harvest.api.HFApi;
import joshie.harvest.api.calendar.Season;
import joshie.harvest.api.mining.MiningContext;
import joshie.harvest.core.HFCore;
import joshie.harvest.core.HFTab;
import joshie.harvest.core.block.BlockFlower;
import joshie.harvest.core.helpers.ConfigHelper;
import joshie.harvest.core.helpers.RegistryHelper;
import joshie.harvest.core.lib.EntityIDs;
import joshie.harvest.core.util.annotations.HFLoader;
import joshie.harvest.mining.block.BlockDirt;
import joshie.harvest.mining.block.BlockElevator;
import joshie.harvest.mining.block.BlockLadder;
import joshie.harvest.mining.block.BlockOre;
import joshie.harvest.mining.block.BlockOre.Ore;
import joshie.harvest.mining.block.BlockPortal;
import joshie.harvest.mining.block.BlockStone;
import joshie.harvest.mining.entity.EntityDarkChick;
import joshie.harvest.mining.entity.EntityDarkChicken;
import joshie.harvest.mining.entity.EntityDarkCow;
import joshie.harvest.mining.entity.EntityDarkSheep;
import joshie.harvest.mining.gen.MiningProvider;
import joshie.harvest.mining.item.ItemDarkDrop;
import joshie.harvest.mining.item.ItemDarkDrop.DarkDrop;
import joshie.harvest.mining.item.ItemDarkSpawner;
import joshie.harvest.mining.item.ItemDarkSpawner.DarkSpawner;
import joshie.harvest.mining.item.ItemMaterial;
import joshie.harvest.mining.item.ItemMaterial.Material;
import joshie.harvest.mining.item.ItemMiningTool;
import joshie.harvest.mining.loot.Between;
import joshie.harvest.mining.loot.Between100;
import joshie.harvest.mining.loot.EndsIn;
import joshie.harvest.mining.loot.Exact;
import joshie.harvest.mining.loot.From;
import joshie.harvest.mining.loot.MultipleOf;
import joshie.harvest.mining.loot.Obtained;
import joshie.harvest.mining.loot.Seasonal;
import joshie.harvest.mining.render.BakedDirt;
import joshie.harvest.mining.render.FakeAnimalsItemRenderer;
import joshie.harvest.mining.render.RenderDarkChick;
import joshie.harvest.mining.render.RenderDarkChicken;
import joshie.harvest.mining.render.RenderDarkCow;
import joshie.harvest.mining.render.RenderDarkSheep;
import joshie.harvest.mining.tile.TileElevator;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@HFLoader
@SuppressWarnings("unused")
public class HFMining {
	public static final BlockOre ORE = new BlockOre().register("ore");
	public static final BlockStone STONE = new BlockStone().register("stone");
	public static final BlockDirt DIRT = new BlockDirt().setBlockUnbreakable().setResistance(6000000.0F).register("dirt");
	public static final BlockDirt DIRT_DECORATIVE = new BlockDirt().setHardness(0.5F).register("dirt_decorative");
	public static final BlockLadder LADDER = new BlockLadder().register("ladder");
	public static final BlockPortal PORTAL = new BlockPortal().setBlockUnbreakable().register("portal");
	public static final BlockElevator ELEVATOR = new BlockElevator().setBlockUnbreakable().register("elevator");
	public static final ItemMaterial MATERIALS = new ItemMaterial().register("materials");
	public static final ItemDarkSpawner DARK_SPAWNER = new ItemDarkSpawner().register("dark_spawner");
	public static final ItemDarkDrop DARK_DROP = new ItemDarkDrop().register("dark_drop");
	public static final ItemMiningTool MINING_TOOL = new ItemMiningTool().register("tool_mining");
	public static DimensionType MINE_WORLD;

	public static void preInit() {
		MINE_WORLD = DimensionType.register("The Mine", "_hf_mine", MINING_ID, MiningProvider.class, false);
		DimensionManager.registerDimension(MINING_ID, MINE_WORLD);
		EntityRegistry.registerModEntity(
				HarvestFestival.id("dark_cow"),
				EntityDarkCow.class,
				"dark_cow",
				EntityIDs.DARK_COW,
				HarvestFestival.instance,
				80,
				3,
				true);
		EntityRegistry.registerModEntity(
				HarvestFestival.id("dark_sheep"),
				EntityDarkSheep.class,
				"dark_sheep",
				EntityIDs.DARK_SHEEP,
				HarvestFestival.instance,
				80,
				3,
				true);
		EntityRegistry.registerModEntity(
				HarvestFestival.id("dark_chicken"),
				EntityDarkChicken.class,
				"dark_chicken",
				EntityIDs.DARK_CHICKEN,
				HarvestFestival.instance,
				80,
				3,
				true);
		EntityRegistry.registerModEntity(
				HarvestFestival.id("dark_chick"),
				EntityDarkChick.class,
				"dark_chick",
				EntityIDs.DARK_CHICK,
				HarvestFestival.instance,
				80,
				3,
				true);
		OreDictionary.registerOre("feather", DARK_DROP.getStackFromEnum(DarkDrop.FEATHER));
		OreDictionary.registerOre("leather", DARK_DROP.getStackFromEnum(DarkDrop.LEATHER));
		RegistryHelper.registerTiles(TileElevator.class);
		LootConditionManager.registerCondition(new From.Serializer());
		LootConditionManager.registerCondition(new Between.Serializer());
		LootConditionManager.registerCondition(new Between100.Serializer());
		LootConditionManager.registerCondition(new EndsIn.Serializer());
		LootConditionManager.registerCondition(new Exact.Serializer());
		LootConditionManager.registerCondition(new MultipleOf.Serializer());
		LootConditionManager.registerCondition(new Obtained.Serializer());
		LootConditionManager.registerCondition(new Seasonal.Serializer());
		RegistryHelper.registerOreIfNotExists("gemRuby", MATERIALS.getStackFromEnum(Material.RUBY));
		RegistryHelper.registerOreIfNotExists("gemTopaz", MATERIALS.getStackFromEnum(Material.TOPAZ));
		RegistryHelper.registerOreIfNotExists("gemAmethyst", MATERIALS.getStackFromEnum(Material.AMETHYST));
		registerSellable(Items.DIAMOND, 100L);
		registerSellable(Items.EMERALD, 80L);
	}

	@SideOnly(Side.CLIENT)
	public static void preInitClient() {
		ModelLoader.setCustomStateMapper(DIRT, new BakedDirt.StateMapper());
		ModelLoader.setCustomStateMapper(DIRT_DECORATIVE, new BakedDirt.StateMapper());
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkCow.class, RenderDarkCow::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkSheep.class, RenderDarkSheep::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkChicken.class, RenderDarkChicken::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkChick.class, RenderDarkChick::new);

		//Register the dark spawner
		RegistryHelper.registerEntityRenderer(DARK_SPAWNER, FakeAnimalsItemRenderer.INSTANCE);
		FakeAnimalsItemRenderer.INSTANCE.register(DarkSpawner.COW, "dark_cow", new ModelHarvestCow.Adult());
		FakeAnimalsItemRenderer.INSTANCE.register(DarkSpawner.SHEEP, "dark_sheep", new ModelHarvestSheep.Wooly());
		FakeAnimalsItemRenderer.INSTANCE.register(DarkSpawner.CHICKEN, "dark_chicken", new ModelHarvestChicken.Adult());
		FakeAnimalsItemRenderer.INSTANCE.register(DarkSpawner.CHICK, "dark_chick", new ModelHarvestChicken.Child());
	}

	public static void init() {
		HFApi.npc.getGifts().addToBlacklist(DARK_SPAWNER, MINING_TOOL, DIRT, DIRT_DECORATIVE, STONE, LADDER, PORTAL, ORE);
		//Spring, Summer, Autumn values
		MiningContext copper = new MiningContext(MiningHelper.COPPER_FLOOR);
		MiningContext silver = new MiningContext(MiningHelper.SILVER_FLOOR);
		MiningContext gold = new MiningContext(MiningHelper.GOLD_FLOOR);
		MiningContext mystril = new MiningContext(MiningHelper.MYSTRIL_FLOOR);
		HFApi.mining.registerOre(
				copper, HFCore.FLOWERS.getStateFromEnum(BlockFlower.FlowerType.WEED), 40D,
				Season.SPRING,
				Season.SUMMER,
				Season.AUTUMN);
		HFApi.mining.registerOre(
				copper, ORE.getStateFromEnum(Ore.ROCK), 100D,
				Season.SPRING, Season.SUMMER, Season.AUTUMN);
		HFApi.mining.registerOre(
				copper, ORE.getStateFromEnum(Ore.COPPER), 6D,
				Season.SPRING, Season.SUMMER, Season.AUTUMN);
		HFApi.mining.registerOre(
				copper, ORE.getStateFromEnum(Ore.AMETHYST), 6D,
				Season.SPRING, Season.SUMMER, Season.AUTUMN);
		HFApi.mining.registerOre(
				silver, ORE.getStateFromEnum(Ore.SILVER), 7D,
				Season.SPRING, Season.SUMMER, Season.AUTUMN);
		HFApi.mining.registerOre(
				silver, ORE.getStateFromEnum(Ore.TOPAZ), 5D,
				Season.SPRING, Season.SUMMER, Season.AUTUMN);
		HFApi.mining.registerOre(
				gold, ORE.getStateFromEnum(Ore.GOLD), 8D,
				Season.SPRING, Season.SUMMER, Season.AUTUMN);
		HFApi.mining.registerOre(
				gold, ORE.getStateFromEnum(Ore.RUBY), 6D,
				Season.SPRING, Season.SUMMER, Season.AUTUMN);
		HFApi.mining.registerOre(
				gold, ORE.getStateFromEnum(Ore.JADE), 5D,
				Season.SPRING, Season.SUMMER, Season.AUTUMN);
		HFApi.mining.registerOre(
				mystril, ORE.getStateFromEnum(Ore.EMERALD), 4D,
				Season.SPRING, Season.SUMMER, Season.AUTUMN);
		HFApi.mining.registerOre(
				mystril, ORE.getStateFromEnum(Ore.MYSTRIL), 5D,
				Season.SPRING, Season.SUMMER, Season.AUTUMN);
		HFApi.mining.registerOre(
				mystril, ORE.getStateFromEnum(Ore.DIAMOND), 3D,
				Season.SPRING, Season.SUMMER, Season.AUTUMN);

		//Winter values
		HFApi.mining.registerOre(copper, ORE.getStateFromEnum(Ore.ROCK), 110D, Season.WINTER);
		HFApi.mining.registerOre(copper, ORE.getStateFromEnum(Ore.COPPER), 9D, Season.WINTER);
		HFApi.mining.registerOre(copper, ORE.getStateFromEnum(Ore.GEM), 5D, Season.WINTER);
		HFApi.mining.registerOre(copper, ORE.getStateFromEnum(Ore.AMETHYST), 6D, Season.WINTER);
		HFApi.mining.registerOre(silver, ORE.getStateFromEnum(Ore.SILVER), 10D, Season.WINTER);
		HFApi.mining.registerOre(silver, ORE.getStateFromEnum(Ore.TOPAZ), 6D, Season.WINTER);
		HFApi.mining.registerOre(gold, ORE.getStateFromEnum(Ore.GOLD), 10D, Season.WINTER);
		HFApi.mining.registerOre(gold, ORE.getStateFromEnum(Ore.RUBY), 7D, Season.WINTER);
		HFApi.mining.registerOre(mystril, ORE.getStateFromEnum(Ore.JADE), 5D, Season.WINTER);
		HFApi.mining.registerOre(mystril, ORE.getStateFromEnum(Ore.EMERALD), 4D, Season.WINTER);
		HFApi.mining.registerOre(mystril, ORE.getStateFromEnum(Ore.MYSTRIL), 5D, Season.WINTER);
		HFApi.mining.registerOre(mystril, ORE.getStateFromEnum(Ore.DIAMOND), 3D, Season.WINTER);
	}

	private static void registerSellable(Item item, long value) {
		HFApi.shipping.registerSellable(new ItemStack(item), value);
		item.setCreativeTab(HFTab.MINING);
	}

	public static int MINING_ID;
	public static boolean ANIMALS_ON_EVERY_FLOOR;

	public static void configure() {
		MINING_ID = ConfigHelper.getInteger("Mining world ID", 4);
		ANIMALS_ON_EVERY_FLOOR = ConfigHelper.getBoolean("Spawn dark animals on every floor instead of hordes", false);
	}
}