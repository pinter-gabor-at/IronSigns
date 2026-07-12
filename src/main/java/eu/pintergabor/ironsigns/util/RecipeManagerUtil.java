package eu.pintergabor.ironsigns.util;

import java.util.Arrays;
import java.util.Map;

import eu.pintergabor.ironsigns.config.ModConfigData;
import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import org.jspecify.annotations.NonNull;

import net.minecraft.resources.Identifier;


public final class RecipeManagerUtil {

	private RecipeManagerUtil() {
		// Static class.
	}

	/**
	 * Remove Item recipe from map.
	 *
	 * @param map Map of all recipes.
	 * @param sv  IronSign variant.
	 */
	private static void removeItemRecipe(
		final @NonNull Map<Identifier, ?> map, final @NonNull SignVariant sv
	) {
		map.remove(Identifier.parse(sv.item.asItem().toString()));
	}

	/**
	 * Remove HangingItem recipe from map.
	 *
	 * @param map Map of all recipes.
	 * @param sv  IronSign variant.
	 */
	private static void removeHangingItemRecipe(
		final @NonNull Map<Identifier, ?> map, final @NonNull SignVariant sv
	) {
		map.remove(Identifier.parse(sv.hangingItem.asItem().toString()));
	}

	/**
	 * Remove paint Item recipe from map.
	 *
	 * @param map Map of all recipes.
	 * @param sv  IronSign variant.
	 */
	private static void removePaintItemRecipe(
		final @NonNull Map<Identifier, ?> map, final @NonNull SignVariant sv
	) {
		map.remove(Identifier.parse(sv.item.asItem() + "_dye"));
	}

	/**
	 * Remove paint HangingItem recipe from map
	 *
	 * @param map Map of all recipes
	 * @param sv  IronSign variant
	 */
	private static void removePaintHangingItemRecipe(
		final @NonNull Map<Identifier, ?> map, final @NonNull SignVariant sv
	) {
		map.remove(Identifier.parse(sv.hangingItem.asItem() + "_dye"));
	}

	/**
	 * Remove Color Sign Recipes, if they are disabled in config.
	 *
	 * @param map Map of all recipes.
	 */
	public static void configRecipes(final Map<Identifier, ?> map) {
		if (!ModConfigData.ENABLE_COLOR_SIGNS.get()) {
			Arrays.stream(Main.colorSigns)
				.forEach(sv -> {
					removeItemRecipe(map, sv);
					removeHangingItemRecipe(map, sv);
					removePaintItemRecipe(map, sv);
					removePaintHangingItemRecipe(map, sv);
				});
		}
	}
}
