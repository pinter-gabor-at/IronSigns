package eu.pintergabor.ironsigns.mixin;

import java.util.Map;

import com.google.gson.JsonElement;
import eu.pintergabor.ironsigns.util.RecipeManagerUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {

	@Inject(method = "apply*", at = @At("HEAD"))
	private void apply(
		Map<Identifier, JsonElement> map,
		ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
		// Remove Color Sign Recipes, if they are disabled in config
		RecipeManagerUtil.configRecipes(map);
	}
}
