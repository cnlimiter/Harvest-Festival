package joshie.harvest.npcs.gift.init;

import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.core.util.annotations.HFLoader;
import joshie.harvest.fishing.HFFishing;
import joshie.harvest.fishing.item.ItemJunk.Junk;

@HFLoader(priority = 0)
public class HFGiftsFishing extends HFGiftsAbstract {
	public static void init() {
		assignGeneric(HFFishing.JUNK.getStackFromEnum(Junk.BAIT), GiftCategory.JUNK);
		assignGeneric(HFFishing.JUNK.getStackFromEnum(Junk.BONES), GiftCategory.JUNK);
		assignGeneric(HFFishing.JUNK.getStackFromEnum(Junk.BOOT), GiftCategory.JUNK);
		assignGeneric(HFFishing.JUNK.getStackFromEnum(Junk.CAN), GiftCategory.JUNK);
		assignGeneric(HFFishing.JUNK.getStackFromEnum(Junk.FOSSIL), GiftCategory.KNOWLEDGE);
		assignGeneric(HFFishing.JUNK.getStackFromEnum(Junk.TREASURE), GiftCategory.GEM);
	}
}