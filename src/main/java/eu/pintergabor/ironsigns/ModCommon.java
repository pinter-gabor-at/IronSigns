package eu.pintergabor.ironsigns;

import eu.pintergabor.ironsigns.config.ModConfig;
import eu.pintergabor.ironsigns.main.Main;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;


@Mod(Global.MODID)
public final class ModCommon {

	@SuppressWarnings("unused")
	public ModCommon(IEventBus modEventBus, ModContainer modContainer) {
		ModConfig.init();
		Main.init(modEventBus);
	}
}
