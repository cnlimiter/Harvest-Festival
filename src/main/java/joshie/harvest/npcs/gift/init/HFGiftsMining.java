package joshie.harvest.npcs.gift.init;

import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.core.util.annotations.HFLoader;
import joshie.harvest.mining.HFMining;
import joshie.harvest.mining.item.ItemMaterial.Material;

@HFLoader(priority = 0)
public class HFGiftsMining extends HFGiftsAbstract {
	public static void init() {
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.JUNK), GiftCategory.JUNK);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.SILVER), GiftCategory.MINERAL);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.MYSTRIL), GiftCategory.MINERAL);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.GOLD), GiftCategory.MINERAL);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.COPPER), GiftCategory.MINERAL);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.MYTHIC), GiftCategory.MINERAL);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.ADAMANTITE), GiftCategory.GEM);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.AGATE), GiftCategory.GEM);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.ALEXANDRITE), GiftCategory.GEM);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.AMETHYST), GiftCategory.GEM);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.FLUORITE), GiftCategory.GEM);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.MOON_STONE), GiftCategory.GEM);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.ORICHALC), GiftCategory.GEM);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.PERIDOT), GiftCategory.GEM);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.PINK_DIAMOND), GiftCategory.GEM);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.RUBY), GiftCategory.GEM);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.SAND_ROSE), GiftCategory.GEM);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.TOPAZ), GiftCategory.GEM);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.COIN_COPPER), GiftCategory.MONEY);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.COIN_SILVER), GiftCategory.MONEY);
		assignGeneric(HFMining.MATERIALS.getStackFromEnum(Material.COIN_GOLD), GiftCategory.MONEY);
	}
}