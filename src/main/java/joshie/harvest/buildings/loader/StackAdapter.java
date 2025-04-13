package joshie.harvest.buildings.loader;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import joshie.harvest.core.helpers.StackHelper;
import net.minecraft.item.ItemStack;


public class StackAdapter implements JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {
	@Override
	public JsonElement serialize(ItemStack src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(StackHelper.getStringFromStack(src));
	}

	@Override
	public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return StackHelper.getStackFromString(json.getAsString());
	}
}

