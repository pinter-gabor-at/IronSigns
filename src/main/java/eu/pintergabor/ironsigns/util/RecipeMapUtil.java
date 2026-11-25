package eu.pintergabor.ironsigns.util;

import java.util.Arrays;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.config.ModConfigData;
import eu.pintergabor.ironsigns.main.Main;
import org.jspecify.annotations.NonNull;

import net.minecraft.resources.Identifier;


public final class RecipeMapUtil {

	private RecipeMapUtil() {
		// Static class.
	}

	/**
	 * Remove Color Sign Recipes, if they are disabled in config.
	 *
	 * @param recipeId recipe id to check.
	 * @return true if this recipe shall be removed.
	 */
	public static boolean isDisabledRecipe(@NonNull Identifier recipeId) {
		final String recipeName = recipeId.getPath();
		return !ModConfigData.getInstance().enableColorSigns &&
			recipeId.getNamespace().equals(Global.MODID) &&
			Arrays.stream(Main.colorSigns)
				.anyMatch(sv ->
					recipeName.startsWith(sv.itemId.identifier().getPath()) ||
						recipeName.startsWith(sv.hangingItemId.identifier().getPath()));
	}
}
