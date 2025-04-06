package eu.pintergabor.ironsigns.renderer;

import java.util.HashMap;
import java.util.Map;

import eu.pintergabor.ironsigns.main.Main;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

// TODO: https://docs.neoforged.net/docs/blockentities/ber/
// IronSignRenderer implements BlockEntityRenderer<SignBlockEntity>
// therefore it must be registered.


public class IronSignRenderer extends SignRenderer {
	private final Map<WoodType, Models> signModels = new HashMap<>();

	public IronSignRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
		signModels.put(WoodType.ACACIA, new Models(
			createSignModel(context.getModelSet(), WoodType.ACACIA, true),
			createSignModel(context.getModelSet(), WoodType.ACACIA, false)));
		signModels.put(Main.ironSign.woodType, new Models(
			createSignModel(context.getModelSet(), Main.ironSign.woodType, true),
			createSignModel(context.getModelSet(), Main.ironSign.woodType, false)));
	}

	@Override
	@NotNull
	protected Model getSignModel(@NotNull BlockState state, @NotNull WoodType woodType) {
		if (woodType == Main.ironSign.woodType) {
			var models = signModels.get(Main.ironSign.woodType);
			return state.getBlock() instanceof StandingSignBlock ? models.standing() : models.wall();
		}
		return super.getSignModel(state, woodType);
	}

	@OnlyIn(Dist.CLIENT)
	record Models(Model standing, Model wall) {
	}
}
