package joshie.harvest.fishing.loot;

import java.util.Random;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import javax.annotation.Nonnull;
import joshie.harvest.calendar.CalendarHelper;
import joshie.harvest.core.lib.HFModInfo;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;

public class ConditionTime implements LootCondition {
	private final int from;
	private final int to;

	public ConditionTime(int from, int to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public boolean testCondition(@Nonnull Random rand, @Nonnull LootContext context) {
		return context.getLootedEntity() != null && CalendarHelper.isBetween(context.getLootedEntity().world, from, to);
	}

	public static class Serializer extends LootCondition.Serializer<ConditionTime> {
		public Serializer() {
			super(new ResourceLocation(HFModInfo.MODID, "time"), ConditionTime.class);
		}

		@Override
		public void serialize(@Nonnull JsonObject json, @Nonnull ConditionTime value, @Nonnull JsonSerializationContext context) {
			json.addProperty("from", value.from);
			json.addProperty("to", value.to);
		}

		@Override
		@Nonnull
		public ConditionTime deserialize(@Nonnull JsonObject json, @Nonnull JsonDeserializationContext context) {
			return new ConditionTime(JsonUtils.getInt(json, "from", 0), JsonUtils.getInt(json, "to", 24000));
		}
	}
}
