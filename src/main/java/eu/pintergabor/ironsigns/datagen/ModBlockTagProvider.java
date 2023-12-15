package eu.pintergabor.ironsigns.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;

import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	public ModBlockTagProvider(
		FabricDataOutput output,
		CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup arg) {
		FabricTagBuilder tagBuilder =
			getOrCreateTagBuilder(Main.IRON_SIGN_BLOCK_TAG);
		// Iron sign
		builderAdd(tagBuilder, Main.ironSign);
		// Color signs
		for (int i = 0; i < Main.colorSigns.length; i++) {
			builderAdd(tagBuilder, Main.colorSigns[i]);
		}
		// Make them mineable with axe and pickaxe
		getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
			.forceAddTag(Main.IRON_SIGN_BLOCK_TAG);
		getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
			.forceAddTag(Main.IRON_SIGN_BLOCK_TAG);
	}

	@SuppressWarnings("UnusedReturnValue")
	private FabricTagBuilder builderAdd(
		FabricTagBuilder tagBuilder, SignVariant sv) {
		return tagBuilder
			.add(sv.block)
			.add(sv.wallBlock)
			.add(sv.hangingBlock)
			.add(sv.hangingWallBlock);
	}
}
