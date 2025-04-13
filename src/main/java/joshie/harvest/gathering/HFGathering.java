package joshie.harvest.gathering;

import joshie.harvest.api.HFApi;
import joshie.harvest.api.calendar.Season;
import joshie.harvest.core.HFCore;
import joshie.harvest.core.block.BlockFlower;
import joshie.harvest.core.helpers.ConfigHelper;
import joshie.harvest.core.util.annotations.HFLoader;
import joshie.harvest.gathering.block.BlockNature;
import joshie.harvest.gathering.block.BlockRock;
import joshie.harvest.gathering.block.BlockWood;

@HFLoader
public class HFGathering {
	public static final BlockNature NATURE = new BlockNature().register("nature");
	public static final BlockRock ROCK = new BlockRock().register("rock");
	public static final BlockWood WOOD = new BlockWood().register("wood");

	public static void preInit() {
		//To init the blocks
	}

	//256 Total
	@SuppressWarnings("deprecation")
	public static void init() {
		//Seasons add up to 64
		//Spring
		HFApi.gathering.registerGathering(HFCore.FLOWERS.getStateFromEnum(BlockFlower.FlowerType.WEED), 18D, Season.SPRING);
		HFApi.gathering.registerGathering(HFCore.FLOWERS.getStateFromEnum(BlockFlower.FlowerType.MOONDROP), 5D, Season.SPRING);
		HFApi.gathering.registerGathering(HFCore.FLOWERS.getStateFromEnum(BlockFlower.FlowerType.TOY), 3D, Season.SPRING);
		HFApi.gathering.registerGathering(NATURE.getStateFromEnum(BlockNature.NaturalBlock.BAMBOO), 11D, Season.SPRING);
		HFApi.gathering.registerGathering(NATURE.getStateFromEnum(BlockNature.NaturalBlock.CHAMOMILE), 13D, Season.SPRING);
		HFApi.gathering.registerGathering(NATURE.getStateFromEnum(BlockNature.NaturalBlock.MINT), 14D, Season.SPRING);

		//Summer
		HFApi.gathering.registerGathering(HFCore.FLOWERS.getStateFromEnum(BlockFlower.FlowerType.WEED), 20D, Season.SUMMER);
		HFApi.gathering.registerGathering(HFCore.FLOWERS.getStateFromEnum(BlockFlower.FlowerType.PINKCAT), 5D, Season.SUMMER);
		HFApi.gathering.registerGathering(NATURE.getStateFromEnum(BlockNature.NaturalBlock.CHAMOMILE), 19D, Season.SUMMER);
		HFApi.gathering.registerGathering(NATURE.getStateFromEnum(BlockNature.NaturalBlock.MINT), 20D, Season.SUMMER);

		//Autumn
		HFApi.gathering.registerGathering(HFCore.FLOWERS.getStateFromEnum(BlockFlower.FlowerType.WEED), 20D, Season.AUTUMN);
		HFApi.gathering.registerGathering(HFCore.FLOWERS.getStateFromEnum(BlockFlower.FlowerType.BLUE_MAGICGRASS), 4D, Season.AUTUMN);
		HFApi.gathering.registerGathering(HFCore.FLOWERS.getStateFromEnum(BlockFlower.FlowerType.RED_MAGICGRASS), 2D, Season.AUTUMN);
		HFApi.gathering.registerGathering(NATURE.getStateFromEnum(BlockNature.NaturalBlock.MATSUTAKE), 1D, Season.AUTUMN);
		HFApi.gathering.registerGathering(NATURE.getStateFromEnum(BlockNature.NaturalBlock.LAVENDER), 10D, Season.AUTUMN);
		HFApi.gathering.registerGathering(NATURE.getStateFromEnum(BlockNature.NaturalBlock.CHAMOMILE), 12D, Season.AUTUMN);
		HFApi.gathering.registerGathering(NATURE.getStateFromEnum(BlockNature.NaturalBlock.MINT), 15D, Season.AUTUMN);

		//Winter
		HFApi.gathering.registerGathering(NATURE.getStateFromEnum(BlockNature.NaturalBlock.LAVENDER), 64D, Season.WINTER);

		//All Seasons, Adds to 192
		HFApi.gathering.registerGathering(WOOD.getStateFromEnum(BlockWood.Wood.BRANCH_SMALL), 28D);
		HFApi.gathering.registerGathering(WOOD.getStateFromEnum(BlockWood.Wood.BRANCH_MEDIUM), 18D);
		HFApi.gathering.registerGathering(WOOD.getStateFromEnum(BlockWood.Wood.BRANCH_LARGE), 12D);
		HFApi.gathering.registerGathering(ROCK.getStateFromEnum(BlockRock.Rock.STONE_SMALL), 28D);
		HFApi.gathering.registerGathering(ROCK.getStateFromEnum(BlockRock.Rock.STONE_MEDIUM), 13D);
		HFApi.gathering.registerGathering(ROCK.getStateFromEnum(BlockRock.Rock.STONE_LARGE), 8D);
		HFApi.gathering.registerGathering(WOOD.getStateFromEnum(BlockWood.Wood.STUMP_SMALL), 25D);
		HFApi.gathering.registerGathering(WOOD.getStateFromEnum(BlockWood.Wood.STUMP_MEDIUM), 10D);
		HFApi.gathering.registerGathering(WOOD.getStateFromEnum(BlockWood.Wood.STUMP_LARGE), 5D);
		HFApi.gathering.registerGathering(ROCK.getStateFromEnum(BlockRock.Rock.BOULDER_SMALL), 28D);
		HFApi.gathering.registerGathering(ROCK.getStateFromEnum(BlockRock.Rock.BOULDER_MEDIUM), 12D);
		HFApi.gathering.registerGathering(ROCK.getStateFromEnum(BlockRock.Rock.BOULDER_LARGE), 5D);
	}

	//Configure
	static int GATHERING_MINIMUM;
	static int GATHERING_MAXIMUM;
	static int GATHERING_ATTEMPTS;
	static int GATHERING_MAX_HALF;

	public static void configure() {
		GATHERING_MINIMUM = ConfigHelper.getInteger(
				"Minimum distance for wilderness",
				48,
				"The minimum distance at which stuff will spawn like flowers/junk around towns");
		GATHERING_MAXIMUM = ConfigHelper.getInteger(
				"Maximum distance for wilderness",
				512,
				"The maximum distance at which stuff will spawn like flowers/junk around towns");
		GATHERING_ATTEMPTS = ConfigHelper.getInteger(
				"Wilderness spawns amount",
				256,
				"The number of blocks to spawn around an individual town in the wilderness");
		GATHERING_MAX_HALF = GATHERING_MAXIMUM / 2;
	}
}
