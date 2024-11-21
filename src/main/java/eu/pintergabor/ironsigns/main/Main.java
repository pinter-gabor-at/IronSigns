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

	private static void initTags() {
		IRON_SIGN_BLOCK_TAG = TagKey.of(RegistryKeys.BLOCK, new ModIdentifier("block_tag"));
		IRON_SIGN_ITEM_TAG = TagKey.of(RegistryKeys.ITEM, new ModIdentifier("item_tag"));
		HANGING_IRON_SIGN_ITEM_TAG = TagKey.of(RegistryKeys.ITEM, new ModIdentifier("hanging_item_tag"));
	}

	private static SignVariant addToEntityBulder(
		BlockEntityType.Builder<IronSignBlockEntity> signEntityBuilder,
		BlockEntityType.Builder<HangingIronSignBlockEntity> hangingSignEntityBuilder,
		String svname) {
		SignVariant sv = new SignVariant(svname);
		signEntityBuilder
			.addBlock(sv.wallBlock)
			.addBlock(sv.block);
		hangingSignEntityBuilder
			.addBlock(sv.hangingWallBlock)
			.addBlock(sv.hangingBlock);
		return sv;
	}

	private static SignVariant[] addToEntityBulder(
		FabricBlockEntityTypeBuilder<IronSignBlockEntity> signEntityBuilder,
		FabricBlockEntityTypeBuilder<HangingIronSignBlockEntity> hangingSignEntityBuilder,
		SignVariant[] svs) {
		for (int i = 0; i < svs.length; i++) {
			svs[i] = addToEntityBulder(signEntityBuilder, hangingSignEntityBuilder,
				signColors[i].getName() + "_sign");
		}
		return svs;
	}

	/**
	 * Called from {@link Mod#onInitialize()}
	 */
	public static void init() {
		// Tags
		initTags();
		// Entity builders
		BlockEntityType.Builder<IronSignBlockEntity> signEntityBuilder =
			FabricBlockEntityTypeBuilder.create(IronSignBlockEntity::new);
		BlockEntityType.Builder<HangingIronSignBlockEntity> hangingSignEntityBuilder =
			BlockEntityType.Builder.create(HangingIronSignBlockEntity::new);
		// Iron sign
		ironSign = addToEntityBulder(signEntityBuilder, hangingSignEntityBuilder,
			"iron_sign");
		// Colored signs
		signColors = SignColor.values();
		colorSigns = addToEntityBulder(signEntityBuilder, hangingSignEntityBuilder,
			new SignVariant[signColors.length]);
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
