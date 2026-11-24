package eu.pintergabor.ironsigns.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.ironsigns.Global;
import org.jspecify.annotations.NonNull;

import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.crafting.Recipe;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;


public final class ModRecipeRunner extends FabricRecipeProvider {

	public ModRecipeRunner(
		final FabricPackOutput output,
		final CompletableFuture<HolderLookup.Provider> completableFuture
	) {
		super(output, completableFuture);
	}

	@Override
	protected @NonNull RecipeProvider createRecipeProvider(
		final HolderLookup.@NonNull Provider registries,
		final @NonNull BootstrapContext<Recipe<?>> recipes,
		final @NonNull BootstrapContext<Advancement> advancements
	) {
		return new ModRecipeGenerator(recipes, advancements);
	}

	@Override
	public @NonNull String getName() {
		return Global.MODID + " recipes";
	}
}
