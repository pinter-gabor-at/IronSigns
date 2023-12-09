package eu.pintergabor.ironsigns;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.block.WoodType;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;

public class ModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// Entities
		BlockEntityRendererFactories.register(Main.getIronSignEntity(),
				SignBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(Main.getHangingIronSignEntity(),
				HangingSignBlockEntityRenderer::new);
		// Textures
		texture(Main.getIronSign());
		for (int i = 0; i < Main.getColorSignLength(); i++) {
			texture(Main.getColorSign(i));
		}
	}

	private void texture(SignVariant sv) {
		WoodType wt = sv.getWoodType();
		TexturedRenderLayers.SIGN_TYPE_TEXTURES.put(wt,
				TexturedRenderLayers.getSignTextureId(wt));
	}
}
