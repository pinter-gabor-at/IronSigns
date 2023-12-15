package eu.pintergabor.ironsigns;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import eu.pintergabor.ironsigns.util.FormatButtonsHandler;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.WoodType;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;

@Environment(EnvType.CLIENT)
public class ModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// Entities
		BlockEntityRendererFactories.register(Main.ironSignEntity,
				SignBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(Main.hangingIronSignEntity,
				HangingSignBlockEntityRenderer::new);
		// Textures
		texture(Main.ironSign);
		for (int i = 0; i < Main.colorSigns.length; i++) {
			texture(Main.colorSigns[i]);
		}
		// Screen handler
		FormatButtonsHandler.init();
	}

	private void texture(SignVariant sv) {
		WoodType wt = sv.woodType;
		TexturedRenderLayers.SIGN_TYPE_TEXTURES.put(wt,
				TexturedRenderLayers.getSignTextureId(wt));
	}
}
