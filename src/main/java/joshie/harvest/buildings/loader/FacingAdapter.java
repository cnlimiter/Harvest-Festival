package joshie.harvest.buildings.loader;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.minecraft.util.EnumFacing;


public class FacingAdapter implements JsonSerializer<EnumFacing>, JsonDeserializer<EnumFacing> {
	@Override
	public JsonElement serialize(EnumFacing src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.name().toLowerCase());
	}

	@Override
	public EnumFacing deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return EnumFacing.valueOf(json.getAsString().toUpperCase());
	}
}

