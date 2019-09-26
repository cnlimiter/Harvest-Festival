package joshie.harvest.mining.block;

import joshie.harvest.api.gathering.ISmashable;
import joshie.harvest.core.HFTab;
import joshie.harvest.core.HFTrackers;
import joshie.harvest.core.base.block.BlockHFSmashable;
import joshie.harvest.core.entity.EntityBasket;
import joshie.harvest.core.lib.CreativeSort;
import joshie.harvest.mining.HFMining;
import joshie.harvest.mining.MiningHelper;
import joshie.harvest.mining.block.BlockOre.Ore;
import joshie.harvest.mining.item.ItemMaterial.Material;
import joshie.harvest.tools.item.ItemHammer;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nonnull;
import java.util.Locale;

import static joshie.harvest.api.gathering.ISmashable.ToolType.HAMMER;
import static joshie.harvest.core.lib.LootStrings.MINING;
import static joshie.harvest.core.lib.LootStrings.MINING_GEMS;
import static net.minecraft.block.material.Material.ROCK;
import static net.minecraft.init.Items.DIAMOND;

public class BlockOre extends BlockHFSmashable<BlockOre, Ore> implements ISmashable {
    private static final AxisAlignedBB COPPER_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);

    public enum Ore implements IStringSerializable {
        ROCK, COPPER, SILVER, GOLD, MYSTRIL, GEM,
        EMERALD, DIAMOND, RUBY, AMETHYST, TOPAZ, JADE;

        @Override
        public String getName() {
            return name().toLowerCase(Locale.ENGLISH);
        }
    }

    public BlockOre() {
        super(ROCK, Ore.class, HFTab.MINING);
        setBlockUnbreakable();
        setSoundType(SoundType.STONE);
    }

    //Return 0.75F if the plant isn't withered, otherwise, unbreakable!!!
    @Override
    @SuppressWarnings("deprecation")
    public float getPlayerRelativeBlockHardness(IBlockState state, @Nonnull EntityPlayer player, @Nonnull World world, @Nonnull BlockPos pos) {
        return (player.getHeldItemMainhand().getItem() instanceof ItemHammer)
                ? ((((ItemHammer)player.getHeldItemMainhand().getItem()).getTier(player.getHeldItemMainhand()).ordinal() + 2)
                - getToolLevel(getEnumFromState(state))) * 0.025F : -1F;
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return COPPER_AABB;
    }

    @Override
    public ToolType getToolType() {
        return HAMMER;
    }

    @Override
    public boolean isValidTool(ItemStack stack) {
        return stack.getItem() instanceof ItemHammer;
    }

    @Override
    protected int getToolLevel(Ore ore) {
        switch (ore) {
            case ROCK:
            case COPPER:
            case AMETHYST:
            case GEM:
                return 1;
            case SILVER:
            case TOPAZ:
                return 2;
            case GOLD:
            case JADE:
            case RUBY:
                return 3;
            case MYSTRIL:
            case EMERALD:
            case DIAMOND:
                return 4;
            default:
                return 0;
        }
    }

    private static void getRandomStack(NonNullList<ItemStack> drops, World world, Material material, int bonus) {
        while (bonus > 0) {
            if (world.rand.nextInt(bonus) == 0)
            {
                drops.add(HFMining.MATERIALS.getStackFromEnum(material, 1 + world.rand.nextInt(bonus)));
                return;
            }
            bonus--;
        }

        drops.add(HFMining.MATERIALS.getStackFromEnum(material, 1));
    }

    private static void getRandomStack(NonNullList<ItemStack> drops, World world, Item item, int bonus) {
        drops.add(new ItemStack(item, 1 + world.rand.nextInt(bonus)));
    }

    @Override
    public void getDrops(final NonNullList<ItemStack> drops, EntityPlayer player, World world, BlockPos pos, IBlockState state, float luck) {
        Ore ore = getEnumFromState(state);

        if (world instanceof WorldServer) {
            WorldServer server = ((WorldServer) world);
            server.spawnParticle(EnumParticleTypes.BLOCK_CRACK, pos.getX(), pos.getY(), pos.getZ(), 10, 0.5D, 0.5D, 0.5D, 0.0D, Block.getStateId(Blocks.DIRT.getDefaultState()));
        }

        switch (ore) {
            case ROCK:
                if (world.isRemote)
                {
                    drops.add(new ItemStack(this));
                }
                else {
                    MiningHelper.getLoot(drops, MINING, world, player, luck);
                }
                break;
            case COPPER:
                getRandomStack(drops, world, Material.COPPER, 5);
                break;
            case SILVER:
                getRandomStack(drops, world, Material.SILVER, 4);
                break;
            case GOLD:
                getRandomStack(drops, world, Material.GOLD, 3);
                break;
            case MYSTRIL:
                getRandomStack(drops, world, Material.MYSTRIL, 3);
                break;
            case EMERALD:
                getRandomStack(drops, world, Items.EMERALD, 5);
                break;
            case DIAMOND:
                if (world.rand.nextInt(512) == 0)
                {
                    getRandomStack(drops, world, Material.PINK_DIAMOND, 1);
                }
                else {
                    getRandomStack(drops, world, DIAMOND, 3);
                }
                break;
            case RUBY:
                getRandomStack(drops, world, Material.RUBY, 4);
                break;
            case JADE:
                getRandomStack(drops, world, Material.JADE, 5);
                break;
            case AMETHYST:
                getRandomStack(drops, world, Material.AMETHYST, 3);
                break;
            case TOPAZ:
                getRandomStack(drops, world, Material.TOPAZ, 4);
                break;
            case GEM:
                if (world.isRemote)
                {
                    drops.add(new ItemStack(this));
                }
                else {
                    MiningHelper.getLoot(drops, MINING_GEMS, world, player, luck);
                }
                break;
        }

        EntityBasket.findBasketAndShip(player, drops);
        if (!world.isRemote) {
            for (ItemStack stack : drops) {
                HFTrackers.getPlayerTrackerFromPlayer(player).getTracking().addAsObtained(stack);
            }
        }
    }

    @Override
    public int getSortValue(@Nonnull ItemStack stack) {
        return CreativeSort.TOOLS - 20 + stack.getItemDamage();
    }
}