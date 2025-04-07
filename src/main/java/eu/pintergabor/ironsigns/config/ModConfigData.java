package eu.pintergabor.ironsigns.config;

import eu.pintergabor.ironsigns.Global;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;


public class ModConfigData {
	// Builder.
	private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	// Enable Color Sign Recipes.
	private static final ModConfigSpec.BooleanValue ENABLE_COLOR_SIGNS = BUILDER
		.translation(Global.modName("config.enableColorSigns"))
		.define("enableColorSigns", true);
	public static boolean enableColorSigns;
	// Enable Iron Sign Text Formatting.
	private static final ModConfigSpec.BooleanValue ENABLE_IRON_SIGN_TEXT_FORMATTING = BUILDER
		.translation(Global.modName("config.enableIronSignTextFormatting"))
		.define("enableIronSignTextFormatting", true);
	public static boolean enableIronSignTextFormatting;
	// Enable Wooden Sign Text Formatting.
	private static final ModConfigSpec.BooleanValue ENABLE_WOODEN_SIGN_TEXT_FORMATTING = BUILDER
		.translation(Global.modName("config.enableWoodenSignTextFormatting"))
		.define("enableWoodenSignTextFormatting", true);
	public static boolean enableWoodenSignTextFormatting;
	// Build and prepare for registration.
	public static final ModConfigSpec SPEC = BUILDER.build();

	/**
	 * @return true if any Text Formatting is enabled.
	 */
	public static boolean enableSignTextFormatting() {
		return enableIronSignTextFormatting || enableWoodenSignTextFormatting;
	}

	/**
	 * Load parameters from TOML.
	 */
	@SuppressWarnings("unused")
	public static void onLoad(final ModConfigEvent event) {
		enableColorSigns = ENABLE_COLOR_SIGNS.get();
		enableIronSignTextFormatting = ENABLE_IRON_SIGN_TEXT_FORMATTING.get();
		enableWoodenSignTextFormatting = ENABLE_WOODEN_SIGN_TEXT_FORMATTING.get();
	}
}
