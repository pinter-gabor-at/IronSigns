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
	public DeferredBlock<Block> standingSign;

	/**
	 * {@link #standingSign} id.
	 * <p>
	 * Read only outside class.
	 */
	public ResourceKey<Block> standingSignId;

	/**
	 * Sign attached to a wall.
	 * <p>
	 * Read only outside class.
	 */
	public DeferredBlock<Block> wallSign;

	/**
	 * {@link #wallSign} id.
	 * <p>
	 * Read only outside class.
	 */
	public ResourceKey<Block> wallSignId;

	/**
	 * Sign hanging from the ceiling.
	 * <p>
	 * Read only outside class.
	 */
	public DeferredBlock<Block> ceilingHangingSign;

	/**
	 * {@link #ceilingHangingSign} id.
	 * <p>
	 * Read only outside class.
	 */
	public ResourceKey<Block> ceilingHangingSignId;

	/**
	 * Hanging sign attached to a wall.
	 * <p>
	 * Read only outside class.
	 */
	public DeferredBlock<Block> wallHangingSign;

	/**
	 * {@link #wallHangingSign} id.
	 * <p>
	 * Read only outside class.
	 */
	public ResourceKey<Block> wallHangingSignId;

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
		// of the location of the GUI texture files, and the definition of sounds.
		woodType = new WoodType(
			Global.modName(name), BlockSetType.IRON,
			SoundType.IRON, SoundType.IRON,
			// Not used, but must be defined.
			SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN);
		// Add woodType to the known WoodTypes, and then they will be used just like the vanilla
		// WoodTypes to do everything with the new signs the same ways as with the vanilla signs.
		WoodType.register(woodType);
		// Blocks.
		final BlockBehaviour.Properties blockProps = BlockBehaviour.Properties.of()
			.forceSolidOn()
			.noCollision()
			.strength(0.5F, 6.0F)
			.requiresCorrectToolForDrops();
		standingSign = Main.BLOCKS.register(name, id -> {
			standingSignId = ResourceKey.create(Registries.BLOCK, id);
			return new IronStandingSignBlock(woodType, blockProps
				.setId(standingSignId));
		});
		wallSign = Main.BLOCKS.register("wall_" + name, id -> {
			wallSignId = ResourceKey.create(Registries.BLOCK, id);
			return new IronWallSignBlock(woodType, blockProps
				.setId(wallSignId));
		});
		ceilingHangingSign = Main.BLOCKS.register("hanging_" + name, id -> {
			ceilingHangingSignId = ResourceKey.create(Registries.BLOCK, id);
			return new IronCeilingHangingSignBlock(woodType, blockProps
				.setId(ceilingHangingSignId));
		});
		wallHangingSign = Main.BLOCKS.register("wall_hanging_" + name, id -> {
			wallHangingSignId = ResourceKey.create(Registries.BLOCK, id);
			return new IronWallHangingSignBlock(woodType, blockProps
				.setId(wallHangingSignId));
		});
		// Items.
		final Item.Properties itemProps = new Item.Properties().stacksTo(64);
		item = Main.ITEMS.registerItem(name,
			props -> new SignItem(standingSign.get(), wallSign.get(), props),
			() -> itemProps);
		hangingItem = Main.ITEMS.registerItem("hanging_" + name,
			props -> new SignItem(ceilingHangingSign.get(), wallHangingSign.get(), props),
			() -> itemProps);
	}
}
