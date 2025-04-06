package eu.pintergabor.ironsigns;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import eu.pintergabor.ironsigns.renderer.IronSignRenderer;
import eu.pintergabor.ironsigns.util.FormatButtonsHandler;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Global.MODID, value = Dist.CLIENT)
public final class ModClient {

	private static void texture(SignVariant sv) {
		WoodType wt = sv.woodType;
		Sheets.SIGN_MATERIALS.put(wt,
			Sheets.getSignMaterial(wt));
	}

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		// Entities.
		BlockEntityRenderers.register(Main.ironSignEntity.get(),
			context -> {
				Global.LOGGER.info(context.toString());
				return new IronSignRenderer(context);
			});
		BlockEntityRenderers.register(Main.hangingIronSignEntity.get(),
			HangingSignRenderer::new);
		// Textures.
		texture(Main.ironSign);
		for (int i = 0; i < Main.colorSigns.length; i++) {
			texture(Main.colorSigns[i]);
		}
		// Screen handler.
		FormatButtonsHandler.init();
	}

	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(
			Main.ironSignEntity.get(),
			IronSignRenderer::new);
	}
}
