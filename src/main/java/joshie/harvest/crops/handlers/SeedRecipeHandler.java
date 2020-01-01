package joshie.harvest.crops.handlers;

import java.util.ArrayList;

import joshie.harvest.api.crops.Crop;
import joshie.harvest.crops.HFCrops;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class SeedRecipeHandler extends ShapelessOreRecipe {

    private final Crop crop;

    public SeedRecipeHandler(ItemStack result, Crop crop) {
        super(null, result, crop.getSeedStack(1));
        this.crop = crop;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean matches(InventoryCrafting var1, World world) {
        ArrayList<Ingredient> required = new ArrayList<>(input);
        for (int x = 0; x < var1.getSizeInventory(); x++) {
            ItemStack slot = var1.getStackInSlot(x);
            if (!slot.isEmpty()) {
                boolean inRecipe = false;

                for (Ingredient aRequired : required) {
                    if (aRequired.test(slot) && HFCrops.SEEDS.getCropFromStack(slot) == crop) {
                        inRecipe = true;
                        required.remove(aRequired);
                        break;
                    }
                }

                if (!inRecipe) {
                    return false;
                }
            }
        }

        return required.isEmpty();
    }
}
