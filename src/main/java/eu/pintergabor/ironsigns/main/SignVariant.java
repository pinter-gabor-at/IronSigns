package eu.pintergabor.ironsigns.main;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.blocks.IronHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallSignBlock;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;


/**
 * One IronSign variant.
 */
public class SignVariant {
	/**
	 * Needed for loading textures.
	 * <p>
	 * Read only outside class.
	 */
	public WoodType woodType;

	/**
	 * Standing sign block.
	 * <p>
	 * Read only outside class.
	 */
	public Block block;

	/**
	 * Sign block attached to a wall.
	 * <p>
	 * Read only outside class.
	 */
	public Block wallBlock;

	/**
	 * Hanging sign block.
	 * <p>
	 * Read only outside class.
	 */
	public Block hangingBlock;

	/**
	 * Hanging sign block attaced to a wall.
	 * <p>
	 * Read only outside class.
	 */
	public Block hangingWallBlock;

	/**
	 * Sign item.
	 * <p>
	 * Read only outside class.
	 */
	public Item item;

	/**
	 * Hanging sign item.
	 * <p>
	 * Read only outside class.
	 */
	public Item hangingItem;

	/**
	 * Create one variant of IronSign.
	 *
	 * @param name The name of the IronSign.
	 */
	public SignVariant(String name) {
		// WoodType is not really any type of wood, but a definition
		// of the location of the texture files, and the definition of sounds.
		// Sign entity: resources/assets/<MODID>/textures/entity/signs/<name>.png
		// WallSign entity is the hard-coded part of the Sign entity.
		// Sign and WallSign GUIs are the same, and they are hard-coded part of the Sign entity.
		// HangingSign entity is the hard-coded part of the HangingWallSign entity.
		// HangingWallSign entity: resources/assets/<MODID>/textures/entity/signs/hanging/<name>.png
		// HangingSign GUI: resources/assets/<MODID>/textures/gui/hanging_signs/<name>.png
		woodType = new WoodType(
			Global.modName(name), BlockSetType.IRON,
			SoundType.IRON, SoundType.IRON,
			SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN);
		// Blocks.
		final BlockBehaviour.Properties blockSettings = BlockBehaviour.Properties.of()
			.forceSolidOn()
			.noCollission()
			.strength(0.5F, 6.0F)
			.requiresCorrectToolForDrops();
		block = Blocks.register(
			ResourceKey.create(Registries.BLOCK, Global.modId(name)),
			settings -> new IronSignBlock(woodType, settings),
			blockSettings);
		wallBlock = Blocks.register(
			ResourceKey.create(Registries.BLOCK, Global.modId("wall_" + name)),
			settings -> new IronWallSignBlock(woodType, settings),
			blockSettings);
		hangingBlock = Blocks.register(
			ResourceKey.create(Registries.BLOCK, Global.modId("hanging_" + name)),
			settings -> new IronHangingSignBlock(woodType, settings),
			blockSettings);
		hangingWallBlock = Blocks.register(
			ResourceKey.create(Registries.BLOCK, Global.modId("hanging_wall_" + name)),
			settings -> new IronWallHangingSignBlock(woodType, settings),
			blockSettings);
		// Items.
		final Item.Properties itemSettings = new Item.Properties().stacksTo(64);
		item = Items.registerItem(
			ResourceKey.create(Registries.ITEM, Global.modId(name)),
			settings -> new SignItem(block, wallBlock, settings),
			itemSettings);
		hangingItem = Items.registerItem(
			ResourceKey.create(Registries.ITEM, Global.modId("hanging_" + name)),
			settings -> new SignItem(hangingBlock, hangingWallBlock, settings),
			itemSettings);
		// Item groups.
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(
			entries -> {
				entries.prepend(item);
				entries.prepend(hangingItem);
			});
	}
}
