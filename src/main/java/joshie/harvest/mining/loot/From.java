package joshie.harvest.mining.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import javax.annotation.Nonnull;
import joshie.harvest.core.lib.HFModInfo;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.conditions.LootCondition;

public class From extends FloorCondition {
	private final int from;

	public From(int from) {
		this.from = from;
	}

	@Override
	public boolean testFloor(int floor) {
		return floor >= from;
	}

	public static class Serializer extends LootCondition.Serializer<From> {
		public Serializer() {
			super(new ResourceLocation(HFModInfo.MODID, "from"), From.class);
		}

		@Override
		public void serialize(@Nonnull JsonObject json, @Nonnull From value, @Nonnull JsonSerializationContext context) {
			json.addProperty("from", value.from);
		}

		@Override
		@Nonnull
		public From deserialize(@Nonnull JsonObject json, @Nonnull JsonDeserializationContext context) {
			return new From(JsonUtils.getInt(json, "from", 0));
		}
	}
}
