package eu.pintergabor.ironsigns.main;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.blocks.IronCeilingHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronStandingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallSignBlock;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;


/**
 * One IronSign variant.
 */
public class SignVariant {

	/**
	 * Needed for loading textures and models.
	 * <p>
	 * Read only outside class.
	 */
	public WoodType woodType;

	/**
	 * Standing sign block.
	 * <p>
	 * Read only outside class.
	 */
	public DeferredBlock<Block> block;

	/**
	 * Sign block attached to a wall.
	 * <p>
	 * Read only outside class.
	 */
	public DeferredBlock<Block> wallBlock;

	/**
	 * Hanging sign block.
	 * <p>
	 * Read only outside class.
	 */
	public DeferredBlock<Block> hangingBlock;

	/**
	 * Hanging sign block attaced to a wall.
	 * <p>
	 * Read only outside class.
	 */
	public DeferredBlock<Block> hangingWallBlock;

	/**
	 * Sign item.
	 * <p>
	 * Read only outside class.
	 */
	public DeferredItem<Item> item;

	/**
	 * Hanging sign item.
	 * <p>
	 * Read only outside class.
	 */
	public DeferredItem<Item> hangingItem;

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
			// Not used, but must be defined.
			SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN);
		// Add woodType to the known WoodTypes, and then they will be used just like the vanilla
		// WoodTypes to do everything with the new signs the same ways as with the vanilla signs.
		WoodType.register(woodType);
		// Blocks.
		final BlockBehaviour.Properties blockProps = BlockBehaviour.Properties.of()
			.forceSolidOn()
			.noCollission()
			.strength(0.5F, 6.0F)
			.requiresCorrectToolForDrops();
		block = Main.BLOCKS.register(name, id ->
			new IronStandingSignBlock(woodType, blockProps
				.setId(ResourceKey.create(Registries.BLOCK, id))));
		wallBlock = Main.BLOCKS.register("wall_" + name, id ->
			new IronWallSignBlock(woodType, blockProps
				.setId(ResourceKey.create(Registries.BLOCK, id))));
		hangingBlock = Main.BLOCKS.register("hanging_" + name, id ->
			new IronCeilingHangingSignBlock(woodType, blockProps
				.setId(ResourceKey.create(Registries.BLOCK, id))));
		hangingWallBlock = Main.BLOCKS.register("hanging_wall_" + name, id ->
			new IronWallHangingSignBlock(woodType, blockProps
				.setId(ResourceKey.create(Registries.BLOCK, id))));
		// Items.
		final Item.Properties itemProps = new Item.Properties().stacksTo(64);
		item = Main.ITEMS.registerItem(name,
			props -> new SignItem(block.get(), wallBlock.get(), props),
			itemProps);
		hangingItem = Main.ITEMS.registerItem("hanging_" + name,
			props -> new SignItem(hangingBlock.get(), hangingWallBlock.get(), props),
			itemProps);
	}
}
