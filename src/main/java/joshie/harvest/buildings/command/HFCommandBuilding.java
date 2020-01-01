package joshie.harvest.buildings.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import joshie.harvest.core.commands.HFCommand;
import joshie.harvest.town.TownHelper;
import joshie.harvest.town.data.TownBuilding;
import joshie.harvest.town.data.TownData;
import joshie.harvest.town.data.TownDataServer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

@HFCommand
public class HFCommandBuilding extends CommandBase {

    @Override
    public String getName() {
        return "building";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/hf building <show|remove>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        BlockPos pos = sender.getPosition();
        World world = sender.getEntityWorld();
        TownDataServer town = TownHelper.getClosestTownToBlockPos(world, pos, false);
        if (args.length == 1 && args[0].equals("show")) {
            town.getBuildings().forEach(building -> {
                sender.sendMessage(new TextComponentString(((TownBuilding) building).building.getResource().toString()));
            });
            return;
        }
        if (args.length == 2 && args[0].equals("remove")) {
            try {
                ResourceLocation id = new ResourceLocation(args[1]);
                if (!town.hasBuilding(id)) {
                    sender.sendMessage(new TextComponentString("找不到建筑：" + id));
                    return;
                }
                town.removeBuilding(town.getBuilding(id));
                sender.sendMessage(new TextComponentString("已移除建筑：" + id));
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        if (args.length == 1) {
            return Arrays.asList("show", "remove");
        }
        if (args.length == 2 && args[0].equals("remove")) {
            BlockPos pos = sender.getPosition();
            World world = sender.getEntityWorld();
            TownData town = TownHelper.getClosestTownToBlockPos(world, pos, false);
            return ((Collection<TownBuilding>) town.getBuildings()).stream().map(b -> b.building.getResource().toString()).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
