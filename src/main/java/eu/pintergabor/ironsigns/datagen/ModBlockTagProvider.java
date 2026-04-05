package eu.pintergabor.ironsigns.datagen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;


public final class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {

	public ModBlockTagProvider(
		FabricPackOutput output,
		CompletableFuture<HolderLookup.Provider> registriesFuture
	) {
		super(output, registriesFuture);
	}


	@SuppressWarnings("UnusedReturnValue")
	private @NonNull TagAppender<Block, Block> builderAdd(
		@NonNull TagAppender<Block, Block> tagBuilder,
		@NonNull SignVariant sv
	) {
		return tagBuilder
			.add(sv.standingSign)
			.add(sv.wallSign)
			.add(sv.ceilingHangingSign)
			.add(sv.wallHangingSign);
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
		TagAppender<Block, Block> tagBuilder =
			valueLookupBuilder(Main.IRON_SIGN_BLOCK_TAG);
		// Iron sign.
		builderAdd(tagBuilder, Main.ironSign);
		// Color signs.
		Arrays.stream(Main.colorSigns)
			.forEach(sv -> builderAdd(tagBuilder, sv));
		// Make them mineable with axe and pickaxe.
		valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
			.addTag(Main.IRON_SIGN_BLOCK_TAG);
		valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
			.addTag(Main.IRON_SIGN_BLOCK_TAG);
	}
}
