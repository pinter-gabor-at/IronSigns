package eu.pintergabor.ironsigns;

import eu.pintergabor.ironsigns.config.ModConfigData;
import eu.pintergabor.ironsigns.main.Main;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.jspecify.annotations.NonNull;


/**
 * Common startup.
 */
@Mod(Global.MODID)
public final class ModCommon {

	public ModCommon(
		@NonNull IEventBus modEventBus,
		@NonNull ModContainer modContainer,
		@NonNull Dist dist
	) {
		// Use configuration parameters on both sides and load them on startup.
		modContainer.registerConfig(ModConfig.Type.STARTUP, ModConfigData.SPEC);
		// Create and register everything.
		Main.init(modEventBus);
	}
}
