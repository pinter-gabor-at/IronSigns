package eu.pintergabor.ironsigns.main;

import java.util.function.Function;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.blocks.IronCeilingHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronStandingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallSignBlock;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
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
	 * {@link #standingSign} id.
	 * <p>
	 * Read only outside class.
	 */
	public ResourceKey<Block> standingSignId;

	/**
	 * Sign block attached to a wall.
	 * <p>
	 * Read only outside class.
	 */
	public Block wallSign;

	/**
	 * {@link #wallSign} id.
	 * <p>
	 * Read only outside class.
	 */
	public ResourceKey<Block> wallSignId;

	/**
	 * Hanging sign block.
	 * <p>
	 * Read only outside class.
	 */
	public Block ceilingHangingSign;

	/**
	 * {@link #ceilingHangingSign} id.
	 * <p>
	 * Read only outside class.
	 */
	public ResourceKey<Block> ceilingHangingSignId;

	/**
	 * Hanging sign block attaced to a wall.
	 * <p>
	 * Read only outside class.
	 */
	public Block wallHangingSign;

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
	public Item item;

	/**
	 * {@link #item} id.
	 * <p>
	 * Read only outside class.
	 */
	public ResourceKey<Item> itemId;

	/**
	 * Hanging sign item.
	 * <p>
	 * Read only outside class.
	 */
	public Item hangingItem;

	/**
	 * {@link #hangingItem} id.
	 * <p>
	 * Read only outside class.
	 */
	public ResourceKey<Item> hangingItemId;

	/**
	 * Register one item.
	 * <p>
	 * See <a href="https://docs.fabricmc.net/develop/items/first-item">Fabric wiki</a> for details.
	 *
	 * @param id      The id of the item.
	 * @param factory The constructor of the item.
	 * @param props   Initial settings of the item.
	 * @return The registered item.
	 */
	private static @NonNull Item registerItem(
		final @NonNull ResourceKey<Item> id,
		final @NonNull Function<Item.Properties, Item> factory,
		final Item.@NonNull Properties props
	) {
		final Item item = factory.apply(props.setId(id));
		Registry.register(BuiltInRegistries.ITEM, id, item);
		return item;
	}

	/**
	 * Create and register a {@link Block} without {@link BlockItem}
	 * <p>
	 * See <a href="https://docs.fabricmc.net/develop/blocks/first-block">Fabric wiki</a> for details.
	 *
	 * @param id      The id of the block
	 * @param factory The constructor of the block.
	 * @param props   Initial settings of the block.
	 * @param <T>     The returned block type.
	 * @return The registered block.
	 */
	public static <T extends Block> @NonNull T registerBlock(
		final @NonNull ResourceKey<Block> id,
		final @NonNull Function<BlockBehaviour.Properties, T> factory,
		final BlockBehaviour.@NonNull Properties props
	) {
		final T block = factory.apply(props.setId(id));
		return Registry.register(BuiltInRegistries.BLOCK, id, block);
	}

	/**
	 * Create one variant of IronSign.
	 *
	 * @param name The name of the IronSign.
	 */
	public SignVariant(String name) {
		// WoodType is not really any type of wood, but a definition
		// of the location of the GUI texture files, and the definition of sounds.
		// Add woodType to the known WoodTypes, and then they will be used just like the vanilla
		// WoodTypes to do everything with the new signs the same ways as with the vanilla signs.
		woodType = new WoodTypeBuilder()
			.soundType(SoundType.IRON)
			.hangingSignSoundType(SoundType.IRON)
			.register(
				Global.modId(name), BlockSetType.IRON);
		// Blocks.
		final BlockBehaviour.Properties blockSettings = BlockBehaviour.Properties.of()
			.forceSolidOn()
			.noCollision()
			.strength(0.5F, 6.0F)
			.requiresCorrectToolForDrops();
		standingSignId = ResourceKey.create(Registries.BLOCK, Global.modId(name));
		standingSign = registerBlock(
			standingSignId,
			props -> new IronStandingSignBlock(woodType, props),
			blockSettings);
		wallSignId = ResourceKey.create(Registries.BLOCK, Global.modId("wall_" + name));
		wallSign = registerBlock(
			wallSignId,
			props -> new IronWallSignBlock(woodType, props),
			blockSettings);
		ceilingHangingSignId = ResourceKey.create(Registries.BLOCK, Global.modId("hanging_" + name));
		ceilingHangingSign = registerBlock(
			ceilingHangingSignId,
			props -> new IronCeilingHangingSignBlock(woodType, props),
			blockSettings);
		wallHangingSignId = ResourceKey.create(Registries.BLOCK, Global.modId("wall_hanging_" + name));
		wallHangingSign = registerBlock(
			wallHangingSignId,
			props -> new IronWallHangingSignBlock(woodType, props),
			blockSettings);
		// Items.
		final Item.Properties itemSettings = new Item.Properties().stacksTo(64);
		itemId = ResourceKey.create(Registries.ITEM, Global.modId(name));
		item = registerItem(
			itemId,
			props -> new SignItem(standingSign, wallSign, props),
			itemSettings);
		hangingItemId = ResourceKey.create(Registries.ITEM, Global.modId("hanging_" + name));
		hangingItem = registerItem(
			hangingItemId,
			props -> new SignItem(ceilingHangingSign, wallHangingSign, props),
			itemSettings);
		// Item groups.
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(
			entries -> {
				entries.insertBefore(Items.CHEST, item);
				entries.insertBefore(Items.CHEST, hangingItem);
			});
	}
}
