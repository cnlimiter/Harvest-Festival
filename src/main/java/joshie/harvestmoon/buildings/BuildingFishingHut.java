package joshie.harvestmoon.buildings;

import java.util.ArrayList;

import joshie.harvestmoon.blocks.BlockWood;
import joshie.harvestmoon.buildings.placeable.blocks.PlaceableBlock;
import joshie.harvestmoon.buildings.placeable.blocks.PlaceableButton;
import joshie.harvestmoon.buildings.placeable.blocks.PlaceableDoor;
import joshie.harvestmoon.buildings.placeable.blocks.PlaceableFlowerPot;
import joshie.harvestmoon.buildings.placeable.blocks.PlaceableIFaceable;
import joshie.harvestmoon.buildings.placeable.blocks.PlaceableLadder;
import joshie.harvestmoon.buildings.placeable.blocks.PlaceableLog;
import joshie.harvestmoon.buildings.placeable.blocks.PlaceableStairs;
import joshie.harvestmoon.buildings.placeable.blocks.PlaceableTorches;
import joshie.harvestmoon.buildings.placeable.blocks.PlaceableTrapDoor;
import joshie.harvestmoon.buildings.placeable.blocks.PlaceableVine;
import joshie.harvestmoon.buildings.placeable.entities.PlaceableItemFrame;
import joshie.harvestmoon.buildings.placeable.entities.PlaceableNPC;
import joshie.harvestmoon.buildings.placeable.entities.PlaceablePainting;
import joshie.harvestmoon.core.helpers.TownHelper;
import joshie.harvestmoon.core.lib.LootStrings;
import joshie.harvestmoon.init.HMBlocks;
import joshie.harvestmoon.init.HMBuildings;
import joshie.harvestmoon.player.Town;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BuildingFishingHut extends Building {
    public BuildingFishingHut() {
        super("fishingHut");
        offsetY = -1;
        list = new ArrayList(345);
        list.add(new PlaceableBlock(Blocks.planks, 5, 2, 0, 1));
        list.add(new PlaceableBlock(Blocks.planks, 5, 2, 0, 5));
        list.add(new PlaceableBlock(Blocks.planks, 5, 3, 0, 0));
        list.add(new PlaceableLog(Blocks.log, 1, 3, 0, 1));
        list.add(new PlaceableBlock(Blocks.planks, 5, 3, 0, 2));
        list.add(new PlaceableBlock(Blocks.planks, 5, 3, 0, 3));
        list.add(new PlaceableBlock(Blocks.planks, 5, 3, 0, 4));
        list.add(new PlaceableLog(Blocks.log, 1, 3, 0, 5));
        list.add(new PlaceableBlock(Blocks.planks, 5, 3, 0, 6));
        list.add(new PlaceableBlock(Blocks.planks, 1, 4, 0, 1));
        list.add(new PlaceableBlock(Blocks.planks, 5, 4, 0, 2));
        list.add(new PlaceableBlock(Blocks.planks, 1, 4, 0, 3));
        list.add(new PlaceableBlock(Blocks.planks, 5, 4, 0, 4));
        list.add(new PlaceableBlock(Blocks.planks, 1, 4, 0, 5));
        list.add(new PlaceableBlock(Blocks.planks, 5, 5, 0, 0));
        list.add(new PlaceableLog(Blocks.log, 1, 5, 0, 1));
        list.add(new PlaceableBlock(Blocks.planks, 5, 5, 0, 2));
        list.add(new PlaceableBlock(Blocks.planks, 1, 5, 0, 3));
        list.add(new PlaceableBlock(Blocks.planks, 5, 5, 0, 4));
        list.add(new PlaceableBlock(Blocks.planks, 1, 5, 0, 5));
        list.add(new PlaceableBlock(Blocks.planks, 5, 6, 0, 1));
        list.add(new PlaceableBlock(Blocks.planks, 1, 6, 0, 2));
        list.add(new PlaceableBlock(Blocks.planks, 1, 6, 0, 3));
        list.add(new PlaceableBlock(Blocks.planks, 1, 6, 0, 4));
        list.add(new PlaceableLog(Blocks.log, 1, 6, 0, 5));
        list.add(new PlaceableBlock(Blocks.planks, 5, 6, 0, 6));
        list.add(new PlaceableBlock(Blocks.planks, 5, 7, 0, 0));
        list.add(new PlaceableLog(Blocks.log, 1, 7, 0, 1));
        list.add(new PlaceableBlock(Blocks.planks, 5, 7, 0, 2));
        list.add(new PlaceableBlock(Blocks.planks, 1, 7, 0, 3));
        list.add(new PlaceableBlock(Blocks.planks, 5, 7, 0, 4));
        list.add(new PlaceableBlock(Blocks.planks, 1, 7, 0, 5));
        list.add(new PlaceableBlock(Blocks.planks, 1, 8, 0, 1));
        list.add(new PlaceableBlock(Blocks.planks, 5, 8, 0, 2));
        list.add(new PlaceableBlock(Blocks.planks, 5, 8, 0, 3));
        list.add(new PlaceableBlock(Blocks.planks, 5, 8, 0, 4));
        list.add(new PlaceableBlock(Blocks.planks, 1, 8, 0, 5));
        list.add(new PlaceableBlock(Blocks.planks, 5, 9, 0, 0));
        list.add(new PlaceableLog(Blocks.log, 1, 9, 0, 1));
        list.add(new PlaceableBlock(Blocks.planks, 1, 9, 0, 2));
        list.add(new PlaceableBlock(Blocks.planks, 1, 9, 0, 3));
        list.add(new PlaceableBlock(Blocks.planks, 1, 9, 0, 4));
        list.add(new PlaceableLog(Blocks.log, 1, 9, 0, 5));
        list.add(new PlaceableBlock(Blocks.planks, 5, 9, 0, 6));
        list.add(new PlaceableBlock(Blocks.planks, 5, 10, 0, 1));
        list.add(new PlaceableBlock(Blocks.planks, 5, 10, 0, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 0, 2, 1, 1));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 14, 2, 1, 2));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 14, 2, 1, 3));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 14, 2, 1, 4));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 0, 2, 1, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 3, 1, 0));
        list.add(new PlaceableLog(Blocks.log, 1, 3, 1, 1));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 3, 1, 2));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 3, 1, 3));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 3, 1, 4));
        list.add(new PlaceableLog(Blocks.log, 1, 3, 1, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 3, 3, 1, 6));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 12, 4, 1, 0));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 4, 1, 1));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 11, 4, 1, 2));
        list.add(new PlaceableBlock(Blocks.cauldron, 3, 4, 1, 3));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 11, 4, 1, 4));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 4, 1, 5));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 13, 4, 1, 6));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 5, 1, 0));
        list.add(new PlaceableLog(Blocks.log, 1, 5, 1, 1));
        list.add(new PlaceableVine(Blocks.vine, 4, 5, 1, 2));
        list.add(new PlaceableNPC("jacob", 5, 1, 3));
        npc_offsets.put(Town.JACOB, new PlaceableNPC("", 5, 1, 3));
        list.add(new PlaceableBlock(Blocks.air, 0, 5, 1, 3));
        list.add(new PlaceableFlowerPot(Blocks.flower_pot, 6, 5, 1, 4));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 5, 1, 5));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 13, 5, 1, 6));
        list.add(new PlaceableDoor(Blocks.wooden_door, 3, 6, 1, 1));
        list.add(new PlaceableBlock(Blocks.fence, 0, 6, 1, 4));
        list.add(new PlaceableLog(Blocks.log, 1, 6, 1, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 3, 6, 1, 6));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 7, 1, 0));
        list.add(new PlaceableLog(Blocks.log, 1, 7, 1, 1));
        list.add(new PlaceableVine(Blocks.vine, 4, 7, 1, 2));
        list.add(new PlaceableLadder(Blocks.ladder, 2, 7, 1, 4));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 7, 1, 5));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 13, 7, 1, 6));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 12, 8, 1, 0));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 8, 1, 1));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 7, 8, 1, 2));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 4, 8, 1, 3));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 6, 8, 1, 4));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 8, 1, 5));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 13, 8, 1, 6));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 9, 1, 0));
        list.add(new PlaceableLog(Blocks.log, 1, 9, 1, 1));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 9, 1, 2));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 9, 1, 3));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 9, 1, 4));
        list.add(new PlaceableLog(Blocks.log, 1, 9, 1, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 3, 9, 1, 6));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 1, 10, 1, 1));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 15, 10, 1, 2));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 15, 10, 1, 3));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 15, 10, 1, 4));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 1, 10, 1, 5));
        list.add(new PlaceableButton(Blocks.wooden_button, 2, 2, 2, 1));
        list.add(new PlaceableVine(Blocks.vine, 8, 2, 2, 2));
        list.add(new PlaceableVine(Blocks.vine, 8, 2, 2, 4));
        list.add(new PlaceableButton(Blocks.wooden_button, 2, 2, 2, 5));
        list.add(new PlaceableButton(Blocks.wooden_button, 4, 3, 2, 0));
        list.add(new PlaceableLog(Blocks.log, 1, 3, 2, 1));
        list.add(new PlaceableBlock(Blocks.planks, 1, 3, 2, 2));
        list.add(new PlaceableBlock(Blocks.stained_glass_pane, 0, 3, 2, 3));
        list.add(new PlaceableBlock(Blocks.planks, 1, 3, 2, 4));
        list.add(new PlaceableLog(Blocks.log, 1, 3, 2, 5));
        list.add(new PlaceableButton(Blocks.wooden_button, 3, 3, 2, 6));
        list.add(new PlaceableBlock(Blocks.planks, 1, 4, 2, 1));
        list.add(new PlaceableTorches(Blocks.lever, 1, 4, 2, 2));
        list.add(new PlaceableTorches(Blocks.lever, 1, 4, 2, 4));
        list.add(new PlaceableBlock(Blocks.planks, 1, 4, 2, 5));
        list.add(new PlaceableTorches(Blocks.torch, 4, 5, 2, 0));
        list.add(new PlaceableItemFrame(new ItemStack(Blocks.trapdoor, 1, 0), 0, 2, 5, 2, 0));
        list.add(new PlaceableLog(Blocks.log, 1, 5, 2, 1));
        list.add(new PlaceableVine(Blocks.vine, 4, 5, 2, 2));
        list.add(new PlaceableVine(Blocks.vine, 1, 5, 2, 4));
        list.add(new PlaceableBlock(Blocks.planks, 1, 5, 2, 5));
        list.add(new PlaceableVine(Blocks.vine, 4, 5, 2, 6));
        list.add(new PlaceableDoor(Blocks.wooden_door, 8, 6, 2, 1));
        list.add(new PlaceableTorches(Blocks.torch, 5, 6, 2, 4));
        list.add(new PlaceableLog(Blocks.log, 1, 6, 2, 5));
        list.add(new PlaceableButton(Blocks.wooden_button, 3, 6, 2, 6));
        list.add(new PlaceableTorches(Blocks.torch, 4, 7, 2, 0));
        list.add(new PlaceableItemFrame(new ItemStack(Blocks.trapdoor, 1, 0), 0, 2, 7, 2, 0));
        list.add(new PlaceableLog(Blocks.log, 1, 7, 2, 1));
        list.add(new PlaceableLadder(Blocks.ladder, 2, 7, 2, 4));
        list.add(new PlaceableBlock(Blocks.planks, 1, 7, 2, 5));
        list.add(new PlaceableVine(Blocks.vine, 4, 7, 2, 6));
        list.add(new PlaceableVine(Blocks.vine, 1, 8, 2, 0));
        list.add(new PlaceableBlock(Blocks.planks, 1, 8, 2, 1));
        list.add(new PlaceableFlowerPot(Blocks.flower_pot, 5, 8, 2, 2));
        list.add(new PlaceableBlock(Blocks.wooden_pressure_plate, 0, 8, 2, 3));
        list.add(new PlaceableIFaceable(HMBlocks.woodmachines, BlockWood.RURAL_CHEST, 8, 2, 4, ForgeDirection.WEST, LootStrings.FISHING_CHEST));
        list.add(new PlaceableBlock(Blocks.planks, 1, 8, 2, 5));
        list.add(new PlaceableVine(Blocks.vine, 4, 8, 2, 6));
        list.add(new PlaceableButton(Blocks.wooden_button, 4, 9, 2, 0));
        list.add(new PlaceableLog(Blocks.log, 1, 9, 2, 1));
        list.add(new PlaceableBlock(Blocks.planks, 1, 9, 2, 2));
        list.add(new PlaceableBlock(Blocks.stained_glass_pane, 0, 9, 2, 3));
        list.add(new PlaceableBlock(Blocks.planks, 1, 9, 2, 4));
        list.add(new PlaceableLog(Blocks.log, 1, 9, 2, 5));
        list.add(new PlaceableButton(Blocks.wooden_button, 3, 9, 2, 6));
        list.add(new PlaceableButton(Blocks.wooden_button, 1, 10, 2, 1));
        list.add(new PlaceableVine(Blocks.vine, 2, 10, 2, 4));
        list.add(new PlaceableButton(Blocks.wooden_button, 1, 10, 2, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 4, 2, 3, 1));
        list.add(new PlaceableVine(Blocks.vine, 8, 2, 3, 2));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 4, 2, 3, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 6, 3, 3, 0));
        list.add(new PlaceableLog(Blocks.log, 1, 3, 3, 1));
        list.add(new PlaceableBlock(Blocks.planks, 1, 3, 3, 2));
        list.add(new PlaceableBlock(Blocks.stained_glass_pane, 0, 3, 3, 3));
        list.add(new PlaceableBlock(Blocks.planks, 1, 3, 3, 4));
        list.add(new PlaceableLog(Blocks.log, 1, 3, 3, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 7, 3, 3, 6));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 2, 4, 3, 1));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 9, 4, 3, 2));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 9, 4, 3, 3));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 8, 4, 3, 4));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 3, 4, 3, 5));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 2, 5, 3, 1));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 9, 5, 3, 2));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 9, 5, 3, 3));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 8, 5, 3, 4));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 3, 5, 3, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 6, 6, 3, 0));
        list.add(new PlaceableLog(Blocks.log, 1, 6, 3, 1));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 9, 6, 3, 2));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 9, 6, 3, 4));
        list.add(new PlaceableLog(Blocks.log, 1, 6, 3, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 7, 6, 3, 6));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 2, 7, 3, 1));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 9, 7, 3, 2));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 9, 7, 3, 3));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 9, 7, 3, 4));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 3, 7, 3, 5));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 2, 8, 3, 1));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 9, 8, 3, 2));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 9, 8, 3, 3));
        list.add(new PlaceableTrapDoor(Blocks.trapdoor, 8, 8, 3, 4));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 3, 8, 3, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 6, 9, 3, 0));
        list.add(new PlaceableLog(Blocks.log, 1, 9, 3, 1));
        list.add(new PlaceableBlock(Blocks.planks, 1, 9, 3, 2));
        list.add(new PlaceableBlock(Blocks.stained_glass_pane, 0, 9, 3, 3));
        list.add(new PlaceableBlock(Blocks.planks, 1, 9, 3, 4));
        list.add(new PlaceableLog(Blocks.log, 1, 9, 3, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 7, 9, 3, 6));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 5, 10, 3, 1));
        list.add(new PlaceableVine(Blocks.vine, 2, 10, 3, 4));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 5, 10, 3, 5));
        list.add(new PlaceableBlock(Blocks.fence, 0, 2, 4, 1));
        list.add(new PlaceableBlock(Blocks.fence, 0, 2, 4, 5));
        list.add(new PlaceableBlock(Blocks.fence, 0, 3, 4, 0));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 3, 4, 1));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 0, 3, 4, 2));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 4, 3, 4, 3));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 0, 3, 4, 4));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 3, 4, 5));
        list.add(new PlaceableBlock(Blocks.fence, 0, 3, 4, 6));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 6, 4, 4, 1));
        list.add(new PlaceableBlock(Blocks.double_wooden_slab, 5, 4, 4, 2));
        list.add(new PlaceableBlock(Blocks.wool, 0, 4, 4, 3));
        list.add(new PlaceableBlock(Blocks.double_wooden_slab, 5, 4, 4, 4));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 7, 4, 4, 5));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 6, 5, 4, 1));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 5, 4, 2));
        list.add(new PlaceableBlock(Blocks.wool, 9, 5, 4, 3));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 5, 4, 4));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 7, 5, 4, 5));
        list.add(new PlaceableBlock(Blocks.fence, 0, 6, 4, 0));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 6, 4, 1));
        list.add(new PlaceableBlock(Blocks.double_wooden_slab, 1, 6, 4, 2));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 6, 4, 3));
        list.add(new PlaceableBlock(Blocks.double_wooden_slab, 1, 6, 4, 4));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 6, 4, 5));
        list.add(new PlaceableBlock(Blocks.fence, 0, 6, 4, 6));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 6, 7, 4, 1));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 7, 4, 2));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 7, 7, 4, 5));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 6, 8, 4, 1));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 8, 4, 2));
        list.add(new PlaceableLadder(Blocks.ladder, 4, 8, 4, 3));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 8, 4, 4));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 7, 8, 4, 5));
        list.add(new PlaceableBlock(Blocks.fence, 0, 9, 4, 0));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 9, 4, 1));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 1, 9, 4, 2));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 5, 9, 4, 3));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 1, 9, 4, 4));
        list.add(new PlaceableBlock(Blocks.stained_hardened_clay, 0, 9, 4, 5));
        list.add(new PlaceableBlock(Blocks.fence, 0, 9, 4, 6));
        list.add(new PlaceableBlock(Blocks.fence, 0, 10, 4, 1));
        list.add(new PlaceableBlock(Blocks.fence, 0, 10, 4, 5));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 13, 0, 5, 3));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 2, 5, 1));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 7, 2, 5, 2));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 6, 2, 5, 4));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 3, 2, 5, 5));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 3, 5, 0));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 2, 3, 5, 1));
        list.add(new PlaceableBlock(Blocks.planks, 1, 3, 5, 2));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 4, 3, 5, 3));
        list.add(new PlaceableBlock(Blocks.planks, 1, 3, 5, 4));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 3, 3, 5, 5));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 3, 5, 6));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 4, 5, 0));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 7, 4, 5, 1));
        list.add(new PlaceableTorches(Blocks.torch, 5, 4, 5, 2));
        list.add(new PlaceableBlock(Blocks.air, 0, 4, 5, 3));
        list.add(new PlaceablePainting("Bomb", 3, 4, 5, 3));
        list.add(new PlaceableFlowerPot(Blocks.flower_pot, 7, 4, 5, 4));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 6, 4, 5, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 3, 4, 5, 6));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 5, 5, 0));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 7, 5, 5, 1));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 6, 5, 5, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 3, 5, 5, 6));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 6, 5, 0));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 2, 6, 5, 1));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 7, 6, 5, 2));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 6, 6, 5, 4));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 3, 6, 5, 5));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 6, 5, 6));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 7, 5, 0));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 7, 7, 5, 1));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 6, 7, 5, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 3, 7, 5, 6));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 8, 5, 0));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 7, 8, 5, 1));
        list.add(new PlaceableButton(Blocks.wooden_button, 2, 8, 5, 2));
        list.add(new PlaceableTorches(Blocks.torch, 2, 8, 5, 3));
        list.add(new PlaceableItemFrame(new ItemStack(Blocks.trapdoor, 1, 0), 0, 1, 8, 5, 3));
        list.add(new PlaceableButton(Blocks.wooden_button, 2, 8, 5, 4));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 6, 8, 5, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 3, 8, 5, 6));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 9, 5, 0));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 2, 9, 5, 1));
        list.add(new PlaceableBlock(Blocks.planks, 1, 9, 5, 2));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 5, 9, 5, 3));
        list.add(new PlaceableBlock(Blocks.planks, 1, 9, 5, 4));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 3, 9, 5, 5));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 9, 5, 6));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 10, 5, 1));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 7, 10, 5, 2));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 6, 10, 5, 4));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 3, 10, 5, 5));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 13, 12, 5, 3));
        list.add(new PlaceableBlock(Blocks.fence, 0, 0, 6, 3));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 2, 6, 2));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 4, 2, 6, 3));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 3, 2, 6, 4));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 2, 3, 6, 2));
        list.add(new PlaceableBlock(Blocks.planks, 1, 3, 6, 3));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 3, 3, 6, 4));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 4, 6, 1));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 7, 4, 6, 2));
        list.add(new PlaceableBlock(Blocks.air, 0, 4, 6, 3));
        list.add(new PlaceableItemFrame(new ItemStack(Items.fish, 1, 1), 0, 3, 4, 6, 3, LootStrings.FISHING_FRAME));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 6, 4, 6, 4));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 3, 4, 6, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 5, 6, 1));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 7, 5, 6, 2));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 6, 5, 6, 4));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 3, 5, 6, 5));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 2, 6, 6, 2));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 3, 6, 6, 4));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 7, 6, 1));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 7, 7, 6, 2));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 6, 7, 6, 4));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 3, 7, 6, 5));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 8, 6, 1));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 7, 8, 6, 2));
        list.add(new PlaceableLadder(Blocks.ladder, 4, 8, 6, 3));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 6, 8, 6, 4));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 3, 8, 6, 5));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 2, 9, 6, 2));
        list.add(new PlaceableBlock(Blocks.planks, 1, 9, 6, 3));
        list.add(new PlaceableStairs(Blocks.spruce_stairs, 3, 9, 6, 4));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 2, 10, 6, 2));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 5, 10, 6, 3));
        list.add(new PlaceableStairs(Blocks.dark_oak_stairs, 3, 10, 6, 4));
        list.add(new PlaceableBlock(Blocks.fence, 0, 12, 6, 3));
        list.add(new PlaceableBlock(Blocks.fence, 0, 0, 7, 3));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 13, 1, 7, 3));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 2, 7, 3));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 1, 3, 7, 3));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 4, 7, 2));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 13, 4, 7, 3));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 4, 7, 4));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 5, 7, 2));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 13, 5, 7, 3));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 5, 7, 4));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 1, 6, 7, 3));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 7, 7, 2));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 13, 7, 7, 3));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 7, 7, 4));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 8, 7, 2));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 13, 8, 7, 3));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 8, 7, 4));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 1, 9, 7, 3));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 10, 7, 3));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 13, 11, 7, 3));
        list.add(new PlaceableBlock(Blocks.fence, 0, 12, 7, 3));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 0, 8, 3));
        list.add(new PlaceableBlock(Blocks.wooden_slab, 5, 12, 8, 3));
    }
    
    @Override
    public long getCost() {
        return 6000L;
    }
    
    @Override
    public int getWoodCount() {
        return 96;
    }
    
    @Override
    public int getStoneCount() {
        return 0;
    }
    
    @Override
    public boolean canBuy(World world, EntityPlayer player) {
        return TownHelper.hasBuilding(player, HMBuildings.miningHill) && TownHelper.hasBuilding(player, HMBuildings.miningHut) && TownHelper.hasBuilding(player, HMBuildings.goddessPond);
    }
}
