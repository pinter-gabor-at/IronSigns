package eu.pintergabor.ironsigns;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import eu.pintergabor.ironsigns.util.FormatButtonsHandler;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


@Environment(EnvType.CLIENT)
public class ModClient implements ClientModInitializer {

	private static void texture(SignVariant sv) {
		final WoodType woodType = sv.woodType;
		Sheets.SIGN_MATERIALS.put(woodType,
			Sheets.getSignMaterial(woodType));
		Sheets.HANGING_SIGN_MATERIALS.put(woodType,
			Sheets.getHangingSignMaterial(woodType));
	}

	@Override
	public void onInitializeClient() {
		// Entity renderers.
		BlockEntityRenderers.register(Main.ironSignEntity,
			SignRenderer::new);
		BlockEntityRenderers.register(Main.hangingIronSignEntity,
			HangingSignRenderer::new);
		// Textures.
		texture(Main.ironSign);
		for (int i = 0; i < Main.colorSigns.length; i++) {
			texture(Main.colorSigns[i]);
		}
		// Screen handler.
		FormatButtonsHandler.init();
	}
}
