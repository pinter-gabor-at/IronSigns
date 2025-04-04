package eu.pintergabor.ironsigns.main;

import static eu.pintergabor.ironsigns.main.Main.signColors;

import eu.pintergabor.ironsigns.Global;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.CreativeModeTabs;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Global.MODID, value = Dist.CLIENT)
public final class CreativeTabs {

	/**
	 * Add items to creative tabs.
	 */
	@SubscribeEvent
	public static void creativeTabs(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			// Iron sign.
			event.accept(Main.ironSign.item);
			event.accept(Main.ironSign.hangingItem);
			// Color signs.
			for (int i = 0; i < signColors.length; i++) {
				event.accept(Main.colorSigns[i].item);
				event.accept(Main.colorSigns[i].hangingItem);
			}
		}
	}
}
