package eu.pintergabor.ironsigns.main;

import net.neoforged.neoforge.common.Tags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;


/**
 * Color names, primary colors and dye item tags.
 */
public enum SignColor {
	WHITE("white", 0xF9FFFE, Tags.Items.DYES_WHITE),
	ORANGE("orange", 0xF9801D, Tags.Items.DYES_ORANGE),
	MAGENTA("magenta", 0xC74EBD, Tags.Items.DYES_MAGENTA),
	LIGHT_BLUE("light_blue", 0x3AB3DA, Tags.Items.DYES_LIGHT_BLUE),
	YELLOW("yellow", 0xFED83D, Tags.Items.DYES_YELLOW),
	LIME("lime", 0x80C71F, Tags.Items.DYES_LIME),
	PINK("pink", 0xF38BAA, Tags.Items.DYES_PINK),
	GRAY("gray", 0x474F52, Tags.Items.DYES_GRAY),
	LIGHT_GRAY("light_gray", 0x9D9D97, Tags.Items.DYES_LIGHT_GRAY),
	CYAN("cyan", 0x169C9C, Tags.Items.DYES_CYAN),
	PURPLE("purple", 0x8932B8, Tags.Items.DYES_PURPLE),
	BLUE("blue", 0x3C44AA, Tags.Items.DYES_BLUE),
	BROWN("brown", 0x835432, Tags.Items.DYES_BROWN),
	GREEN("green", 0x5E7C16, Tags.Items.DYES_GREEN),
	RED("red", 0xB02E26, Tags.Items.DYES_RED),
	BLACK("black", 0x1D1D21, Tags.Items.DYES_BLACK);

	private final String name;
	private final int color;
	private final TagKey<Item> dyeTagKey;

	SignColor(String name, int color, TagKey<Item> dyetagkey) {
		this.name = name;
		this.color = color;
		this.dyeTagKey = dyetagkey;
	}

	/**
	 * Name of the color
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Primary color of the corresponding items and entities.
	 */
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
