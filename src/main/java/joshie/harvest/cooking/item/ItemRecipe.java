package joshie.harvest.cooking.item;

import javax.annotation.Nonnull;
import joshie.harvest.HarvestFestival;
import joshie.harvest.api.cooking.Recipe;
import joshie.harvest.core.HFTab;
import joshie.harvest.core.HFTrackers;
import joshie.harvest.core.base.item.ItemHFRegistry;
import joshie.harvest.core.helpers.ChatHelper;
import joshie.harvest.core.helpers.TextHelper;
import joshie.harvest.core.lib.HFModInfo;
import joshie.harvest.core.lib.HFSounds;
import joshie.harvest.core.util.interfaces.ICreativeSorted;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemRecipe extends ItemHFRegistry<ItemRecipe, Recipe> implements ICreativeSorted {
	public ItemRecipe() {
		super("Recipe", Recipe.REGISTRY, HFTab.COOKING);
	}

	@Override
	@Nonnull
	public String getItemStackDisplayName(@Nonnull ItemStack stack) {
		return TextHelper.format(HFModInfo.MODID + ".recipe.format", getObjectFromStack(stack).getDisplayName());
	}

	@Override
	@Nonnull
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		Recipe recipe = getObjectFromStack(stack);
		if (recipe != null && HFTrackers.getPlayerTrackerFromPlayer(player).getTracking().learnRecipe(recipe)) {
			if (!player.capabilities.isCreativeMode) {
				stack.shrink(1); //Decrease the stack
			}
			world.playSound(player.posX, player.posY, player.posZ, HFSounds.RECIPE, SoundCategory.NEUTRAL, 0.8F, 1F, true);
			if (world.isRemote) {
				ChatHelper.displayChat(TextHelper.translate("meal.learnt") + " " + TextFormatting.YELLOW + recipe.getDisplayName());
			}
			return new ActionResult<>(EnumActionResult.SUCCESS, stack);
		}

		return new ActionResult<>(EnumActionResult.PASS, stack);
	}

	@Override
	public Recipe getDefaultValue() {
		return Recipe.REGISTRY.get(HarvestFestival.id("pancake_savoury"));
	}

	@Override
	public int getSortValue(@Nonnull ItemStack stack) {
		return 5000;
	}
}