package joshie.harvest.cooking.block;

import java.util.Locale;

import javax.annotation.Nonnull;

import joshie.harvest.HarvestFestival;
import joshie.harvest.api.HFApi;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.block.BlockCookware.Cookware;
import joshie.harvest.cooking.tile.TileCooking;
import joshie.harvest.cooking.tile.TileCounter;
import joshie.harvest.cooking.tile.TileFridge;
import joshie.harvest.cooking.tile.TileFryingPan;
import joshie.harvest.cooking.tile.TileHeatable;
import joshie.harvest.cooking.tile.TileMixer;
import joshie.harvest.cooking.tile.TileOven;
import joshie.harvest.cooking.tile.TilePot;
import joshie.harvest.core.HFTab;
import joshie.harvest.core.base.block.BlockHFEnumRotatableTile;
import joshie.harvest.core.handlers.GuiHandler;
import joshie.harvest.core.helpers.SpawnItemHelper;
import joshie.harvest.core.util.interfaces.IFaceable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCookware extends BlockHFEnumRotatableTile<BlockCookware, Cookware>
{
    private static final AxisAlignedBB FRYING_PAN_AABB = new AxisAlignedBB(0.2F, 0F, 0.2F, 0.8F, 0.15F, 0.8F);
    private static final AxisAlignedBB MIXER_AABB = new AxisAlignedBB(0.275F, 0F, 0.275F, 0.725F, 0.725F, 0.725F);
    private static final AxisAlignedBB POT_AABB = new AxisAlignedBB(0.2F, 0F, 0.2F, 0.8F, 0.375F, 0.8F);
    private static Item cookware = null;

    public static enum Cookware implements IStringSerializable
    {
        FRIDGE_TOP(false),
        FRIDGE(true),
        COUNTER(true),
        POT(true),
        FRYING_PAN(true),
        MIXER(true),
        OVEN_OFF(true),
        OVEN_ON(false),
        COUNTER_IC(false),
        COUNTER_OC(false);

        private final boolean isReal;

        Cookware(boolean isReal)
        {
            this.isReal = isReal;
        }

        @Override
        public String getName()
        {
            return toString().toLowerCase(Locale.ENGLISH);
        }
    }

    public BlockCookware()
    {
        super(Material.PISTON, Cookware.class, HFTab.COOKING);
        setHardness(2.5F);
        setSoundType(SoundType.METAL);
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState()
    {
        if (property == null)
            return new BlockStateContainer(this, temporary, FACING);
        return new BlockStateContainer(this, property, FACING);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean canRenderInLayer(IBlockState state, @Nonnull BlockRenderLayer layer)
    {
        Cookware cookware = getEnumFromState(state); //Yayayayayyayayayyayyyyyyyyyyyyyyyyyyyyyyyyyyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaayya
        return cookware != Cookware.FRIDGE_TOP && (cookware == Cookware.MIXER ? layer == BlockRenderLayer.TRANSLUCENT : layer == BlockRenderLayer.CUTOUT_MIPPED);
    }

    @Override
    public String getToolType(Cookware cookware)
    {
        return cookware == Cookware.COUNTER ? "axe" : "pickaxe";
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public Material getMaterial(IBlockState state)
    {
        return getEnumFromState(state) == Cookware.COUNTER ? Material.WOOD : super.getMaterial(state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public boolean shouldSideBeRendered(IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, EnumFacing side)
    {
        if (getEnumFromState(state) == Cookware.FRIDGE)
        {
            AxisAlignedBB axisalignedbb = state.getBoundingBox(world, pos);
            switch (side)
            {
            case DOWN:
                if (axisalignedbb.minY > 0.0D)
                {
                    return true;
                }

                break;
            case UP:
                if (axisalignedbb.maxY < 1.0D)
                {
                    return true;
                }

                break;
            case NORTH:
                if (axisalignedbb.minZ > 0.0D)
                {
                    return true;
                }

                break;
            case SOUTH:
                if (axisalignedbb.maxZ < 1.0D)
                {
                    return true;
                }

                break;
            case WEST:
                if (axisalignedbb.minX > 0.0D)
                {
                    return true;
                }

                break;
            case EAST:
                if (axisalignedbb.maxX < 1.0D)
                {
                    return true;
                }
            }

            return !(world.getBlockState(pos.offset(side)).doesSideBlockRendering(world, pos.offset(side), side.getOpposite()) && world.getBlockState(pos.offset(side).up()).doesSideBlockRendering(world, pos.offset(side).up(), side.getOpposite()));
        }
        else
            return super.shouldSideBeRendered(state, world, pos, side);
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        Cookware cookware = getEnumFromState(state);
        switch (cookware)
        {
        case FRYING_PAN:
            return FRYING_PAN_AABB;
        case POT:
            return POT_AABB;
        case FRIDGE:
            return new AxisAlignedBB(0F, 0F, 0F, 1F, 2F, 1F);
        case FRIDGE_TOP:
            return new AxisAlignedBB(0F, -1F, 0F, 1F, 1F, 1F);
        case MIXER:
            return MIXER_AABB;
        default:
            return FULL_BLOCK_AABB;
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        Cookware cookware = getEnumFromState(state);
        if (player.isSneaking())
            return false;
        else if (cookware == Cookware.FRIDGE || cookware == Cookware.FRIDGE_TOP)
        {
            int y = cookware == Cookware.FRIDGE_TOP ? pos.getY() - 1 : pos.getY();
            if (world.isRemote)
            {
                TileFridge fridge = ((TileFridge) world.getTileEntity(new BlockPos(pos.getX(), y, pos.getZ())));
                if (fridge != null)
                {
                    if (cookware == Cookware.FRIDGE_TOP)
                        fridge.animatingTop = true;
                    else if (cookware == Cookware.FRIDGE)
                        fridge.animatingBottom = true;
                }
            }

            player.openGui(HarvestFestival.instance, GuiHandler.FRIDGE, world, pos.getX(), y, pos.getZ());
            return true;
        }

        //Cooking System
        ItemStack held = player.getHeldItem(hand);
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileCooking)
        {
            TileCooking cooking = (TileCooking) tile;
            if (cooking.isFinishedCooking())
            {
                if (!HFApi.cooking.isKnife(held))
                {
                    cooking.giveToPlayer(player);
                    return true;
                }
            }
            else if (!held.isEmpty())
            {
                if (HFApi.cooking.isKnife(held))
                {
                    if (cookware == Cookware.COUNTER || world.getTileEntity(pos.down()) instanceof TileCounter)
                    {
                        cooking = cookware == Cookware.COUNTER ? cooking : (TileCooking) world.getTileEntity(pos.down());
                        if (cooking != null)
                        {
                            cooking.update(); //Activate
                            return true;
                        }
                    }

                    return false;
                }
                else if (!isCookware(held))
                {
                    if (cooking.addIngredient(held))
                    {
                        if (!player.capabilities.isCreativeMode)
                        {
                            player.inventory.decrStackSize(player.inventory.currentItem, 1);
                        }

                        return true;
                    }
                }
            }
            else
            {
                cooking.takeBackLastStack(player);
            }
        }

        return false;
    }

    private static boolean isCookware(@Nonnull ItemStack stack)
    {
        if (cookware == null)
            cookware = Item.getItemFromBlock(HFCooking.COOKWARE);
        return stack.getItem() == cookware;
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileFridge)
        {
            world.setBlockState(pos.up(), getStateFromEnum(Cookware.FRIDGE_TOP), 2);
        }
    }

    @Override
    public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state)
    {
        Cookware cookware = getEnumFromState(state);
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileCooking)
        {
            TileCooking cooking = ((TileCooking) tile);
            SpawnItemHelper.spawnItemStack(world, pos, cooking.getIngredients());
            SpawnItemHelper.spawnItemStack(world, pos, cooking.getResult());
            world.updateComparatorOutputLevel(pos, this);
        }
        else if (tile instanceof TileFridge)
        {
            TileFridge fridge = ((TileFridge) tile);
            InventoryHelper.dropInventoryItems(world, pos, fridge.getContents());
            world.updateComparatorOutputLevel(pos, this);
        }

        if (cookware == Cookware.FRIDGE_TOP)
        {
            world.setBlockToAir(pos.down());
        }
        else if (cookware == Cookware.FRIDGE)
        {
            world.setBlockToAir(pos.up());
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return getEnumFromState(state) == Cookware.FRIDGE_TOP ? Cookware.FRIDGE.ordinal() : super.damageDropped(state);
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return getEnumFromState(state) != Cookware.FRIDGE_TOP;
    }

    @Override
    @Nonnull
    public IBlockState getActualState(@Nonnull IBlockState state, IBlockAccess world, BlockPos pos)
    {
        IBlockState ret = super.getActualState(state, world, pos);
        Cookware cookware = getEnumFromState(ret);
        if (cookware == Cookware.OVEN_OFF || cookware == Cookware.OVEN_ON)
        {
            TileEntity tile = world instanceof ChunkCache ? ((ChunkCache) world).getTileEntity(pos.up(), Chunk.EnumCreateEntityType.CHECK) : world.getTileEntity(pos.up());
            if (tile instanceof TileHeatable)
            {
                if (((TileHeatable) tile).isCooking())
                {
                    return ret.withProperty(property, Cookware.OVEN_ON);
                }
            }

            return ret.withProperty(property, Cookware.OVEN_OFF);
        }
        else if (cookware == Cookware.COUNTER)
        {
            EnumFacing northFacing = getFacing(EnumFacing.NORTH, world, pos);
            EnumFacing eastFacing = getFacing(EnumFacing.EAST, world, pos);
            EnumFacing southFacing = getFacing(EnumFacing.SOUTH, world, pos);
            EnumFacing westFacing = getFacing(EnumFacing.WEST, world, pos);

            //Inner Corner
            if (northFacing == EnumFacing.WEST && westFacing == EnumFacing.NORTH)
                return state.withProperty(property, Cookware.COUNTER_IC).withProperty(FACING, EnumFacing.WEST);
            if (southFacing == EnumFacing.WEST && westFacing == EnumFacing.SOUTH)
                return state.withProperty(property, Cookware.COUNTER_IC).withProperty(FACING, EnumFacing.SOUTH);
            if (southFacing == EnumFacing.EAST && eastFacing == EnumFacing.SOUTH)
                return state.withProperty(property, Cookware.COUNTER_IC).withProperty(FACING, EnumFacing.EAST);
            if (northFacing == EnumFacing.EAST && eastFacing == EnumFacing.NORTH)
                return state.withProperty(property, Cookware.COUNTER_IC).withProperty(FACING, EnumFacing.NORTH);

            //Outer Corner
            if (northFacing == EnumFacing.EAST && westFacing == EnumFacing.SOUTH)
                return state.withProperty(property, Cookware.COUNTER_OC).withProperty(FACING, EnumFacing.EAST);
            if (southFacing == EnumFacing.EAST && westFacing == EnumFacing.NORTH)
                return state.withProperty(property, Cookware.COUNTER_OC).withProperty(FACING, EnumFacing.NORTH);
            if (southFacing == EnumFacing.WEST && eastFacing == EnumFacing.NORTH)
                return state.withProperty(property, Cookware.COUNTER_OC).withProperty(FACING, EnumFacing.WEST);
            if (northFacing == EnumFacing.WEST && eastFacing == EnumFacing.SOUTH)
                return state.withProperty(property, Cookware.COUNTER_OC).withProperty(FACING, EnumFacing.SOUTH);
        }

        return ret;
    }

    public EnumFacing getFacing(EnumFacing facing, IBlockAccess world, BlockPos pos)
    {
        TileEntity tile = world.getTileEntity(pos.offset(facing));
        if (tile instanceof TileCounter || tile instanceof TileOven || tile instanceof TileFridge)
        {
            return ((IFaceable) tile).getFacing();
        }

        return EnumFacing.DOWN;
    }

    @Override
    @SuppressWarnings("all")
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state)
    {
        Cookware cookware = getEnumFromState(state);
        switch (cookware)
        {
        case FRIDGE:
            return new TileFridge();
        case COUNTER:
            return new TileCounter();
        case POT:
            return new TilePot();
        case FRYING_PAN:
            return new TileFryingPan();
        case MIXER:
            return new TileMixer();
        case OVEN_OFF:
            return new TileOven();
        case OVEN_ON:
            return new TileOven();
        default:
            return null;
        }
    }

    @Override
    public int getSortValue(@Nonnull ItemStack stack)
    {
        return 99;
    }

    @Override
    protected boolean shouldDisplayInCreative(Cookware cookware)
    {
        return cookware.isReal;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModels(Item item, String name)
    {
        for (int i = 0; i < values.length; i++)
        {
            if (values[i] == Cookware.FRIDGE || values[i] == Cookware.FRIDGE_TOP)
            {
                ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(getRegistryName(), "inventory_fridge"));
            }
            else if (values[i] == Cookware.OVEN_ON || values[i] == Cookware.OVEN_OFF)
            {
                ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(getRegistryName(), "inventory_oven"));
            }
            else
                ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(getRegistryName(), property.getName() + "=" + getEnumFromMeta(i).getName() + ",facing=north"));
        }
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        Cookware cookware = getEnumFromState(state);
        switch (cookware)
        {
        case MIXER:
        case POT:
        case FRYING_PAN:
            return BlockFaceShape.UNDEFINED;

        default:
            return BlockFaceShape.SOLID;
        }
    }
}
