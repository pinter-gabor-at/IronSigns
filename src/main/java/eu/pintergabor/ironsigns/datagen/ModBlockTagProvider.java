package eu.pintergabor.ironsigns.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;


public class ModBlockTagProvider extends BlockTagsProvider {

	public ModBlockTagProvider(
		PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider, Global.MODID);
	}

	private static void addVariant(
		SignVariant sv, IntrinsicTagAppender<Block> modTag) {
		modTag.add(
			sv.block.get(), sv.wallBlock.get(),
			sv.hangingBlock.get(), sv.hangingWallBlock.get());
	}

	@Override
	protected void addTags(@NotNull HolderLookup.Provider lookupProvider) {
		IntrinsicTagAppender<Block> modBlockTag = tag(Main.IRON_SIGN_BLOCK_TAG);
		// Iron sign.
		addVariant(Main.ironSign, modBlockTag);
		// Color signs.
		for (int i = 0; i < Main.colorSigns.length; i++) {
			addVariant(Main.colorSigns[i], modBlockTag);
		}
		// Make them mineable with axe and pickaxe.
		tag(BlockTags.MINEABLE_WITH_AXE)
			.addTag(Main.IRON_SIGN_BLOCK_TAG);
		tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.addTag(Main.IRON_SIGN_BLOCK_TAG);
	}
}
