package joshie.harvest.plugins.immersiveengineering;

import com.google.common.base.Supplier;

import joshie.harvest.api.crops.Crop;
import joshie.harvest.api.crops.StateHandlerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class StateHandlerHemp extends StateHandlerBlock {
    public StateHandlerHemp(Supplier<Block> block) {
        super(block);
    }

    static int getMetaFromStage(int stage) {
        if (stage <= 3) return 0;
        else if (stage <= 6) return 1;
        else if (stage <= 10) return 2;
        else if (stage <= 14) return 3;
        else return 4;
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getState(IBlockAccess world, BlockPos pos, PlantSection section, Crop crop, int stage, boolean withered) {
        if (section == PlantSection.TOP) return block.get().getStateFromMeta(5);
        return block.get().getStateFromMeta(getMetaFromStage(stage));
    }
}
