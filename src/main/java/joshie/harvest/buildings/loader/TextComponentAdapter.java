package joshie.harvest.buildings.loader;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;


public class TextComponentAdapter implements JsonSerializer<ITextComponent>, JsonDeserializer<ITextComponent> {
	@Override
	public JsonElement serialize(ITextComponent src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.getUnformattedText());
	}

	@Override
	public ITextComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		String text = json.getAsJsonPrimitive().getAsString();
		return text.startsWith("translate:") ? new TextComponentTranslation(text.replace("translate:", "")) : new TextComponentString(text);
	}
}

