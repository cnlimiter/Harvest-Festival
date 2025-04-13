package joshie.harvest.knowledge.gui.stats.relations.page;

import java.util.List;

import javax.annotation.Nonnull;
import joshie.harvest.core.base.gui.BookPage;
import joshie.harvest.knowledge.gui.stats.GuiStats;
import joshie.harvest.knowledge.gui.stats.button.ButtonTabRight;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.item.ItemStack;

public class PageRelationship extends BookPage<GuiStats> {
	PageRelationship(String string, @Nonnull ItemStack stack) {
		super("relationships", string, stack);
	}

	@Override
	public void initGui(GuiStats gui, List<GuiButton> buttonList, List<GuiLabel> labelList) {
		super.initGui(gui, buttonList, labelList);
		buttonList.add(new ButtonTabRight(gui, PageNPC.INSTANCE, buttonList.size(), 308, 32));
		buttonList.add(new ButtonTabRight(gui, PageAnimals.INSTANCE, buttonList.size(), 308, 66));
	}
}
