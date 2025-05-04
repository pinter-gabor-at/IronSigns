package eu.pintergabor.ironsigns.main;

import static net.minecraft.world.item.CreativeModeTab.TabVisibility;

import java.util.Arrays;

import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;


public final class CreativeTabs {

	/**
	 * Add one item to creative tabs.
	 */
	private static void add(
		BuildCreativeModeTabContentsEvent event, ItemLike item
	) {
		event.insertBefore(
			new ItemStack(Items.CHEST), new ItemStack(item),
			TabVisibility.PARENT_AND_SEARCH_TABS);
	}

	/**
	 * Add one sign variant to creative tabs.
	 */
	private static void add(
		BuildCreativeModeTabContentsEvent event, SignVariant ironSign
	) {
		add(event, ironSign.item);
		add(event, ironSign.hangingItem);
	}

	/**
	 * Add items to creative tabs.
	 */
	public static void init(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			// Iron sign.
			add(event, Main.ironSign);
			// Color signs.
			Arrays.stream(Main.colorSigns)
				.forEach(sv -> add(event, sv));
		}
	}
}
