package eu.pintergabor.ironsigns;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Global {

	// Used for logging and registration
	public static final String MODID = "ironsigns";

	// This logger is used to write text to the console and the log file.
	@SuppressWarnings("unused")
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	/**
	 * Create a mod specific identifier
	 * @param path Name, as in lang/*.json files without "*.modid." prefix
	 */
	public static Identifier ModIdentifier(String path) {
		return Identifier.of(MODID, path);
	}
}
