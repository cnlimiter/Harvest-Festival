package joshie.harvest.npcs.gift.init;

import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.core.HFCore;
import joshie.harvest.core.block.BlockFlower.FlowerType;
import joshie.harvest.core.util.annotations.HFLoader;
import joshie.harvest.gathering.HFGathering;
import joshie.harvest.gathering.block.BlockNature.NaturalBlock;
import joshie.harvest.gathering.block.BlockRock.Rock;
import joshie.harvest.gathering.block.BlockWood.Wood;

@HFLoader(priority = 0)
public class HFGiftsGathering extends HFGiftsAbstract {
	public static void init() {
		assignGeneric(HFGathering.NATURE.getStackFromEnum(NaturalBlock.BAMBOO), GiftCategory.VEGETABLE);
		assignGeneric(HFGathering.NATURE.getStackFromEnum(NaturalBlock.MATSUTAKE), GiftCategory.MUSHROOM);
		assignGeneric(HFGathering.NATURE.getStackFromEnum(NaturalBlock.CHAMOMILE), GiftCategory.HERB);
		assignGeneric(HFGathering.NATURE.getStackFromEnum(NaturalBlock.MINT), GiftCategory.HERB);
		assignGeneric(HFGathering.NATURE.getStackFromEnum((NaturalBlock.LAVENDER)), GiftCategory.HERB);
		assignGeneric(HFCore.FLOWERS.getStackFromEnum(FlowerType.WEED), GiftCategory.JUNK);
		assignGeneric(HFCore.FLOWERS.getStackFromEnum(FlowerType.GODDESS), GiftCategory.FLOWER);
		assignGeneric(HFCore.FLOWERS.getStackFromEnum(FlowerType.PINKCAT), GiftCategory.FLOWER);
		assignGeneric(HFCore.FLOWERS.getStackFromEnum(FlowerType.TOY), GiftCategory.FLOWER);
		assignGeneric(HFCore.FLOWERS.getStackFromEnum(FlowerType.BLUE_MAGICGRASS), GiftCategory.FLOWER);
		assignGeneric(HFCore.FLOWERS.getStackFromEnum(FlowerType.MOONDROP), GiftCategory.FLOWER);
		assignGeneric(HFCore.FLOWERS.getStackFromEnum(FlowerType.RED_MAGICGRASS), GiftCategory.FLOWER);
		assignGeneric(HFGathering.WOOD.getStackFromEnum(Wood.BRANCH_SMALL), GiftCategory.JUNK);
		assignGeneric(HFGathering.WOOD.getStackFromEnum(Wood.BRANCH_MEDIUM), GiftCategory.JUNK);
		assignGeneric(HFGathering.WOOD.getStackFromEnum(Wood.BRANCH_LARGE), GiftCategory.JUNK);
		assignGeneric(HFGathering.WOOD.getStackFromEnum(Wood.STUMP_SMALL), GiftCategory.JUNK);
		assignGeneric(HFGathering.WOOD.getStackFromEnum(Wood.STUMP_MEDIUM), GiftCategory.JUNK);
		assignGeneric(HFGathering.WOOD.getStackFromEnum(Wood.STUMP_LARGE), GiftCategory.JUNK);
		assignGeneric(HFGathering.ROCK.getStackFromEnum(Rock.STONE_SMALL), GiftCategory.JUNK);
		assignGeneric(HFGathering.ROCK.getStackFromEnum(Rock.STONE_MEDIUM), GiftCategory.JUNK);
		assignGeneric(HFGathering.ROCK.getStackFromEnum(Rock.STONE_LARGE), GiftCategory.JUNK);
		assignGeneric(HFGathering.ROCK.getStackFromEnum(Rock.BOULDER_SMALL), GiftCategory.JUNK);
		assignGeneric(HFGathering.ROCK.getStackFromEnum(Rock.BOULDER_MEDIUM), GiftCategory.JUNK);
		assignGeneric(HFGathering.ROCK.getStackFromEnum(Rock.BOULDER_LARGE), GiftCategory.JUNK);
	}
}