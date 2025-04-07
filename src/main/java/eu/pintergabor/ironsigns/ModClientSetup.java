package eu.pintergabor.ironsigns;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;


public final class ModClientSetup {

	private static void texture(SignVariant sv) {
		final WoodType woodType = sv.woodType;
		Sheets.SIGN_MATERIALS.put(woodType,
			Sheets.getSignMaterial(woodType));
		Sheets.HANGING_SIGN_MATERIALS.put(woodType,
			Sheets.getHangingSignMaterial(woodType));
	}

	/**
	 * Entity renderers and textures are used only on the client side.
	 */
	@SuppressWarnings("unused")
	public static void init(FMLClientSetupEvent event) {
		// Entities.
		BlockEntityRenderers.register(Main.ironSignEntity.get(),
			SignRenderer::new);
		BlockEntityRenderers.register(Main.hangingIronSignEntity.get(),
			HangingSignRenderer::new);
		// Textures.
		texture(Main.ironSign);
		for (int i = 0; i < Main.colorSigns.length; i++) {
			texture(Main.colorSigns[i]);
		}
	}
}
