package joshie.harvest.knowledge.gui.stats.collection.page;

import javax.annotation.Nonnull;
import joshie.harvest.core.base.gui.BookPage;
import joshie.harvest.knowledge.gui.stats.CollectionHelper;
import joshie.harvest.mining.HFMining;
import joshie.harvest.mining.item.ItemMaterial.Material;
import net.minecraft.item.ItemStack;

public class PageMining extends PageShipping {
	public static final BookPage INSTANCE = new PageMining();

	private PageMining() {
		super("mining", HFMining.MATERIALS.getStackFromEnum(Material.ADAMANTITE));
	}

	@Override
	boolean qualifies(@Nonnull ItemStack stack) {
		return CollectionHelper.isInMiningCollection(stack);
	}
}
