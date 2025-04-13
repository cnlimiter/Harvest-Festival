package joshie.harvest.core.base.other;

import joshie.harvest.api.npc.greeting.Script;
import joshie.harvest.core.lib.HFModInfo;
import net.minecraft.util.ResourceLocation;

public class HFScript extends Script {
	public HFScript(String name) {
		super(new ResourceLocation(HFModInfo.MODID, name));
	}
}
