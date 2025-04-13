package joshie.harvest.npcs.gift.init;

import joshie.harvest.api.core.MatchType;
import joshie.harvest.api.core.Ore;
import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.core.util.annotations.HFLoader;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

@HFLoader(priority = 0)
public class HFGiftsVanillaBlocks extends HFGiftsAbstract {
	public static void init() {
		assignGeneric(Ore.of("stone").setType(MatchType.PREFIX), GiftCategory.BUILDING);
		assignGeneric(Ore.of("grass"), GiftCategory.JUNK);
		assignGeneric(Ore.of("dirt"), GiftCategory.JUNK);
		assignGeneric(Ore.of("cobblestone"), GiftCategory.BUILDING);
		assignGeneric(Ore.of("plank").setType(MatchType.PREFIX), GiftCategory.BUILDING);
		assignGeneric(Ore.of("sand"), GiftCategory.JUNK);
		assignGeneric(Ore.of("gravel"), GiftCategory.JUNK);
		assignGeneric(Ore.of("oreGold"), GiftCategory.MINERAL);
		assignGeneric(Ore.of("oreIron"), GiftCategory.MINERAL);
		assignGeneric(Ore.of("oreCoal"), GiftCategory.MINERAL);
		assignGeneric(Ore.of("log").setType(MatchType.PREFIX), GiftCategory.BUILDING);
		assignGeneric(Ore.of("tree").setType(MatchType.PREFIX), GiftCategory.PLANT);
		assignGeneric(Blocks.SPONGE, GiftCategory.FISH);
		assignGeneric(Ore.of("blockGlass").setType(MatchType.PREFIX), GiftCategory.BUILDING);
		assignGeneric(Ore.of("oreLapis"), GiftCategory.MINERAL);
		assignGeneric(Ore.of("blockLapis"), GiftCategory.GEM);
		assignGeneric(Ore.of("sandstone"), GiftCategory.BUILDING);
		assignGeneric(Blocks.WEB, GiftCategory.MONSTER);
		assignGeneric(Blocks.TALLGRASS, GiftCategory.PLANT);
		assignGeneric(Blocks.DEADBUSH, GiftCategory.JUNK);
		assignGeneric(Blocks.WOOL, GiftCategory.WOOL);
		assignGeneric(Blocks.YELLOW_FLOWER, GiftCategory.FLOWER);
		assignGeneric(Blocks.RED_FLOWER, GiftCategory.FLOWER);
		assignGeneric(Blocks.BROWN_MUSHROOM, GiftCategory.MUSHROOM);
		assignGeneric(Blocks.RED_MUSHROOM, GiftCategory.MUSHROOM);
		assignGeneric(Ore.of("blockGold"), GiftCategory.MINERAL);
		assignGeneric(Ore.of("blockIron"), GiftCategory.MINERAL);
		assignGeneric(Blocks.STONE_SLAB, GiftCategory.BUILDING);
		assignGeneric(Blocks.BRICK_BLOCK, GiftCategory.BUILDING);
		assignGeneric(Blocks.TNT, GiftCategory.MONSTER);
		assignGeneric(Blocks.BOOKSHELF, GiftCategory.BUILDING);
		assignGeneric(Blocks.MOSSY_COBBLESTONE, GiftCategory.BUILDING);
		assignGeneric(Ore.of("obsidian"), GiftCategory.BUILDING);
		assignGeneric(Ore.of("torch"), GiftCategory.JUNK);
		assignGeneric(Ore.of("stair").setType(MatchType.PREFIX), GiftCategory.BUILDING);
		assignGeneric(Ore.of("oreDiamond"), GiftCategory.MINERAL);
		assignGeneric(Ore.of("blockDiamond"), GiftCategory.GEM);
		assignGeneric(Ore.of("workbench"), GiftCategory.BUILDING);
		assignGeneric(Blocks.LADDER, GiftCategory.BUILDING);
		assignGeneric(Blocks.STONE_STAIRS, GiftCategory.BUILDING);
		assignGeneric(Blocks.LEVER, GiftCategory.BUILDING);
		assignGeneric(Blocks.STONE_PRESSURE_PLATE, GiftCategory.BUILDING);
		assignGeneric(Blocks.WOODEN_PRESSURE_PLATE, GiftCategory.BUILDING);
		assignGeneric(Ore.of("oreRedstone"), GiftCategory.MINERAL);
		assignGeneric(Blocks.REDSTONE_TORCH, GiftCategory.BUILDING);
		assignGeneric(Blocks.STONE_BUTTON, GiftCategory.BUILDING);
		assignGeneric(Blocks.ICE, GiftCategory.MINERAL);
		assignGeneric(Blocks.SNOW, GiftCategory.BUILDING);
		assignGeneric(Ore.of("blockCactus"), GiftCategory.PLANT);
		assignGeneric(Blocks.CLAY, GiftCategory.BUILDING);
		assignGeneric(Blocks.OAK_FENCE, GiftCategory.BUILDING);
		assignGeneric(Blocks.SPRUCE_FENCE, GiftCategory.BUILDING);
		assignGeneric(Blocks.BIRCH_FENCE, GiftCategory.BUILDING);
		assignGeneric(Blocks.JUNGLE_FENCE, GiftCategory.BUILDING);
		assignGeneric(Blocks.ACACIA_FENCE, GiftCategory.BUILDING);
		assignGeneric(Blocks.DARK_OAK_FENCE, GiftCategory.BUILDING);
		assignGeneric(Blocks.PUMPKIN, GiftCategory.VEGETABLE);
		assignGeneric(Ore.of("netherrack"), GiftCategory.JUNK);
		assignGeneric(Blocks.SOUL_SAND, GiftCategory.MONSTER);
		assignGeneric(Ore.of("glowstone"), GiftCategory.MINERAL);
		assignGeneric(Blocks.LIT_PUMPKIN, GiftCategory.VEGETABLE);
		assignGeneric(Blocks.TRAPDOOR, GiftCategory.BUILDING);
		assignGeneric(Blocks.STONEBRICK, GiftCategory.BUILDING);
		assignGeneric(Blocks.BROWN_MUSHROOM_BLOCK, GiftCategory.PLANT);
		assignGeneric(Blocks.RED_MUSHROOM_BLOCK, GiftCategory.PLANT);
		assignGeneric(Blocks.IRON_BARS, GiftCategory.BUILDING);
		assignGeneric(Ore.of("paneGlass").setType(MatchType.PREFIX), GiftCategory.BUILDING);
		assignGeneric(Blocks.MELON_BLOCK, GiftCategory.FRUIT);
		assignGeneric(Ore.of("vine"), GiftCategory.PLANT);
		assignGeneric(Blocks.OAK_FENCE_GATE, GiftCategory.BUILDING);
		assignGeneric(Blocks.SPRUCE_FENCE_GATE, GiftCategory.BUILDING);
		assignGeneric(Blocks.BIRCH_FENCE_GATE, GiftCategory.BUILDING);
		assignGeneric(Blocks.JUNGLE_FENCE_GATE, GiftCategory.BUILDING);
		assignGeneric(Blocks.ACACIA_FENCE_GATE, GiftCategory.BUILDING);
		assignGeneric(Blocks.DARK_OAK_FENCE_GATE, GiftCategory.BUILDING);
		assignGeneric(Blocks.BRICK_STAIRS, GiftCategory.BUILDING);
		assignGeneric(Blocks.STONE_BRICK_STAIRS, GiftCategory.BUILDING);
		assignGeneric(Blocks.MYCELIUM, GiftCategory.JUNK);
		assignGeneric(Blocks.WATERLILY, GiftCategory.PLANT);
		assignGeneric(Blocks.NETHER_BRICK, GiftCategory.BUILDING);
		assignGeneric(Blocks.NETHER_BRICK_FENCE, GiftCategory.BUILDING);
		assignGeneric(Blocks.NETHER_BRICK_STAIRS, GiftCategory.BUILDING);
		assignGeneric(Ore.of("endstone"), GiftCategory.JUNK);
		assignGeneric(Blocks.DRAGON_EGG, GiftCategory.MONSTER);
		assignGeneric(Blocks.REDSTONE_LAMP, GiftCategory.BUILDING);
		assignGeneric(Ore.of("slab").setType(MatchType.PREFIX), GiftCategory.BUILDING);
		assignGeneric(Blocks.SANDSTONE_STAIRS, GiftCategory.BUILDING);
		assignGeneric(Ore.of("oreEmerald"), GiftCategory.MINERAL);
		assignGeneric(Blocks.TRIPWIRE_HOOK, GiftCategory.BUILDING);
		assignGeneric(Ore.of("blockEmerald"), GiftCategory.GEM);
		assignGeneric(Blocks.COBBLESTONE_WALL, GiftCategory.BUILDING);
		assignGeneric(Blocks.WOODEN_BUTTON, GiftCategory.BUILDING);
		assignGeneric(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, GiftCategory.BUILDING);
		assignGeneric(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, GiftCategory.BUILDING);
		assignGeneric(Blocks.DAYLIGHT_DETECTOR, GiftCategory.BUILDING);
		assignGeneric(Ore.of("blockRedstone"), GiftCategory.MINERAL);
		assignGeneric(Ore.of("oreQuartz"), GiftCategory.MINERAL);
		assignGeneric(Ore.of("blockQuartz"), GiftCategory.MINERAL);
		assignGeneric(Blocks.QUARTZ_STAIRS, GiftCategory.BUILDING);
		assignGeneric(Blocks.STAINED_HARDENED_CLAY, GiftCategory.BUILDING);
		assignGeneric(Blocks.HAY_BLOCK, GiftCategory.BUILDING);
		assignGeneric(Blocks.CARPET, GiftCategory.WOOL);
		assignGeneric(Blocks.HARDENED_CLAY, GiftCategory.BUILDING);
		assignGeneric(Ore.of("blockCoal"), GiftCategory.MINERAL);
		assignGeneric(Blocks.PACKED_ICE, GiftCategory.BUILDING);
		assignGeneric(new ItemStack(Blocks.DOUBLE_PLANT, 1, 0), GiftCategory.FLOWER); //Sunflower
		assignGeneric(new ItemStack(Blocks.DOUBLE_PLANT, 1, 1), GiftCategory.FLOWER); //Lilac
		assignGeneric(new ItemStack(Blocks.DOUBLE_PLANT, 1, 2), GiftCategory.PLANT); //Tallgrass
		assignGeneric(new ItemStack(Blocks.DOUBLE_PLANT, 1, 3), GiftCategory.PLANT); //Fern
		assignGeneric(new ItemStack(Blocks.DOUBLE_PLANT, 1, 4), GiftCategory.FLOWER); //Rose
		assignGeneric(new ItemStack(Blocks.DOUBLE_PLANT, 1, 5), GiftCategory.FLOWER); //Peony
		assignGeneric(Blocks.STAINED_GLASS, GiftCategory.BUILDING);
		assignGeneric(Blocks.STAINED_GLASS_PANE, GiftCategory.BUILDING);
		assignGeneric(Ore.of("blockPrismarine").setType(MatchType.PREFIX), GiftCategory.BUILDING);
		assignGeneric(Blocks.SEA_LANTERN, GiftCategory.BUILDING);
		assignGeneric(Blocks.RED_SANDSTONE, GiftCategory.BUILDING);
		assignGeneric(Blocks.RED_SANDSTONE_STAIRS, GiftCategory.BUILDING);
		assignGeneric(Blocks.PURPUR_BLOCK, GiftCategory.BUILDING);
		assignGeneric(Blocks.PURPUR_PILLAR, GiftCategory.BUILDING);
		assignGeneric(Blocks.PURPUR_SLAB, GiftCategory.BUILDING);
		assignGeneric(Blocks.PURPUR_STAIRS, GiftCategory.BUILDING);
		assignGeneric(Blocks.END_BRICKS, GiftCategory.BUILDING);
		assignGeneric(Ore.of("blockSlime"), GiftCategory.JUNK);
	}
}