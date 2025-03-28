package eu.pintergabor.ironsigns.util;

import eu.pintergabor.ironsigns.config.ModConfig;
import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import net.minecraft.util.Identifier;

import java.util.Map;

public class RecipeManagerUtil {
    private RecipeManagerUtil() {
    }

    /**
     * Remove Color Sign Recipes, if they are disabled in config
     *
     * @param map Map of all recipes
     */
    public static void configRecipes(Map<Identifier, ?> map) {
        if (!ModConfig.getInstance().enableColorSigns) {
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
     * Remove Item recipe from map
     *
     * @param map Map of all recipes
     * @param sv  IronSign variant
     */
    private static void removeItemRecipe(
            Map<Identifier, ?> map, SignVariant sv) {
        map.remove(Identifier.of(sv.item.toString()));
    }

    /**
     * Remove HangingItem recipe from map
     *
     * @param map Map of all recipes
     * @param sv  IronSign variant
     */
    private static void removeHangingItemRecipe(
            Map<Identifier, ?> map, SignVariant sv) {
        map.remove(Identifier.of(sv.hangingItem.toString()));
    }

    /**
     * Remove paint Item recipe from map
     *
     * @param map Map of all recipes
     * @param sv  IronSign variant
     */
    private static void removePaintItemRecipe(
            Map<Identifier, ?> map, SignVariant sv) {
        map.remove(Identifier.of(sv.item.toString() + "_dye"));
    }

    /**
     * Remove paint HangingItem recipe from map
     *
     * @param map Map of all recipes
     * @param sv  IronSign variant
     */
    private static void removePaintHangingItemRecipe(
            Map<Identifier, ?> map, SignVariant sv) {
        map.remove(Identifier.of(sv.hangingItem.toString() + "_dye"));
    }
}
