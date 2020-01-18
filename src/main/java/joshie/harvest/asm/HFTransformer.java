package joshie.harvest.asm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import joshie.harvest.asm.transformers.AbstractASM;
import joshie.harvest.asm.transformers.PlayerWakeTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.SortingIndex;

@SortingIndex(100)
public class HFTransformer implements IFMLLoadingPlugin {
    public static boolean isObfuscated = false;
    static final List<AbstractASM> asm = new ArrayList<>();

    static {
        //asm.add(new PlayerWakeTransformer());
        asm.add(new RenderRainTransformer());
        //asm.add(new RenderItemTransformer());
        //asm.add(new FishingTransformer(4, "net.minecraft.entity.projectile.EntityFishHook"));
        //asm.add(new FishingTransformer(3, "com.teammetallurgy.aquaculture.handlers.EntityCustomFishHook"));
    }

    @Override
    public String[] getASMTransformerClass() {
        return null;
        //return new String[] { RealTransformer.class.getName() };
    }

    @Override
    public String getAccessTransformerClass() {
        return "joshie.harvest.asm.RealTransformer"; // I do not know why, but it worked
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        HFTransformer.isObfuscated = ((Boolean) data.get("runtimeDeobfuscationEnabled"));
    }
}
