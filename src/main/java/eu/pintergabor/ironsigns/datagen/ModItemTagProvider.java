package eu.pintergabor.ironsigns.datagen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public class ModItemTagProvider extends KeyTagProvider<Item> {

	public ModItemTagProvider(
		PackOutput output,
		CompletableFuture<HolderLookup.Provider> lookupProvider,
		CompletableFuture<TagLookup<Block>> blockTagProvider
	) {
		super(output, Registries.ITEM, lookupProvider, Global.MODID);
	}

	private static void addVariant(
		SignVariant ironSign,
		TagAppender<ResourceKey<Item>, Item> modTag,
		TagAppender<ResourceKey<Item>, Item> modHangingTag
	) {
		modTag.add(ironSign.item.getKey());
		modHangingTag.add(ironSign.hangingItem.getKey());
	}

	@Override
	protected void addTags(@NotNull HolderLookup.Provider lookupProvider) {
		TagAppender<ResourceKey<Item>, Item> modTag = tag(Main.IRON_SIGN_ITEM_TAG);
		TagAppender<ResourceKey<Item>, Item> modHangingTag = tag(Main.IRON_SIGN_ITEM_TAG);
		// Iron sign.
		addVariant(Main.ironSign, modTag, modHangingTag);
		// Color signs.
		Arrays.stream(Main.colorSigns)
			.forEach(sv -> addVariant(sv, modTag, modHangingTag));
	}
}
