package eu.pintergabor.ironsigns.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.screens.Screen;


@Environment(EnvType.CLIENT)
public class ModMenu implements ModMenuApi {

	@Override
	public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
		return parent -> AutoConfig.getConfigScreen(ModConfigData.class, parent).get();
	}
}
