package eu.pintergabor.ironsigns.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.ironsigns.Global;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;


public final class ModRecipeRunner extends FabricRecipeProvider {

	public ModRecipeRunner(
		FabricDataOutput output,
		CompletableFuture<HolderLookup.Provider> completableFuture
	) {
		super(output, completableFuture);
	}

	@Override
	protected @NonNull RecipeProvider createRecipeProvider(
		HolderLookup.@NonNull Provider registryLookup,
		@NonNull RecipeOutput output
	) {
		return new ModRecipeGenerator(registryLookup, output);
	}

	@Override
	public @NonNull String getName() {
		return Global.MODID + " recipes";
	}
}
