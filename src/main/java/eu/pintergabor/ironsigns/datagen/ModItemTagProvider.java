package eu.pintergabor.ironsigns.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import org.jetbrains.annotations.NotNull;


public class ModItemTagProvider extends ItemTagsProvider {

	public ModItemTagProvider(
		PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTagProvider) {
		super(output, lookupProvider, blockTagProvider, Global.MODID);
	}

	private static void addVariant(
		SignVariant ironSign,
		IntrinsicTagAppender<Item> modTag, IntrinsicTagAppender<Item> modHangingTag) {
		modTag.add(ironSign.item.get());
		modHangingTag.add(ironSign.hangingItem.get());
	}

	@Override
	protected void addTags(@NotNull HolderLookup.Provider lookupProvider) {
		IntrinsicTagAppender<Item> modTag = tag(Main.IRON_SIGN_ITEM_TAG);
		IntrinsicTagAppender<Item> modHangingTag = tag(Main.IRON_SIGN_ITEM_TAG);
		// Iron sign.
		addVariant(Main.ironSign, modTag, modHangingTag);
		// Color signs
		for (int i = 0; i < Main.colorSigns.length; i++) {
			addVariant(Main.colorSigns[i], modTag, modHangingTag);
		}
	}
}
