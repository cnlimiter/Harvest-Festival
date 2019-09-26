package joshie.harvest.core.advancements;

import joshie.harvest.core.util.annotations.HFLoader;
import net.minecraft.advancements.CriteriaTriggers;

@HFLoader
public class HFAdvancements
{
    @SuppressWarnings("unused")
    public static void postInit()
    {
        CriteriaTriggers.register(EventTrigger.INSTANCE);

        //        firstChristmas = addAdvancement("firstChristmas", 0, -3, new ItemStack(Blocks.SAPLING, 1, 1), summon);
        //        birthday = addAdvancement("birthday", 0, -5, new ItemStack(Items.CAKE), firstChristmas);
        //        firstShipping = addAdvancement("firstShipping", -2, 2, HFCore.STORAGE.getStackFromEnum(Storage.SHIPPING), summon);
        //        millionaire = addAdvancement("millionaire", -4, 2, new ItemStack(Items.GOLD_INGOT), firstShipping);
    }
}
