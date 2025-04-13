package joshie.harvest.buildings.loader;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.minecraft.util.ResourceLocation;


public class ResourceAdapter implements JsonSerializer<ResourceLocation>, JsonDeserializer<ResourceLocation> {
	@Override
	public JsonElement serialize(ResourceLocation src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.toString());
	}

	@Override
	public ResourceLocation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return new ResourceLocation(json.getAsJsonPrimitive().getAsString());
	}
}

