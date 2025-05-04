package eu.pintergabor.ironsigns.main;


import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;


/**
 * Color names, primary colors and dye item tags.
 */
public enum SignColor {
	WHITE("white", 0xF9FFFE, ConventionalItemTags.WHITE_DYES),
	ORANGE("orange", 0xF9801D, ConventionalItemTags.ORANGE_DYES),
	MAGENTA("magenta", 0xC74EBD, ConventionalItemTags.MAGENTA_DYES),
	LIGHT_BLUE("light_blue", 0x3AB3DA, ConventionalItemTags.LIGHT_BLUE_DYES),
	YELLOW("yellow", 0xFED83D, ConventionalItemTags.YELLOW_DYES),
	LIME("lime", 0x80C71F, ConventionalItemTags.LIME_DYES),
	PINK("pink", 0xF38BAA, ConventionalItemTags.PINK_DYES),
	GRAY("gray", 0x474F52, ConventionalItemTags.GRAY_DYES),
	LIGHT_GRAY("light_gray", 0x9D9D97, ConventionalItemTags.LIGHT_GRAY_DYES),
	CYAN("cyan", 0x169C9C, ConventionalItemTags.CYAN_DYES),
	PURPLE("purple", 0x8932B8, ConventionalItemTags.PURPLE_DYES),
	BLUE("blue", 0x3C44AA, ConventionalItemTags.BLUE_DYES),
	BROWN("brown", 0x835432, ConventionalItemTags.BROWN_DYES),
	GREEN("green", 0x5E7C16, ConventionalItemTags.GREEN_DYES),
	RED("red", 0xB02E26, ConventionalItemTags.RED_DYES),
	BLACK("black", 0x1D1D21, ConventionalItemTags.BLACK_DYES);

	private final String name;
	private final int color;
	private final TagKey<Item> dyeTagKey;

	SignColor(String name, int color, TagKey<Item> dyetagkey) {
		this.name = name;
		this.color = color;
		this.dyeTagKey = dyetagkey;
	}

	/**
	 * Name of the color.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Primary color of the corresponding items and entities.
	 */
	@SuppressWarnings("unused")
	public int getColor() {
		return this.color;
	}

	/**
	 * Dye used in the crafting recipes of the corresponding items.
	 */
	public TagKey<Item> getDyeTagKey() {
		return dyeTagKey;
	}

	@SuppressWarnings("unused")
	public String asString() {
		return this.name;
	}
}
