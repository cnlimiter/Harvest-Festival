package joshie.harvest.core.util.interfaces;

import java.util.Map;

import net.minecraft.nbt.NBTBase;
import net.minecraftforge.common.util.INBTSerializable;

public interface INBTSerializableMap<R, T, N extends NBTBase> extends INBTSerializable<N> {
	void buildMap(Map<R, T> map);
}
