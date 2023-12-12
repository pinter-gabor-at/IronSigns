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
		// Static class
	}

	/**
	 * All {@link SignColor} enum values in an array
	 */
	private static SignColor[] signColors;

	/**
	 * Get one item from {@link SignColor} enum by index
	 * @param ix
	 * @return {@link SignColor} enum
	 */
	public static SignColor getSignColors(int ix) {
		return signColors[ix];
	}

	private static SignVariant ironSign;
	private static SignVariant[] colorSigns;

	private static BlockEntityType<IronSignBlockEntity> ironSignEntity;
	private static BlockEntityType<HangingIronSignBlockEntity> hangingIronSignEntity;

	public static SignVariant getIronSign() {
		return ironSign;
	}

	/**
	 * The number of colors
	 * @return (currently 16)
	 */
	public static int getColorSignLength() {
		return colorSigns.length;
	}

	/**
	 * Get one colored sign by index
	 * @param ix index
	 * @return Colored sign
	 */
	public static SignVariant getColorSign(int ix) {
		return colorSigns[ix];
	}

	/**
	 * The only entity for all IronSigns
	 */
	public static BlockEntityType<IronSignBlockEntity> getIronSignEntity() {
		return ironSignEntity;
	}

	/**
	 * The only entity for all hanging IronSigns
	 */
	public static BlockEntityType<HangingIronSignBlockEntity> getHangingIronSignEntity() {
		return hangingIronSignEntity;
	}

	private static TagKey<Block> IRON_SIGN_BLOCK_TAG;
	private static TagKey<Item> IRON_SIGN_ITEM_TAG;
	private static TagKey<Item> HANGING_IRON_SIGN_ITEM_TAG;

	/**
	 * All sign and hanging sign blocks
	 * <p>
	 * Used to set up mineable rules
	 */
	public static TagKey<Block> getIronSignBlockTag() {
		return IRON_SIGN_BLOCK_TAG;
	}

	/**
	 * All sign items
	 * <p>
	 * Used in coloring recipes
	 */
	public static TagKey<Item> getIronSignItemTag() {
		return IRON_SIGN_ITEM_TAG;
	}

	/**
	 * All hanging sign items
	 * <p>
	 * Used in coloring recipes
	 */
	public static TagKey<Item> getHangingIronSignItemTag() {
		return HANGING_IRON_SIGN_ITEM_TAG;
	}


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
			.addBlock(ironSign.getWallBlock())
			.addBlock(ironSign.getBlock());
		hangingSignEntityBuilder
			.addBlock(ironSign.getHangingWallBlock())
			.addBlock(ironSign.getHangingBlock());
		// Colored signs
		signColors = SignColor.values();
		colorSigns = new SignVariant[signColors.length];
		for (int i = 0; i < signColors.length; i++) {
			colorSigns[i] = new SignVariant(getSignColors(i).getName() + "_sign");
			signEntityBuilder
				.addBlock(colorSigns[i].getWallBlock())
				.addBlock(colorSigns[i].getBlock());
			hangingSignEntityBuilder
				.addBlock(colorSigns[i].getHangingWallBlock())
				.addBlock(colorSigns[i].getHangingBlock());
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
