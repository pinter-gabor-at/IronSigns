package eu.pintergabor.ironsigns.datagen;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.main.Main;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

public class ModRecipeGenerator extends RecipeGenerator {
    protected ModRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    @Override
    public void generate() {
        // Iron sign
        generateIronSignItemRecipe(exporter,
                Main.ironSign.item,
                ItemTags.PLANKS);
        generateHangingIronSignItemRecipe(exporter,
                Main.ironSign.hangingItem,
                ItemTags.PLANKS);
        // Color signs
        for (int i = 0; i < Main.colorSigns.length; i++) {
            generateIronSignItemRecipe(exporter,
                    Main.colorSigns[i].item,
                    Main.signColors[i].getDyeTagKey());
            generateHangingIronSignItemRecipe(exporter,
                    Main.colorSigns[i].hangingItem,
                    Main.signColors[i].getDyeTagKey());
            paintIronSignItemRecipe(exporter,
                    Main.colorSigns[i].item,
                    Main.signColors[i].getDyeTagKey());
            paintIronSignItemRecipe(exporter,
                    Main.colorSigns[i].hangingItem,
                    Main.signColors[i].getDyeTagKey());
        }
    }

    /**
     * Generate primary IronSign recipes
     *
     * @param signitem The recipe for this item
     * @param tag      ItemTag.PLANKS for IronSign, DyeTag for color signs
     */
    private void generateIronSignItemRecipe(
            RecipeExporter exporter, Item signitem, TagKey<Item> tag) {
        createShaped(RecipeCategory.MISC, signitem, 20)
                .pattern("SSS")
                .pattern("SPS")
                .pattern(" I ")
                .input('S', Items.IRON_INGOT)
                .input('P', tag)
                .input('I', Items.STICK)
                .criterion(hasItem(Items.IRON_INGOT),
                        conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);
    }

    /**
     * Generate primary HangingIronSign recipes
     *
     * @param signitem The recipe for this item
     * @param tag      ItemTag.PLANKS for HangingIronSign, DyeTag for hanging color
     *                 signs
     */
    private void generateHangingIronSignItemRecipe(
            RecipeExporter exporter, Item signitem, TagKey<Item> tag) {
        createShaped(RecipeCategory.MISC, signitem, 20)
                .pattern("C C")
                .pattern("SPS")
                .pattern("SSS")
                .input('C', Items.CHAIN)
                .input('P', tag)
                .input('S', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT),
                        conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);
    }

    /**
     * Generate IronSign and IronHangingSign repaint recipes
     *
     * @param signitem The recipe for this item
     * @param dye      DyeTag for color signs
     */
    private void paintIronSignItemRecipe(
            RecipeExporter exporter, Item signitem, TagKey<Item> dye) {
        createShapeless(RecipeCategory.MISC, signitem)
                .input(Main.IRON_SIGN_ITEM_TAG)
                .input(dye)
                .criterion(hasItem(Items.IRON_INGOT),
                        conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, Global.MODID + ":" + getRecipeName(signitem) + "_dye");
    }
}
