package eu.pintergabor.ironsigns.main;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.Mod;
import eu.pintergabor.ironsigns.blocks.IronCeilingHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronStandingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallSignBlock;
import eu.pintergabor.ironsigns.entities.HangingIronSignBlockEntity;
import eu.pintergabor.ironsigns.entities.IronSignBlockEntity;
import org.jetbrains.annotations.NotNull;

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
	 * Initialize block types.
	 */
	private static void initBlockTypes() {
		Registry.register(BuiltInRegistries.BLOCK_TYPE,
			"iron_sign",
			IronStandingSignBlock.CODEC);
		Registry.register(BuiltInRegistries.BLOCK_TYPE,
			"iron_wall_sign",
			IronWallSignBlock.CODEC);
		Registry.register(BuiltInRegistries.BLOCK_TYPE,
			"iron_ceiling_hanging_sign",
			IronCeilingHangingSignBlock.CODEC);
		Registry.register(BuiltInRegistries.BLOCK_TYPE,
			"iron_wall_hanging_sign",
			IronWallHangingSignBlock.CODEC);
	}

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
		// Iron sign.
		ironSign = new SignVariant("iron_sign");
		// Colored signs.
		signColors = SignColor.values();
		colorSigns = new SignVariant[signColors.length];
		for (int i = 0; i < signColors.length; i++) {
			colorSigns[i] = new SignVariant(signColors[i].getName() + "_sign");
		}
	}

	/**
	 * @return an array of all standing and wall sign blocks.
	 */
	@NotNull
	private static Block[] getIronSignBlocks() {
		// Create an array of blocks associated with the entity.
		Block[] signBlocks = new Block[2 * signColors.length + 2];
		signBlocks[0] = ironSign.standingSign;
		signBlocks[1] = ironSign.wallSign;
		for (int i = 0; i < signColors.length; i++) {
			signBlocks[2 * i + 2] = colorSigns[i].standingSign;
			signBlocks[2 * i + 3] = colorSigns[i].wallSign;
		}
		return signBlocks;
	}

	/**
	 * Initialize and register {@link #ironSignEntity}.
	 */
	private static void initIronSignEntity() {
		// Build entity.
		FabricBlockEntityTypeBuilder<IronSignBlockEntity> signEntityBuilder =
			FabricBlockEntityTypeBuilder.create(
				IronSignBlockEntity::new,
				getIronSignBlocks());
		// Register entity.
		ironSignEntity = Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Global.modId("iron_sign_entity"),
			signEntityBuilder.build());
	}

	/**
	 * @return an array of all ceiling and wall hanging sign blocks.
	 */
	@NotNull
	private static Block[] getHangingSignBlocks() {
		// Create an array of blocks associated with the entity.
		var hangingSignBlocks = new Block[2 * signColors.length + 2];
		hangingSignBlocks[0] = ironSign.ceilingHangingSign;
		hangingSignBlocks[1] = ironSign.wallHangingSign;
		for (int i = 0; i < signColors.length; i++) {
			hangingSignBlocks[2 * i + 2] = colorSigns[i].ceilingHangingSign;
			hangingSignBlocks[2 * i + 3] = colorSigns[i].wallHangingSign;
		}
		return hangingSignBlocks;
	}

	/**
	 * Initialize and register {@link #hangingIronSignEntity}
	 */
	private static void initHangingIronSignEntity() {
		// Build entity.
		FabricBlockEntityTypeBuilder<HangingIronSignBlockEntity> hangingSignEntityBuilder =
			FabricBlockEntityTypeBuilder.create(
				HangingIronSignBlockEntity::new,
				getHangingSignBlocks());
		// Register entity.
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
	 * Called from {@link Mod#onInitialize()}.
	 */
	public static void init() {
		// Block types.
		initBlockTypes();
		// Tags.
		initTags();
		// Signs.
		initSigns();
		// Entities.
		initEntities();
	}
}
