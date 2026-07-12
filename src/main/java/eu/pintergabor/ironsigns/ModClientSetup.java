package eu.pintergabor.ironsigns;

import eu.pintergabor.ironsigns.main.Main;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.StandingSignRenderer;


public final class ModClientSetup {

	/**
	 * Entity renderers and textures are used only on the client side.
	 */
	@SuppressWarnings("unused")
	public static void init(FMLClientSetupEvent event) {
		// Entities.
		BlockEntityRenderers.register(Main.ironSignEntity.get(),
			StandingSignRenderer::new);
		BlockEntityRenderers.register(Main.hangingIronSignEntity.get(),
			HangingSignRenderer::new);
	}
}
