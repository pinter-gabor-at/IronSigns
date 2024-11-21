package eu.pintergabor.ironsigns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.resources.ResourceLocation;


public final class Global {

	// Used for logging and registration.
	public static final String MODID = "ironsigns";

	// This logger is used to write text to the console and the log file.
	@SuppressWarnings("unused")
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	/**
	 * Create a mod specific identifier.
	 *
	 * @param path Name, as in lang/*.json files without "*.modid." prefix.
	 */
	public static ResourceLocation ModIdentifier(String path) {
		return ResourceLocation.fromNamespaceAndPath(MODID, path);
	}
}
