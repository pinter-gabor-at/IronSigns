package eu.pintergabor.ironsigns.datagen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import eu.pintergabor.ironsigns.main.Main;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.world.item.Item;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;


public final class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

	public ModItemTagProvider(
		FabricPackOutput output,
		CompletableFuture<HolderLookup.Provider> completableFuture
	) {
		super(output, completableFuture);
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
		TagAppender<Item, Item> tagBuilder =
			valueLookupBuilder(Main.IRON_SIGN_ITEM_TAG);
		TagAppender<Item, Item> hangingTagBuilder =
			valueLookupBuilder(Main.IRON_SIGN_ITEM_TAG);
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
