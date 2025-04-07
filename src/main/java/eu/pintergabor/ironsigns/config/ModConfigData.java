package eu.pintergabor.ironsigns.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import org.jetbrains.annotations.ApiStatus;


@ApiStatus.Internal
@Config(name = "ironsigns")
public class ModConfigData implements ConfigData {
	@ConfigEntry.Gui.Tooltip
	public boolean enableColorSigns = true;

	@ConfigEntry.Gui.PrefixText
	@ConfigEntry.Gui.Tooltip
	public boolean enableIronSignTextFormatting = true;

	@ConfigEntry.Gui.Tooltip
	public boolean enableWoodenSignTextFormatting = false;

	public static void init() {
		AutoConfig.register(ModConfigData.class, Toml4jConfigSerializer::new);
	}

	public static ModConfigData getInstance() {
		return AutoConfig.getConfigHolder(ModConfigData.class).getConfig();
	}

	/**
	 * @return true if any Text Formatting is enabled.
	 */
	public static boolean enableSignTextFormatting() {
		final ModConfigData m = getInstance();
		return m.enableIronSignTextFormatting || m.enableWoodenSignTextFormatting;
	}
}
