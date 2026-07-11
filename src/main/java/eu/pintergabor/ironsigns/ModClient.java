package eu.pintergabor.ironsigns;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.util.FormatButtonsHandler;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.StandingSignRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


@Environment(EnvType.CLIENT)
public final class ModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// Entity renderers.
		BlockEntityRenderers.register(Main.ironSignEntity,
			StandingSignRenderer::new);
		BlockEntityRenderers.register(Main.hangingIronSignEntity,
			HangingSignRenderer::new);
		// Screen handler.
		FormatButtonsHandler.init();
	}
}
