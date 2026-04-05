package eu.pintergabor.ironsigns.util;

import java.util.Arrays;
import java.util.Map;

import eu.pintergabor.ironsigns.config.ModConfigData;
import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import org.jspecify.annotations.NonNull;

import net.minecraft.resources.Identifier;


public class RecipeManagerUtil {

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
		@NonNull Map<Identifier, ?> map, @NonNull SignVariant sv
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
		@NonNull Map<Identifier, ?> map, @NonNull SignVariant sv
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
		@NonNull Map<Identifier, ?> map, @NonNull SignVariant sv
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
		@NonNull Map<Identifier, ?> map, @NonNull SignVariant sv
	) {
		map.remove(Identifier.parse(sv.hangingItem.asItem() + "_dye"));
	}

	/**
	 * Remove Color Sign Recipes, if they are disabled in config.
	 *
	 * @param map Map of all recipes.
	 */
	public static void configRecipes(Map<Identifier, ?> map) {
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
