package eu.pintergabor.ironsigns;

import eu.pintergabor.ironsigns.config.ModConfigData;
import eu.pintergabor.ironsigns.main.Main;

import net.fabricmc.api.ModInitializer;


public class Mod implements ModInitializer {

	@Override
	public void onInitialize() {
		ModConfigData.init();
		Main.init();
	}
}
