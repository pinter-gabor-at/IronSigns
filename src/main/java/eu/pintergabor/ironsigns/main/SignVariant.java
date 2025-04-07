package eu.pintergabor.ironsigns.main;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.blocks.IronCeilingHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronStandingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallSignBlock;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
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

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;


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
	public Block standingSign;

	/**
	 * Sign block attached to a wall.
	 * <p>
	 * Read only outside class.
	 */
	public Block wallSign;

	/**
	 * Hanging sign block.
	 * <p>
	 * Read only outside class.
	 */
	public Block ceilingHangingSign;

	/**
	 * Hanging sign block attaced to a wall.
	 * <p>
	 * Read only outside class.
	 */
	public Block wallHangingSign;

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
		// Add woodType to the known WoodTypes, and then they will be used just like the vanilla
		// WoodTypes to do everything with the new signs the same ways as with the vanilla signs.
		woodType = new WoodTypeBuilder()
			.soundGroup(SoundType.IRON)
			.hangingSignSoundGroup(SoundType.IRON)
			.register(
				Global.modId(name), BlockSetType.IRON);
		// Blocks.
		final BlockBehaviour.Properties blockSettings = BlockBehaviour.Properties.of()
			.forceSolidOn()
			.noCollission()
			.strength(0.5F, 6.0F)
			.requiresCorrectToolForDrops();
		standingSign = Blocks.register(
			ResourceKey.create(Registries.BLOCK, Global.modId(name)),
			props -> new IronStandingSignBlock(woodType, props),
			blockSettings);
		wallSign = Blocks.register(
			ResourceKey.create(Registries.BLOCK, Global.modId("wall_" + name)),
			props -> new IronWallSignBlock(woodType, props),
			blockSettings);
		ceilingHangingSign = Blocks.register(
			ResourceKey.create(Registries.BLOCK, Global.modId("ceiling_hanging_" + name)),
			props -> new IronCeilingHangingSignBlock(woodType, props),
			blockSettings);
		wallHangingSign = Blocks.register(
			ResourceKey.create(Registries.BLOCK, Global.modId("wall_hanging_" + name)),
			props -> new IronWallHangingSignBlock(woodType, props),
			blockSettings);
		// Items.
		final Item.Properties itemSettings = new Item.Properties().stacksTo(64);
		item = Items.registerItem(
			ResourceKey.create(Registries.ITEM, Global.modId(name)),
			props -> new SignItem(standingSign, wallSign, props),
			itemSettings);
		hangingItem = Items.registerItem(
			ResourceKey.create(Registries.ITEM, Global.modId("hanging_" + name)),
			props -> new SignItem(ceilingHangingSign, wallHangingSign, props),
			itemSettings);
		// Item groups.
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(
			entries -> {
				entries.addBefore(Items.CHEST, item);
				entries.addBefore(Items.CHEST, hangingItem);
			});
	}
}
