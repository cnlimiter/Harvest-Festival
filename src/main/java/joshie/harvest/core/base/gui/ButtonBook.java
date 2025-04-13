package joshie.harvest.core.base.gui;

import joshie.harvest.HarvestFestival;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class ButtonBook<G extends GuiBaseBook> extends GuiButton {
	protected static final ResourceLocation TEXTURE = HarvestFestival.id("textures/gui/book_cooking_left.png");
	protected G gui;

	public ButtonBook(G gui, int buttonId, int x, int y, String string) {
		super(buttonId, gui.guiLeft + x, gui.guiTop + y, string);
		this.gui = gui;
	}
}
