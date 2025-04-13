package joshie.harvest.core.proxy;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import joshie.harvest.HarvestFestival;
import joshie.harvest.core.HFApiLoader;
import joshie.harvest.core.helpers.ConfigHelper;
import joshie.harvest.core.util.annotations.HFLoader;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;

public class HFCommonProxy {
	private static final List<Class<?>> LIST = new ArrayList<>();

	public List<Class<?>> getList() {
		return LIST;
	}

	public void setup(@Nonnull ASMDataTable table) {
		List<Triple<Integer, String, String>> unsorted = new ArrayList<>();
		Set<ASMData> datas = new HashSet<>(table.getAll(HFLoader.class.getCanonicalName()));
		for (ASMDataTable.ASMData data : datas) {
			try {
				String clazz = data.getClassName();
				Map<String, Object> map = data.getAnnotationInfo();
				String mods = map.get("mods") != null ? (String) map.get("mods") : "";
				int value = mods.isEmpty() ? map.get("priority") != null ? (int) map.get("priority") : 1 : -5000;
				unsorted.add(Triple.of(value, mods, clazz));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//Now that we have gathered all the classes, let's sort them by priority
		Comparator<Triple<Integer, String, String>> priority = (str1, str2) -> str2.getLeft().compareTo(str1.getLeft());
		unsorted.sort(priority);

		//Add Everything to the real LIST
		triple:
		for (Triple<Integer, String, String> entry : unsorted) {
			try {
				if (!entry.getMiddle().isEmpty()) {
					String[] mods = entry.getMiddle().replace(" ", "").split(",");
					for (String mod : mods) {
						if (!isModLoaded(mod)) {
							continue triple;
						}
					}
				}

				LIST.add(Class.forName(entry.getRight()));
			} catch (Exception e) {
				HarvestFestival.LOGGER.log(Level.ERROR, "Harvest Festival failed to load the following class: " + entry.getMiddle());
				HarvestFestival.LOGGER.log(
						Level.ERROR,
						"If this a mod related class, try updating your version of that mod before reporting");
			}
		}
	}

	private boolean isModLoaded(String mod) {
		return Loader.isModLoaded(mod) || Loader.isModLoaded(mod.toLowerCase(Locale.ENGLISH));
	}

	public void setupConfig(File file) {
		ConfigHelper.setConfig(new Configuration(file));
	}

	public void configure() {
		Configuration config = ConfigHelper.getConfig();
		for (Class<?> c : LIST) {
			try {
				Method configure = c.getMethod("configure");
				try {
					config.load();
					ConfigHelper.setCategory(c.getSimpleName().replace("HF", ""));
					configure.invoke(null);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				} finally {
					if (config.hasChanged()) {
						config.save();
					}
				}
			} catch (NoSuchMethodException ex) { /**/ }
		}
	}

	public void load(String stage) {
		//Continue
		for (Class<?> c : LIST) {
			try { //Attempt to load default
				c.getMethod(stage).invoke(null);
			} catch (NoClassDefFoundError | NoSuchMethodException ignored) {
			} catch (Exception e) {
				HarvestFestival.LOGGER.error("Harvest Festival failed to load the following class: " + c.getSimpleName());
				throw new RuntimeException(e);
			}

			//Attempt to load client side only
			if (isClient()) {
				try { //Attempt to load default
					c.getMethod(stage + "Client").invoke(null);
				} catch (NoClassDefFoundError | NoSuchMethodException ignored) {
				} catch (Exception e) {
					HarvestFestival.LOGGER.error("Harvest Festival failed to load the following class: " + c.getSimpleName());
					throw new RuntimeException(e);
				}
			}
		}
	}

	public boolean isClient() {
		return false;
	}

	public void loadAPI(ASMDataTable data) {
		HFApiLoader.load(data, isClient());
	}
}
