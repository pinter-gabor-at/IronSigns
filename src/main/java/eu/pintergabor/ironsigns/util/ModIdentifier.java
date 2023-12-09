package eu.pintergabor.ironsigns.util;

import eu.pintergabor.ironsigns.Global;
import net.minecraft.util.Identifier;

public class ModIdentifier extends Identifier {
	/**
	 * Create a mod specific identifier
	 * @param path Name, as in lang/*.json files
	 */
	public ModIdentifier(String path) {
		super(Global.MODID, path);
	}
}
