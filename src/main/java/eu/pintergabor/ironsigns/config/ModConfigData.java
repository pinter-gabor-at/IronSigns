package eu.pintergabor.ironsigns.config;

import eu.pintergabor.ironsigns.Global;
import net.neoforged.neoforge.common.ModConfigSpec;


public class ModConfigData {
	// Builder.
	private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	// Enable Color Sign Recipes.
	public static final ModConfigSpec.BooleanValue ENABLE_COLOR_SIGNS = BUILDER
		.translation(Global.modName("config.enableColorSigns"))
		.define("enableColorSigns", true);
	// Enable Iron Sign Text Formatting.
	public static final ModConfigSpec.BooleanValue ENABLE_IRON_SIGN_TEXT_FORMATTING = BUILDER
		.translation(Global.modName("config.enableIronSignTextFormatting"))
		.define("enableIronSignTextFormatting", true);
	// Enable Wooden Sign Text Formatting.
	public static final ModConfigSpec.BooleanValue ENABLE_WOODEN_SIGN_TEXT_FORMATTING = BUILDER
		.translation(Global.modName("config.enableWoodenSignTextFormatting"))
		.define("enableWoodenSignTextFormatting", true);
	// Build and prepare for registration.
	public static final ModConfigSpec SPEC = BUILDER.build();

	/**
	 * @return true if any Text Formatting is enabled.
	 */
	public static boolean enableSignTextFormatting() {
		return ENABLE_IRON_SIGN_TEXT_FORMATTING.get() || ENABLE_WOODEN_SIGN_TEXT_FORMATTING.get();
	}
}
