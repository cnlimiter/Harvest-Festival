package joshie.harvest.knowledge.gui.stats.collection.page;

import javax.annotation.Nonnull;
import joshie.harvest.core.base.gui.BookPage;
import joshie.harvest.fishing.HFFishing;
import joshie.harvest.fishing.item.ItemFish.Fish;
import joshie.harvest.knowledge.gui.stats.CollectionHelper;
import net.minecraft.item.ItemStack;

public class PageFishing extends PageShipping {
	public static final BookPage INSTANCE = new PageFishing();

	private PageFishing() {
		super("fishing", HFFishing.FISH.getStackFromEnum(Fish.PUPFISH));
	}

	@Override
	@Nonnull
	public ItemStack getIcon() {
		return HFFishing.FISH.getStackFromEnum(Fish.PUPFISH);
	}

	@Override
	boolean qualifies(@Nonnull ItemStack stack) {
		return CollectionHelper.isInFishCollection(stack);
	}
}
