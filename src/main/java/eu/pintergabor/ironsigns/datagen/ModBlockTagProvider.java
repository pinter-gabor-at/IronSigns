package eu.pintergabor.ironsigns.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;


public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

	public ModBlockTagProvider(
		FabricDataOutput output,
		CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		FabricTagBuilder tagBuilder =
			getOrCreateTagBuilder(Main.IRON_SIGN_BLOCK_TAG);
		// Iron sign.
		builderAdd(tagBuilder, Main.ironSign);
		// Color signs.
		for (int i = 0; i < Main.colorSigns.length; i++) {
			builderAdd(tagBuilder, Main.colorSigns[i]);
		}
		// Make them mineable with axe and pickaxe.
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
			.forceAddTag(Main.IRON_SIGN_BLOCK_TAG);
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
			.forceAddTag(Main.IRON_SIGN_BLOCK_TAG);
	}

	@SuppressWarnings("UnusedReturnValue")
	private FabricTagBuilder builderAdd(
		FabricTagBuilder tagBuilder, SignVariant sv) {
		return tagBuilder
			.add(sv.standingSign)
			.add(sv.wallSign)
			.add(sv.ceilingHangingSign)
			.add(sv.wallHangingSign);
	}
}
