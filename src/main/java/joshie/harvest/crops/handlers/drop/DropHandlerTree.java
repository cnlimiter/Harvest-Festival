package joshie.harvest.crops.handlers.drop;

import java.util.Random;

import javax.annotation.Nonnull;
import joshie.harvest.api.crops.DropHandler;
import joshie.harvest.api.trees.Tree;
import net.minecraft.item.ItemStack;

@SuppressWarnings("unused")
public class DropHandlerTree extends DropHandler<Tree> {
	@Override
	@Nonnull
	public ItemStack getDrop(Tree tree, int stage, Random rand) {
		return tree.getWoodStack();
	}
}