package joshie.harvest.calendar.command;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import joshie.harvest.api.calendar.Weather;
import joshie.harvest.calendar.data.CalendarServer;
import joshie.harvest.core.HFTrackers;
import joshie.harvest.core.commands.CommandManager.CommandLevel;
import joshie.harvest.core.commands.HFCommand;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

@HFCommand
@SuppressWarnings("unused")
public class HFCommandWeather extends CommandBase {
	@Override
	@Nonnull
	public String getName() {
		return "weather";
	}

	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "/hf weather <sunny|rain|snow|typhoon|blizzard>";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return CommandLevel.OP_AFFECT_GAMEPLAY.ordinal();
	}

	@Override
	public void execute(
			@Nonnull MinecraftServer server,
			@Nonnull ICommandSender sender,
			@Nonnull String[] parameters) throws CommandException {
		World world = sender.getEntityWorld();
		CalendarServer calendar = HFTrackers.getCalendar(world);
		if (parameters.length == 0) {
			notifyCommandListener(sender, this, "Weather: " + calendar.getTodaysWeather());
			notifyCommandListener(sender, this, "LastWeather: " + calendar.getRecentNonSunnyWeather());
			notifyCommandListener(sender, this, "RainingStrength: " + world.rainingStrength);
			notifyCommandListener(sender, this, "ThunderingStrength: " + world.thunderingStrength);
		} else if (parameters.length == 1) {
			for (Weather weather : Weather.VALUES) {
				if (StringUtils.equalsIgnoreCase(weather.name(), parameters[0])) {
					calendar.setTodaysWeather(weather);
					return;
				}
			}
		}
		throw new WrongUsageException(getUsage(sender));
	}
}