package eu.pintergabor.ironsigns.main;

import eu.pintergabor.ironsigns.Mod;
import eu.pintergabor.ironsigns.entities.HangingIronSignBlockEntity;
import eu.pintergabor.ironsigns.entities.IronSignBlockEntity;
import eu.pintergabor.ironsigns.util.ModIdentifier;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;


public final class Main {
	private Main() {
	}

	/**
	 * All {@link SignColor} enum values in an array
	 * <p>
	 * Read-only outside its class.
	 */
	public static SignColor[] signColors;

	/**
	 * The Iron Sign
	 * <p>
	 * Read-only outside its class.
	 */
	public static SignVariant ironSign;

	/**
	 * Colored Iron Signs
	 * <p>
	 * Read-only outside its class.
	 */
	public static SignVariant[] colorSigns;

	/**
	 * The BlockEntity of all Iron Signs
	 * <p>
	 * Read-only outside its class.
	 */
	public static BlockEntityType<IronSignBlockEntity> ironSignEntity;

	/**
	 * The HangingBlockEntity of all Hanging Iron Signs
	 * <p>
	 * Read-only outside its class.
	 */
	public static BlockEntityType<HangingIronSignBlockEntity> hangingIronSignEntity;

	/**
	 * All sign and hanging sign blocks
	 * <p>
	 * Used to set up mineable rules. Read-only outside its class.
	 */
	public static TagKey<Block> IRON_SIGN_BLOCK_TAG;

	/**
	 * All sign items
	 * <p>
	 * Used in coloring recipes. Read-only outside its class.
	 */
	public static TagKey<Item> IRON_SIGN_ITEM_TAG;

	/**
	 * All hanging sign items
	 * <p>
	 * Used in coloring recipes. Read-only outside its class.
	 */
	public static TagKey<Item> HANGING_IRON_SIGN_ITEM_TAG;

	/**
	 * Called from {@link Mod#onInitialize()}
	 */
	public static void init() {
		// Tags
		IRON_SIGN_BLOCK_TAG = TagKey.of(RegistryKeys.BLOCK, new ModIdentifier("block_tag"));
		IRON_SIGN_ITEM_TAG = TagKey.of(RegistryKeys.ITEM, new ModIdentifier("item_tag"));
		HANGING_IRON_SIGN_ITEM_TAG = TagKey.of(RegistryKeys.ITEM, new ModIdentifier("hanging_item_tag"));
		// Entity builders
		FabricBlockEntityTypeBuilder<IronSignBlockEntity> signEntityBuilder =
			FabricBlockEntityTypeBuilder.create(IronSignBlockEntity::new);
		FabricBlockEntityTypeBuilder<HangingIronSignBlockEntity> hangingSignEntityBuilder =
			FabricBlockEntityTypeBuilder.create(HangingIronSignBlockEntity::new);
		// Iron sign
		ironSign = new SignVariant("iron_sign");
		signEntityBuilder
			.addBlock(ironSign.wallBlock)
			.addBlock(ironSign.block);
		hangingSignEntityBuilder
			.addBlock(ironSign.hangingWallBlock)
			.addBlock(ironSign.hangingBlock);
		// Colored signs
		signColors = SignColor.values();
		colorSigns = new SignVariant[signColors.length];
		for (int i = 0; i < signColors.length; i++) {
			colorSigns[i] = new SignVariant(signColors[i].getName() + "_sign");
			signEntityBuilder
				.addBlock(colorSigns[i].wallBlock)
				.addBlock(colorSigns[i].block);
			hangingSignEntityBuilder
				.addBlock(colorSigns[i].hangingWallBlock)
				.addBlock(colorSigns[i].hangingBlock);
		}
		// Register entities
		ironSignEntity = Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			new ModIdentifier("iron_sign_entity"),
			signEntityBuilder.build());
		hangingIronSignEntity = Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			new ModIdentifier("hanging_iron_sign_entity"),
			hangingSignEntityBuilder.build(null));
	}
}
