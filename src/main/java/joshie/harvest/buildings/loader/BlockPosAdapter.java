package joshie.harvest.buildings.loader;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.minecraft.util.math.BlockPos;


public class BlockPosAdapter implements JsonSerializer<BlockPos>, JsonDeserializer<BlockPos> {
	@Override
	public JsonElement serialize(BlockPos src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.getX() + " " + src.getY() + " " + src.getZ());
	}

	@Override
	public BlockPos deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		String[] pos = json.getAsString().split(" ");
		return new BlockPos(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), Integer.parseInt(pos[2]));
	}
}

