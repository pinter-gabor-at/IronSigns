package eu.pintergabor.ironsigns.datagen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Item;


public class ModItemTagProvider extends TagsProvider<Item> {

	public ModItemTagProvider(
		final PackOutput output,
		final CompletableFuture<HolderLookup.Provider> lookup
	) {
		super(output, Registries.ITEM, lookup, Global.MODID);
	}

	private static void addVariant(
		final @NonNull SignVariant ironSign,
		final @NonNull TagAppender<Item> modTag,
		final @NonNull TagAppender<Item> modHangingTag
	) {
		modTag.add(ironSign.item.key());
		modHangingTag.add(ironSign.hangingItem.key());
	}

	@Override
	protected void addTags(final HolderLookup.@NonNull Provider lookupProvider) {
		TagAppender<Item> modTag = tag(Main.IRON_SIGN_ITEM_TAG);
		TagAppender<Item> modHangingTag = tag(Main.IRON_SIGN_ITEM_TAG);
		// Iron sign.
		addVariant(Main.ironSign, modTag, modHangingTag);
		// Color signs.
		Arrays.stream(Main.colorSigns)
			.forEach(sv -> addVariant(sv, modTag, modHangingTag));
	}
}
