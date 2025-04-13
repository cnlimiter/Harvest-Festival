package joshie.harvest.core.base.other;

import joshie.harvest.HarvestFestival;
import joshie.harvest.api.npc.greeting.Script;

public class HFScript extends Script {
	public HFScript(String name) {
		super(HarvestFestival.id(name));
	}
}
