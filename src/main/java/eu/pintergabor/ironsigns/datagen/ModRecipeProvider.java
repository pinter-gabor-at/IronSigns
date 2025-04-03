package eu.pintergabor.ironsigns.datagen;

import eu.pintergabor.ironsigns.Global;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.minecraft.core.HolderLookup;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;


public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(
            FabricDataOutput output,
			CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

	@Override
	protected RecipeProvider createRecipeProvider(
		HolderLookup.Provider registryLookup, RecipeOutput output) {
        return new ModRecipeGenerator(registryLookup, output);
    }

    @Override
	@NotNull
    public String getName() {
        return Global.MODID + " recipes";
    }
}
