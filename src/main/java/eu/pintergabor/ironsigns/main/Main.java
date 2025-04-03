package eu.pintergabor.ironsigns.main;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.ModCommon;
import eu.pintergabor.ironsigns.entities.HangingIronSignBlockEntity;
import eu.pintergabor.ironsigns.entities.IronSignBlockEntity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;


public final class Main {

	/**
	 * All {@link SignColor} enum values in an array.
	 * <p>
	 * Read-only outside its class.
	 */
	public static SignColor[] signColors;

	/**
	 * The Iron Sign.
	 * <p>
	 * Read-only outside its class.
	 */
	public static SignVariant ironSign;

	/**
	 * Colored Iron Signs.
	 * <p>
	 * Read-only outside its class.
	 */
	public static SignVariant[] colorSigns;

	/**
	 * The BlockEntity of all Iron Signs.
	 * <p>
	 * Read-only outside its class.
	 */
	public static BlockEntityType<IronSignBlockEntity> ironSignEntity;

	/**
	 * The HangingBlockEntity of all Hanging Iron Signs.
	 * <p>
	 * Read-only outside its class.
	 */
	public static BlockEntityType<HangingIronSignBlockEntity> hangingIronSignEntity;

	/**
	 * All sign and hanging sign blocks.
	 * <p>
	 * Used to set up mineable rules. Read-only outside its class.
	 */
	public static TagKey<Block> IRON_SIGN_BLOCK_TAG;

	/**
	 * All sign items.
	 * <p>
	 * Used in coloring recipes. Read-only outside its class.
	 */
	public static TagKey<Item> IRON_SIGN_ITEM_TAG;

	/**
	 * All hanging sign items.
	 * <p>
	 * Used in coloring recipes. Read-only outside its class.
	 */
	public static TagKey<Item> HANGING_IRON_SIGN_ITEM_TAG;

	/**
	 * Initialize tags.
	 */
	private static void initTags() {
		IRON_SIGN_BLOCK_TAG = TagKey.create(Registries.BLOCK, Global.modId("block_tag"));
		IRON_SIGN_ITEM_TAG = TagKey.create(Registries.ITEM, Global.modId("item_tag"));
		HANGING_IRON_SIGN_ITEM_TAG = TagKey.create(Registries.ITEM, Global.modId("hanging_item_tag"));
	}

	/**
	 * Initialize signs.
	 */
	private static void initSigns() {
		// Iron sign
		ironSign = new SignVariant("iron_sign");
		// Colored signs
		signColors = SignColor.values();
		colorSigns = new SignVariant[signColors.length];
		for (int i = 0; i < signColors.length; i++) {
			colorSigns[i] = new SignVariant(signColors[i].getName() + "_sign");
		}
	}

	/**
	 * Initialize and register {@link #ironSignEntity}.
	 */
	private static void initIronSignEntity() {
		// Create an array of blocks associated with the entity
		Block[] signBlocks = new Block[2 * signColors.length + 2];
		signBlocks[0] = ironSign.block;
		signBlocks[1] = ironSign.wallBlock;
		for (int i = 0; i < signColors.length; i++) {
			signBlocks[2 * i + 2] = colorSigns[i].block;
			signBlocks[2 * i + 3] = colorSigns[i].wallBlock;
		}
		// Build entity.
		FabricBlockEntityTypeBuilder<IronSignBlockEntity> signEntityBuilder =
			FabricBlockEntityTypeBuilder.create(
				IronSignBlockEntity::new,
				signBlocks);
		// Register entity.
		ironSignEntity = Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Global.modId("iron_sign_entity"),
			signEntityBuilder.build());
	}

	/**
	 * Initialize and register {@link #hangingIronSignEntity}
	 */
	private static void initHangingIronSignEntity() {
		// Create an array of blocks associated with the entity
		var hangingSignBlocks = new Block[2 * signColors.length + 2];
		hangingSignBlocks[0] = ironSign.hangingBlock;
		hangingSignBlocks[1] = ironSign.hangingWallBlock;
		for (int i = 0; i < signColors.length; i++) {
			hangingSignBlocks[2 * i + 2] = colorSigns[i].hangingBlock;
			hangingSignBlocks[2 * i + 3] = colorSigns[i].hangingWallBlock;
		}
		// Build entity
		FabricBlockEntityTypeBuilder<HangingIronSignBlockEntity> hangingSignEntityBuilder =
			FabricBlockEntityTypeBuilder.create(
				HangingIronSignBlockEntity::new,
				hangingSignBlocks);
		// Register entity
		hangingIronSignEntity = Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Global.modId("hanging_iron_sign_entity"),
			hangingSignEntityBuilder.build());
	}

	/**
	 * Initialize and register entities.
	 */
	private static void initEntities() {
		initIronSignEntity();
		initHangingIronSignEntity();
	}

	/**
	 * Called from {@link ModCommon}.
	 */
	public static void init() {
		// Tags.
		initTags();
		// Signs.
		initSigns();
		// Entities.
		initEntities();
	}
}
