package eu.pintergabor.ironsigns.datagen;

import eu.pintergabor.ironsigns.main.Main;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        // Iron sign
        generateIronSignItemRecipe(exporter,
                Main.getIronSign().getItem(),
                ItemTags.PLANKS);
        generateHangingIronSignItemRecipe(exporter,
                Main.getIronSign().getHangingItem(),
                ItemTags.PLANKS);
        // Color signs
        for (int i = 0; i < Main.getColorSignLength(); i++) {
            generateIronSignItemRecipe(exporter,
                    Main.getColorSign(i).getItem(),
                    Main.getSignColors(i).getDyeTagKey());
            generateHangingIronSignItemRecipe(exporter,
                    Main.getColorSign(i).getHangingItem(),
                    Main.getSignColors(i).getDyeTagKey());
            paintIronSignItemRecipe(exporter,
                    Main.getColorSign(i).getItem(),
                    Main.getSignColors(i).getDyeTagKey());
            paintHangingIronSignItemRecipe(exporter,
                    Main.getColorSign(i).getHangingItem(),
                    Main.getSignColors(i).getDyeTagKey());
        }

    }

    /**
     * Generate primary IronSign recipes
     * @param signitem The recipe for this item
     * @param tag ItemTag.PLANKS for IronSign, DyeTag for color signs
     */
    private void generateIronSignItemRecipe(
            RecipeExporter exporter, Item signitem, TagKey<Item> tag) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, signitem, 20)
                .pattern("SSS")
                .pattern("SPS")
                .pattern(" I ")
                .input('S', Items.IRON_INGOT)
                .input('P', tag)
                .input('I', Items.STICK)
                .criterion(hasItem(Items.IRON_INGOT),
                        conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(signitem)));
    }

    /**
     * Generate primary HangingIronSign recipes
     * @param signitem The recipe for this item
     * @param tag ItemTag.PLANKS for HangingIronSign, DyeTag for hanging color signs
     */
    private void generateHangingIronSignItemRecipe(
            RecipeExporter exporter, Item signitem, TagKey<Item> tag) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, signitem, 20)
                .pattern("C C")
                .pattern("SPS")
                .pattern("SSS")
                .input('C', Items.CHAIN)
                .input('P', tag)
                .input('S', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT),
                        conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(signitem)));
    }

    /**
     * Generate IronSign repaint recipes
     * @param signitem The recipe for this item
     * @param dye DyeTag for color signs
     */
    private void paintIronSignItemRecipe(
            RecipeExporter exporter, Item signitem, TagKey<Item> dye) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, signitem)
                .input(Main.getIronSignItemTag())
                .input(dye)
                .criterion(hasItem(Items.IRON_INGOT),
                        conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(signitem) + "_dye"));
    }

    /**
     * Generate HangingIronSign repaint recipes
     * @param signitem The recipe for this item
     * @param dye DyeTag for color signs
     */
    private void paintHangingIronSignItemRecipe(
            RecipeExporter exporter, Item signitem, TagKey<Item> dye) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, signitem)
                .input(Main.getHangingIronSignItemTag())
                .input(dye)
                .criterion(hasItem(Items.IRON_INGOT),
                        conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(signitem) + "_dye"));
    }
}
