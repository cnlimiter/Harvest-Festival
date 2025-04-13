package joshie.harvest.plugins.crafttweaker.command;

import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import crafttweaker.CraftTweakerAPI;
import javax.annotation.Nonnull;
import joshie.harvest.api.shops.Shop;
import joshie.harvest.core.commands.CommandManager.CommandLevel;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;

public class HFCommandShops extends CommandBase {
	@Override
	@Nonnull
	public String getName() {
		return "shops";
	}

	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "/hf shops";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return CommandLevel.ANYONE.ordinal();
	}

	@Override
	public void execute(
			@Nonnull MinecraftServer server,
			@Nonnull ICommandSender sender,
			@Nonnull String[] parameters) throws CommandException {
		CraftTweakerAPI.logCommand("Shops: \n" + this.getShopList().toString().replace("[", "").replace("]", "").replace(", ", "\n"));
		sender.sendMessage(new TextComponentString("List generated; see minetweaker.log in your minecraft dir"));
	}

	private List<ResourceLocation> getShopList() {
		return Shop.REGISTRY.keySet()
				.stream()
				.sorted(Comparator.comparing(ResourceLocation::toString))
				.collect(Collectors.toList());
	}
}