package eu.pintergabor.ironsigns.main;

import com.mojang.serialization.MapCodec;
import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.ModCommon;
import eu.pintergabor.ironsigns.blocks.IronCeilingHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronStandingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallSignBlock;
import eu.pintergabor.ironsigns.entities.HangingIronSignBlockEntity;
import eu.pintergabor.ironsigns.entities.IronSignBlockEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;


/**
 * Create, register and hold all blocks and items.
 * <p>
 * Entity types must be registered with a list of blocks they are associated with.
 * Therefore, existing vanilla entity types cannot be used, because they are already
 * registered with their list of blocks.
 * <p>
 * Blocks must create their associated entities.
 * Therefore, existing vanilla blocks cannot be used, because they are associated with
 * different entity types.
 * <p>
 * And, finally, new block types must be created, because the codecs are static
 * in the block classes, and therefore new blocks cannot inherit them.
 */
public final class Main {
	public static final DeferredRegister.Items ITEMS =
		DeferredRegister.createItems(Global.MODID);
	public static final DeferredRegister<MapCodec<? extends Block>> BLOCK_TYPES =
		DeferredRegister.create(BuiltInRegistries.BLOCK_TYPE, Global.MODID);
	public static final DeferredRegister.Blocks BLOCKS =
		DeferredRegister.createBlocks(Global.MODID);
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
		DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Global.MODID);

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
	public static DeferredHolder<BlockEntityType<?>, BlockEntityType<IronSignBlockEntity>> ironSignEntity;

	/**
	 * The HangingBlockEntity of all Hanging Iron Signs.
	 * <p>
	 * Read-only outside its class.
	 */
	public static DeferredHolder<BlockEntityType<?>, BlockEntityType<HangingIronSignBlockEntity>> hangingIronSignEntity;

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
		BLOCK_TYPES.register(
			"iron_sign",
			() -> IronStandingSignBlock.CODEC
		);
		BLOCK_TYPES.register(
			"iron_wall_sign",
			() -> IronWallSignBlock.CODEC
		);
		BLOCK_TYPES.register(
			"iron_hanging_sign",
			() -> IronCeilingHangingSignBlock.CODEC
		);
		BLOCK_TYPES.register(
			"iron_wall_hanging_sign",
			() -> IronWallHangingSignBlock.CODEC
		);
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
		signBlocks[0] = ironSign.standingSign.get();
		signBlocks[1] = ironSign.wallSign.get();
		for (int i = 0; i < signColors.length; i++) {
			signBlocks[2 * i + 2] = colorSigns[i].standingSign.get();
			signBlocks[2 * i + 3] = colorSigns[i].wallSign.get();
		}
		return signBlocks;
	}

	/**
	 * Initialize and register {@link #ironSignEntity}.
	 */
	private static void initIronSignEntity() {
		// Build and register entity.
		ironSignEntity = BLOCK_ENTITY_TYPES.register(
			"iron_sign_entity",
			() -> new BlockEntityType<>(
				IronSignBlockEntity::new,
				getIronSignBlocks()));
	}

	/**
	 * @return an array of all ceiling and wall hanging sign blocks.
	 */
	@NotNull
	private static Block[] getHangingSignBlocks() {
		// Create an array of blocks associated with the entity.
		var hangingSignBlocks = new Block[2 * signColors.length + 2];
		hangingSignBlocks[0] = ironSign.ceilingHangingSign.get();
		hangingSignBlocks[1] = ironSign.wallHangingSign.get();
		for (int i = 0; i < signColors.length; i++) {
			hangingSignBlocks[2 * i + 2] = colorSigns[i].ceilingHangingSign.get();
			hangingSignBlocks[2 * i + 3] = colorSigns[i].wallHangingSign.get();
		}
		return hangingSignBlocks;
	}

	/**
	 * Initialize and register {@link #hangingIronSignEntity}
	 */
	private static void initHangingIronSignEntity() {
		// Build and register entity.
		hangingIronSignEntity = BLOCK_ENTITY_TYPES.register(
			"hanging_iron_sign_entity",
			() -> new BlockEntityType<>(
				HangingIronSignBlockEntity::new,
				getHangingSignBlocks()));
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
	public static void init(IEventBus modEventBus) {
		// Block types.
		initBlockTypes();
		BLOCK_TYPES.register(modEventBus);
		// Tags.
		initTags();
		// Signs.
		initSigns();
		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);
		// Entities.
		initEntities();
		BLOCK_ENTITY_TYPES.register(modEventBus);
	}
}
