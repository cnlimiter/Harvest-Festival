package joshie.harvest.npcs.gift;

import joshie.harvest.api.core.Ore;
import joshie.harvest.api.npc.gift.GiftCategory;
import net.minecraft.init.Items;

@SuppressWarnings("unused")
public class GiftsJenni extends Gifts {
	public GiftsJenni() {
		stackRegistry.register(Items.CARROT_ON_A_STICK, Quality.AWESOME);
		stackRegistry.register(Ore.of("cropCarrot"), Quality.AWESOME);
		stackRegistry.register(Items.GOLDEN_CARROT, Quality.AWESOME);
		categoryRegistry.put(GiftCategory.ART, Quality.GOOD);
		stackRegistry.register(Ore.of("cropOrange"), Quality.GOOD);
		stackRegistry.register(Ore.of("cropPeach"), Quality.GOOD);
		stackRegistry.register(Items.RABBIT_FOOT, Quality.GOOD);
		categoryRegistry.put(GiftCategory.WOOL, Quality.GOOD);
		categoryRegistry.put(GiftCategory.VEGETABLE, Quality.GOOD);
		categoryRegistry.put(GiftCategory.MONEY, Quality.DECENT);
		categoryRegistry.put(GiftCategory.JUNK, Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.BUILDING, Quality.DISLIKE);
		categoryRegistry.put(GiftCategory.MINERAL, Quality.BAD);
		stackRegistry.register(Items.FLINT, Quality.TERRIBLE);
		stackRegistry.register(Ore.of("ingotIron"), Quality.TERRIBLE);
		stackRegistry.register(Ore.of("ingotGold"), Quality.TERRIBLE);
	}
}