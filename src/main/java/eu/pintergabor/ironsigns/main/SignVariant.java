package eu.pintergabor.ironsigns.main;

import eu.pintergabor.ironsigns.blocks.IronHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallSignBlock;
import eu.pintergabor.ironsigns.util.ModIdentifier;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SignItem;
import net.minecraft.sound.BlockSoundGroup;

import static eu.pintergabor.ironsigns.util.Register.registerBlock;
import static eu.pintergabor.ironsigns.util.Register.registerItem;

/**
 * One IronSign variant
 */
public class SignVariant {
	private final WoodType woodType;
	private final Block block;
	private final Block wallBlock;
	private final Block hangingBlock;
	private final Block hangingWallBlock;
	private final Item item;
	private final Item hangingItem;

	/**
	 * Needed for loading textures
	 */
	public WoodType getWoodType() {
		return woodType;
	}

	/**
	 * Standing sign block
	 */
	public Block getBlock() {
		return block;
	}

	/**
	 * Sign block attached to a wall
	 */
	public Block getWallBlock() {
		return wallBlock;
	}

	/**
	 * Hanging sign block
	 */
	public Block getHangingBlock() {
		return hangingBlock;
	}

	/**
	 * Hanging sign block attaced to a wall
	 */
	public Block getHangingWallBlock() {
		return hangingWallBlock;
	}

	/**
	 * Sign item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Hanging sign item
	 */
	public Item getHangingItem() {
		return hangingItem;
	}

	/**
	 * Create one variant of IronSign
	 * @param name The name of the IronSign
	 */
	public SignVariant(String name) {
		// WoodType, which is not really a wood, but a definition of the location of the texture files,
		// and the definition of sounds.
		// Sign entity: resources/assets/<MODID>/textures/entity/signs/<name>.png
		// WallSign entity is the hard-coded part of the Sign entity.
		// Sign and WallSign GUIs are the same, and they are hard-coded part of the Sign entity.
		// HangingSign entity is the hard-coded part of the HangingWallSign entity.
		// HangingWallSign entity: resources/assets/<MODID>/textures/entity/signs/hanging/<name>.png
		// HangingSign GUI: resources/assets/<MODID>/textures/gui/hanging_signs/<name>.png
		woodType = (new WoodTypeBuilder())
				.soundGroup(BlockSoundGroup.METAL)
				.hangingSignSoundGroup(BlockSoundGroup.METAL)
				.register(
						new ModIdentifier(name), BlockSetType.IRON);
		// Blocks
		final FabricBlockSettings settings = FabricBlockSettings.create()
				.solid().noCollision().strength(0.5f, 6.0f).requiresTool();
		block = registerBlock(name,
				new IronSignBlock(woodType, settings));
		wallBlock = registerBlock("wall_" + name,
				new IronWallSignBlock(woodType, settings));
		hangingBlock = registerBlock("hanging_" + name,
				new IronHangingSignBlock(woodType, settings));
		hangingWallBlock = registerBlock("hanging_wall_" + name,
				new IronWallHangingSignBlock(woodType, settings));
		// Items
		item = registerItem(name,
				new SignItem(
						new FabricItemSettings().maxCount(64),
						block, wallBlock));
		hangingItem = registerItem("hanging_" + name,
				new HangingSignItem(
						hangingBlock, hangingWallBlock,
						new FabricItemSettings().maxCount(64)));
		// Item groups
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(
				entries -> {
					entries.add(item);
					entries.add(hangingItem);
				});
	}
}
