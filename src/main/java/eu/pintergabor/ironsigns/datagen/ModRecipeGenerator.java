package eu.pintergabor.ironsigns.datagen;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.main.Main;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;


public class ModRecipeGenerator extends RecipeProvider {

	public ModRecipeGenerator(HolderLookup.Provider registryLookup, RecipeOutput output) {
		super(registryLookup, output);
	}

	/**
	 * Generate primary IronSign recipes.
	 *
	 * @param signitem The recipe for this item.
	 * @param tag      ItemTag.PLANKS for IronSign, DyeTag for color signs.
	 */
	private void generateIronSignItemRecipe(
		Item signitem, TagKey<Item> tag) {
		shaped(RecipeCategory.MISC, signitem, 20)
			.pattern("SSS")
			.pattern("SPS")
			.pattern(" I ")
			.define('S', Items.IRON_INGOT)
			.define('P', tag)
			.define('I', Items.STICK)
			.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
			.save(output);
	}

	/**
	 * Generate primary HangingIronSign recipes.
	 *
	 * @param signitem The recipe for this item.
	 * @param tag      ItemTag.PLANKS for HangingIronSign, DyeTag for hanging color
	 *                 signs.
	 */
	private void generateHangingIronSignItemRecipe(
		Item signitem, TagKey<Item> tag) {
		shaped(RecipeCategory.MISC, signitem, 20)
			.pattern("C C")
			.pattern("SPS")
			.pattern("SSS")
			.define('C', Items.CHAIN)
			.define('P', tag)
			.define('S', Items.IRON_INGOT)
			.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
			.save(output);
	}

	/**
	 * Generate IronSign and IronHangingSign repaint recipes.
	 *
	 * @param signitem The recipe for this item.
	 * @param dye      DyeTag for color signs.
	 */
	private void paintIronSignItemRecipe(
		Item signitem, TagKey<Item> dye) {
		shapeless(RecipeCategory.MISC, signitem)
			.requires(Main.IRON_SIGN_ITEM_TAG)
			.requires(dye)
			.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
			.save(output, Global.MODID + ":" + getSimpleRecipeName(signitem) + "_dye");
	}

	@Override
	public void buildRecipes() {
		// Iron sign.
		generateIronSignItemRecipe(
			Main.ironSign.item,
			ItemTags.PLANKS);
		generateHangingIronSignItemRecipe(
			Main.ironSign.hangingItem,
			ItemTags.PLANKS);
		// Color signs.
		for (int i = 0; i < Main.colorSigns.length; i++) {
			generateIronSignItemRecipe(
				Main.colorSigns[i].item,
				Main.signColors[i].getDyeTagKey());
			generateHangingIronSignItemRecipe(
				Main.colorSigns[i].hangingItem,
				Main.signColors[i].getDyeTagKey());
			paintIronSignItemRecipe(
				Main.colorSigns[i].item,
				Main.signColors[i].getDyeTagKey());
			paintIronSignItemRecipe(
				Main.colorSigns[i].hangingItem,
				Main.signColors[i].getDyeTagKey());
		}
	}
}
