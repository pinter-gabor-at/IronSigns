package eu.pintergabor.ironsigns;

import eu.pintergabor.ironsigns.main.CreativeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.jspecify.annotations.NonNull;


/**
 * Client side startup.
 */
@Mod(value = Global.MODID, dist = Dist.CLIENT)
public final class ModClient {

	public ModClient(
		@NonNull IEventBus modEventBus,
		@NonNull ModContainer modContainer
	) {
		// Config screen.
		modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
		// Entity renderers and textures.
		modEventBus.addListener(ModClientSetup::listen);
		// Creative tabs.
		modEventBus.addListener(CreativeTabs::listen);
		// Data generator.
		modEventBus.addListener(ModDataGenerator::listen0);
		modEventBus.addListener(ModDataGenerator::listen1);
	}
}
