package joshie.harvest.calendar.packet;

import io.netty.buffer.ByteBuf;
import joshie.harvest.api.calendar.Weather;
import joshie.harvest.calendar.data.CalendarClient;
import joshie.harvest.core.HFTrackers;
import joshie.harvest.core.network.Packet;
import joshie.harvest.core.network.PenguinPacket;
import net.minecraft.entity.player.EntityPlayer;

@Packet(Packet.Side.CLIENT)
public class PacketSyncStrength extends PenguinPacket {
	private Weather weather;
	private int rain;
	private int storm;

	public PacketSyncStrength() {}

	public PacketSyncStrength(Weather weather, int rain, int storm) {
		this.weather = weather;
		this.rain = rain;
		this.storm = storm;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(weather.ordinal());
		buf.writeInt(rain);
		buf.writeInt(storm);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		weather = Weather.VALUES.get(buf.readByte());
		rain = buf.readInt();
		storm = buf.readInt();
	}

	@Override
	public void handlePacket(EntityPlayer player) {
		CalendarClient calendar = HFTrackers.getCalendar(player.world);
		calendar.setRecentNonSunnyWeather(weather);
		calendar.setStrength(rain, storm);
	}
}