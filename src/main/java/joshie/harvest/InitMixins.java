package joshie.harvest;

import java.util.Collections;
import java.util.List;

import zone.rong.mixinbooter.ILateMixinLoader;

public class InitMixins implements ILateMixinLoader {
	@Override
	public List<String> getMixinConfigs() {
		return Collections.singletonList("harvestfestival.mixins.json");
	}
}
