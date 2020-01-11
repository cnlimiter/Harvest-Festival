package joshie.harvest.plugins.immersiveengineering;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import joshie.harvest.api.HFApi;
import joshie.harvest.api.calendar.Season;
import joshie.harvest.api.crops.Crop;
import joshie.harvest.core.util.annotations.HFLoader;
import joshie.harvest.crops.HFCrops;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry.ItemStackHolder;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@HFLoader(mods = "immersiveengineering", priority = -1)
@SuppressWarnings("unused")
public class ImmersiveEngineering {

    private static Crop HEMP;

    @ItemStackHolder(value = "immersiveengineering:material", meta = 4)
    public static final ItemStack hemp = ItemStack.EMPTY;

    @ItemStackHolder(value = "immersiveengineering:seed")
    public static final ItemStack hemp_seeds = ItemStack.EMPTY;

    @ObjectHolder(value = "immersiveengineering:hemp")
    public static final Block hemp_block = Blocks.AIR;

    public static void preInit() {
        HEMP = new Crop(new ResourceLocation("immersiveengineering", "hemp")).setValue(1000, 1).setStages(15).setRegrow(1).setSeedColours(0xB57449).setSeasons(Season.SPRING, Season.SUMMER, Season.AUTUMN).setStateHandler(new StateHandlerHemp(() -> hemp_block)).setSkipRender().setAnimalFoodType(null).setRequiresSickle(15).setGrowthHandler(new HempGrowthHandler()).setBecomesDouble(15);
    }

    public static void init() {
        HEMP.setDropHandler(new DropHandlerHemp(hemp.getItem())).setItem(hemp);
        HFApi.crops.registerSeedForBlacklisting(hemp_seeds);
    }

    @SuppressWarnings("unchecked")
    public static void postInit() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class clazz = Class.forName("blusunrize.immersiveengineering.api.crafting.SqueezerRecipe");
        Method method = clazz.getMethod("addRecipe", FluidStack.class, ItemStack.class, Object.class, int.class);
        method.invoke(null, FluidRegistry.getFluidStack("plantoil", 750), new ItemStack(Blocks.WOOL), HFCrops.SEEDS, 6400);
    }
}
