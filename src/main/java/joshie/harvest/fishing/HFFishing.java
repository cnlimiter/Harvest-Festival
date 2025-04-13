package joshie.harvest.fishing;

import java.util.EnumMap;
import java.util.Locale;

import org.apache.commons.lang3.tuple.Pair;

import joshie.harvest.HarvestFestival;
import joshie.harvest.api.HFApi;
import joshie.harvest.api.calendar.Season;
import joshie.harvest.api.core.ITiered.ToolTier;
import joshie.harvest.api.core.Ore;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.core.helpers.RegistryHelper;
import joshie.harvest.core.lib.EntityIDs;
import joshie.harvest.core.util.annotations.HFLoader;
import joshie.harvest.fishing.FishingHelper.WaterType;
import joshie.harvest.fishing.block.BlockFishTrap;
import joshie.harvest.fishing.block.BlockFloating;
import joshie.harvest.fishing.entity.EntityFishHookHF;
import joshie.harvest.fishing.item.ItemFish;
import joshie.harvest.fishing.item.ItemFish.Fish;
import joshie.harvest.fishing.item.ItemFishingRod;
import joshie.harvest.fishing.item.ItemJunk;
import joshie.harvest.fishing.item.ItemJunk.Junk;
import joshie.harvest.fishing.loot.ConditionTier;
import joshie.harvest.fishing.loot.ConditionTime;
import joshie.harvest.fishing.loot.SetWeight;
import joshie.harvest.fishing.render.SpecialRendererHatchery;
import joshie.harvest.fishing.render.SpecialRendererTrap;
import joshie.harvest.fishing.tile.TileHatchery;
import joshie.harvest.fishing.tile.TileTrap;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFishFood.FishType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@HFLoader
@SuppressWarnings("unused")
public class HFFishing {
	public static final EnumMap<ToolTier, ItemFishingRod> FISHING_RODS = new EnumMap<>(ToolTier.class);
	public static final ItemFish FISH = new ItemFish().register("fish");
	public static final ItemJunk JUNK = new ItemJunk().register("junk");
	public static final BlockFishTrap AQUATIC_BLOCKS = new BlockFishTrap().register("aquatic");
	public static final BlockFloating FLOATING_BLOCKS = new BlockFloating().register("floating");

	static {
		for (ToolTier tier : ToolTier.values()) {
			FISHING_RODS.put(tier, new ItemFishingRod(tier).register("fishing_rod_" + tier.name().toLowerCase(Locale.ENGLISH)));
		}
	}

	@SuppressWarnings("ConstantConditions")
	public static void preInit() {
		LootFunctionManager.registerFunction(new SetWeight.Serializer());
		LootConditionManager.registerCondition(new ConditionTime.Serializer());
		LootConditionManager.registerCondition(new ConditionTier.Serializer());
		EntityRegistry.registerModEntity(
				HarvestFestival.id("hook"),
				EntityFishHookHF.class,
				"hook",
				EntityIDs.FISHING,
				HarvestFestival.instance,
				64,
				5,
				true);
		EntityRegistry.instance().lookupModSpawn(EntityFishHookHF.class, false).setCustomSpawning(null, true);
		HFApi.shipping.registerSellable(new ItemStack(Items.FISH, 1, 0), 10L);
		HFApi.shipping.registerSellable(new ItemStack(Items.FISH, 1, 1), 30L);
		HFApi.shipping.registerSellable(new ItemStack(Items.FISH, 1, 2), 50L);
		HFApi.shipping.registerSellable(new ItemStack(Items.FISH, 1, 3), 100L);
		HFApi.shipping.registerSellable(new ItemStack(Items.COOKED_FISH, 1, 0), (long) (10 * HFCooking.COOKING_SELL_MODIFIER));
		HFApi.shipping.registerSellable(new ItemStack(Items.COOKED_FISH, 1, 1), (long) (30 * HFCooking.COOKING_SELL_MODIFIER));
		HFApi.fishing.registerBait(JUNK.getStackFromEnum(Junk.BAIT));
		RegistryHelper.registerTiles(TileTrap.class, TileHatchery.class);

		FishingAPI.INSTANCE.breeding.register(Ore.of("fish"), 3);
		//Register vanilla fish
		for (FishType fish : FishType.values()) {
			RegistryHelper.registerOreIfNotExists("fish", new ItemStack(Items.FISH, 1, fish.getMetadata()));
		}

		//Register my fish
		for (Fish fish : Fish.values()) {
			RegistryHelper.registerOreIfNotExists("fish", FISH.getStackFromEnum(fish));
		}
	}

	public static void init() {
		for (ToolTier tier : ToolTier.values()) {
			HFApi.npc.getGifts().addToBlacklist(FISHING_RODS.get(tier));
		}

		registerLootTable("lake_spring", WaterType.LAKE, Season.SPRING);
		registerLootTable("lake_summer", WaterType.LAKE, Season.SUMMER);
		registerLootTable("lake_autumn", WaterType.LAKE, Season.AUTUMN);
		registerLootTable("lake_winter", WaterType.LAKE, Season.WINTER);
		registerLootTable("ocean_spring", WaterType.OCEAN, Season.SPRING);
		registerLootTable("ocean_summer", WaterType.OCEAN, Season.SUMMER);
		registerLootTable("ocean_autumn", WaterType.OCEAN, Season.AUTUMN);
		registerLootTable("ocean_winter", WaterType.OCEAN, Season.WINTER);
		registerLootTable("pond_spring", WaterType.POND, Season.SPRING);
		registerLootTable("pond_summer", WaterType.POND, Season.SUMMER);
		registerLootTable("pond_autumn", WaterType.POND, Season.AUTUMN);
		registerLootTable("pond_winter", WaterType.POND, Season.WINTER);
		registerLootTable("river_spring", WaterType.RIVER, Season.SPRING);
		registerLootTable("river_summer", FishingHelper.WaterType.RIVER, Season.SUMMER);
		registerLootTable("river_autumn", FishingHelper.WaterType.RIVER, Season.AUTUMN);
		registerLootTable("river_winter", FishingHelper.WaterType.RIVER, Season.WINTER);
	}

	@SideOnly(Side.CLIENT)
	public static void initClient() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileHatchery.class, new SpecialRendererHatchery());
		ClientRegistry.bindTileEntitySpecialRenderer(TileTrap.class, new SpecialRendererTrap());
	}

	private static void registerLootTable(String id, WaterType type, Season season) {
		FishingHelper.FISHING_LOOT.put(
				Pair.of(season, type),
				LootTableList.register(HarvestFestival.id("gameplay/fishing/" + id)));
	}
}
