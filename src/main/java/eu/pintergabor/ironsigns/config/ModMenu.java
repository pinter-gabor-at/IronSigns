package eu.pintergabor.ironsigns.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfigClient;
import org.jetbrains.annotations.NotNull;

import net.minecraft.client.gui.screens.Screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


@Environment(EnvType.CLIENT)
public class ModMenu implements ModMenuApi {

	@Override
	public ConfigScreenFactory<@NotNull Screen> getModConfigScreenFactory() {
		return parent -> AutoConfigClient.getConfigScreen(ModConfigData.class, parent).get();
	}
}
