package eu.pintergabor.ironsigns.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@Config(name = "ironsigns")
public class ModConfig implements ConfigData {
	@ConfigEntry.Gui.Tooltip
	public boolean enableColorSigns = true;

	@ConfigEntry.Gui.PrefixText
	@ConfigEntry.Gui.Tooltip
	public boolean enableIronSignTextFormatting = true;

	@ConfigEntry.Gui.Tooltip
	public boolean enableWoodenSignTextFormatting = false;

	public static void init() {
		AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
	}

	public static ModConfig getInstance() {
		return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
	}

	/**
	 * @return true if any Text Formatting is enabled
	 */
	public static boolean enableSignTextFormatting() {
		final ModConfig m = getInstance();
		return m.enableIronSignTextFormatting || m.enableWoodenSignTextFormatting;
	}
}
