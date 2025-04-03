package eu.pintergabor.ironsigns;

import eu.pintergabor.ironsigns.config.ModConfig;
import eu.pintergabor.ironsigns.main.Main;

import net.fabricmc.api.ModInitializer;


public class Mod implements ModInitializer {

	@Override
	public void onInitialize() {
		ModConfig.init();
		Main.init();
	}
}
