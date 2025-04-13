package joshie.harvest.core.util;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import net.minecraft.util.ResourceLocation;

public class ResourceLoader {
	public static String getJSONResource(ResourceLocation id, String directory) {
		String s = id.getResourceDomain();
		String s1 = id.getResourcePath();
		InputStream inputstream = null;

		try {
			inputstream = ResourceLoader.class.getResourceAsStream("/assets/" + s + "/" + directory + "/" + s1 + ".json");
			return IOUtils.toString(inputstream);
		} catch (Throwable ignored) {
		} finally {
			IOUtils.closeQuietly(inputstream);
		}

		return "";
	}
}
