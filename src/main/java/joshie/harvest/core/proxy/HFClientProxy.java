package joshie.harvest.core.proxy;

import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.block.statemap.StateMap;

public class HFClientProxy extends HFCommonProxy {
	//public static final HashMap<Item, EntityItemRenderer> RENDER_MAP = new HashMap<>();
	public static final StateMap NO_WATER = new StateMap.Builder().ignore(BlockLiquid.LEVEL).build();

	@Override
	public boolean isClient() {
		return true;
	}
}
