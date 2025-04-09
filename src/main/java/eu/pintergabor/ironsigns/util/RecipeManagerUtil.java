package eu.pintergabor.ironsigns.util;

import eu.pintergabor.ironsigns.config.ModConfigData;
import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;

import net.minecraft.resources.ResourceLocation;

import java.util.Map;


public class RecipeManagerUtil {

    private RecipeManagerUtil() {
		// Static class.
    }

    /**
     * Remove Color Sign Recipes, if they are disabled in config.
     *
     * @param map Map of all recipes.
     */
    public static void configRecipes(Map<ResourceLocation, ?> map) {
        if (!ModConfigData.ENABLE_COLOR_SIGNS.get()) {
            for (int i = 0; i < Main.colorSigns.length; i++) {
                SignVariant sv = Main.colorSigns[i];
                removeItemRecipe(map, sv);
                removeHangingItemRecipe(map, sv);
                removePaintItemRecipe(map, sv);
                removePaintHangingItemRecipe(map, sv);
            }
        }
    }

    /**
     * Remove Item recipe from map.
     *
     * @param map Map of all recipes.
     * @param sv  IronSign variant.
     */
    private static void removeItemRecipe(
            Map<ResourceLocation, ?> map, SignVariant sv) {
        map.remove(ResourceLocation.parse(sv.item.toString()));
    }

    /**
     * Remove HangingItem recipe from map.
     *
     * @param map Map of all recipes.
     * @param sv  IronSign variant.
     */
    private static void removeHangingItemRecipe(
            Map<ResourceLocation, ?> map, SignVariant sv) {
        map.remove(ResourceLocation.parse(sv.hangingItem.toString()));
    }

    /**
     * Remove paint Item recipe from map.
     *
     * @param map Map of all recipes.
     * @param sv  IronSign variant.
     */
    private static void removePaintItemRecipe(
            Map<ResourceLocation, ?> map, SignVariant sv) {
        map.remove(ResourceLocation.parse(sv.item.toString() + "_dye"));
    }

    /**
     * Remove paint HangingItem recipe from map
     *
     * @param map Map of all recipes
     * @param sv  IronSign variant
     */
    private static void removePaintHangingItemRecipe(
            Map<ResourceLocation, ?> map, SignVariant sv) {
        map.remove(ResourceLocation.parse(sv.hangingItem.toString() + "_dye"));
    }
}
