package eu.pintergabor.ironsigns.util;

import eu.pintergabor.ironsigns.Global;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public final class Register {

	private Register() {
		// Static class.
	}

	/**
	 * Register mod item.
	 *
	 * @param name Name, as in lang/*.json files.
	 * @param item Item.
	 * @return The same item.
	 */
	@SuppressWarnings({"unused", "UnusedReturnValue"})
	public static Item registerItem(String name, Item item) {
		return Registry.register(
			BuiltInRegistries.ITEM,
			Global.ModIdentifier(name),
			item);
	}

	/**
	 * Register mod block without blockitem.
	 *
	 * @param name  Name, as in lang/*.json files.
	 * @param block Block.
	 * @return Block for chaining.
	 */
	@SuppressWarnings({"unused", "UnusedReturnValue"})
	public static Block registerBlock(String name, Block block) {
		return Registry.register(
			BuiltInRegistries.BLOCK,
			Global.ModIdentifier(name),
			block);
	}

	/**
	 * Register blockitem.
	 *
	 * @param name  Name, as in lang/*.json files.
	 * @param block Block.
	 */
	@SuppressWarnings({"unused", "UnusedReturnValue"})
	public static void registerBlockItem(String name, Block block) {
		Registry.register(
			BuiltInRegistries.ITEM,
			Global.ModIdentifier(name),
			new BlockItem(block, new Item.Properties()));
	}

	/**
	 * Register mod block with blockitem.
	 *
	 * @param name  Name, as in lang/*.json files.
	 * @param block Block.
	 * @return Block for chaining.
	 */
	@SuppressWarnings({"unused", "UnusedReturnValue"})
	public static Block registerBlockAndItem(String name, Block block) {
		registerBlockItem(name, block);
		return Registry.register(
			BuiltInRegistries.BLOCK,
			Global.ModIdentifier(name),
			block);
	}
}
