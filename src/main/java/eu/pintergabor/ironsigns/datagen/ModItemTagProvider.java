package eu.pintergabor.ironsigns.datagen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import eu.pintergabor.ironsigns.main.Main;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;


public final class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

	public ModItemTagProvider(
		FabricDataOutput output,
		CompletableFuture<HolderLookup.Provider> completableFuture
	) {
		super(output, completableFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		FabricTagBuilder tagBuilder =
			getOrCreateTagBuilder(Main.IRON_SIGN_ITEM_TAG);
		FabricTagBuilder hangingTagBuilder =
			getOrCreateTagBuilder(Main.IRON_SIGN_ITEM_TAG);
		// Iron sign.
		tagBuilder.add(Main.ironSign.item);
		hangingTagBuilder.add(Main.ironSign.hangingItem);
		// Color signs.
		Arrays.stream(Main.colorSigns)
			.forEach(sv -> {
				tagBuilder.add(sv.item);
				hangingTagBuilder.add(sv.hangingItem);
			});
	}
}
