package eu.pintergabor.ironsigns.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import eu.pintergabor.ironsigns.util.RecipeManagerUtil;
import net.minecraft.recipe.PreparedRecipes;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.SortedMap;

@Mixin(ServerRecipeManager.class)
public abstract class RecipeManagerMixin {
    /**
     * Remove Color Sign Recipes, if they are disabled in config
     */
    @Inject(method = "prepare(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)Lnet/minecraft/recipe/PreparedRecipes;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/resource/JsonDataLoader;load(Lnet/minecraft/resource/ResourceManager;Ljava/lang/String;Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/Codec;Ljava/util/Map;)V",
                    shift = At.Shift.AFTER))
    private void afterLoad(ResourceManager resourceManager, Profiler profiler, CallbackInfoReturnable<PreparedRecipes> cir, @Local SortedMap<Identifier, Recipe<?>> sortedMap) {
        RecipeManagerUtil.configRecipes(sortedMap);
    }
}
