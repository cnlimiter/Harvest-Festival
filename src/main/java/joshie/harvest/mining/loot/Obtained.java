package joshie.harvest.mining.loot;

import java.util.Random;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import javax.annotation.Nonnull;
import joshie.harvest.HarvestFestival;
import joshie.harvest.core.HFTrackers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;

public class Obtained implements LootCondition {
	@Nonnull
	private final ItemStack stack;

	public Obtained(Item item, int meta) {
		this.stack = new ItemStack(item, 1, meta);
	}

	@Override
	public boolean testCondition(@Nonnull Random rand, @Nonnull LootContext context) {
		EntityPlayer player = (EntityPlayer) context.getKillerPlayer();
		return player != null && HFTrackers.getPlayerTrackerFromPlayer(player).getTracking().hasObtainedItem(stack);
	}

	public static class Serializer extends LootCondition.Serializer<Obtained> {
		public Serializer() {
			super(HarvestFestival.id("obtained"), Obtained.class);
		}

		@Override
		public void serialize(@Nonnull JsonObject json, @Nonnull Obtained value, @Nonnull JsonSerializationContext context) {
			json.addProperty("item", value.stack.getItem().getRegistryName().toString());
			json.addProperty("meta", value.stack.getItemDamage());
		}

		@Override
		@Nonnull
		public Obtained deserialize(@Nonnull JsonObject json, @Nonnull JsonDeserializationContext context) {
			return new Obtained(JsonUtils.getItem(json, "item"), JsonUtils.getInt(json, "meta", 0));
		}
	}
}
