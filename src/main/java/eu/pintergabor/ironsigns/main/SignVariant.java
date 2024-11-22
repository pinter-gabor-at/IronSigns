package eu.pintergabor.ironsigns.main;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.blocks.IronHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallHangingSignBlock;
import eu.pintergabor.ironsigns.blocks.IronWallSignBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;

import static eu.pintergabor.ironsigns.util.Register.registerItem;

/**
 * One IronSign variant
 */
public class SignVariant {
    /**
     * Needed for loading textures
     * <p>
     * Read only outside class.
     */
    public WoodType woodType;

    /**
     * Standing sign block
     * <p>
     * Read only outside class.
     */
    public Block block;

    /**
     * Sign block attached to a wall
     * <p>
     * Read only outside class.
     */
    public Block wallBlock;

    /**
     * Hanging sign block
     * <p>
     * Read only outside class.
     */
    public Block hangingBlock;

    /**
     * Hanging sign block attaced to a wall
     * <p>
     * Read only outside class.
     */
    public Block hangingWallBlock;

    /**
     * Sign item
     * <p>
     * Read only outside class.
     */
    public Item item;

    /**
     * Hanging sign item
     * <p>
     * Read only outside class.
     */
    public Item hangingItem;

    /**
     * Create one variant of IronSign
     *
     * @param name The name of the IronSign
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
        woodType = new WoodTypeBuilder()
                .soundGroup(BlockSoundGroup.METAL)
                .hangingSignSoundGroup(BlockSoundGroup.METAL)
                .register(
                        Global.ModIdentifier(name), BlockSetType.IRON);
        // Blocks
        final AbstractBlock.Settings blockSettings = AbstractBlock.Settings.create()
                .solid().noCollision().strength(0.5f, 6.0f).requiresTool();
        block = Blocks.register(
                RegistryKey.of(RegistryKeys.BLOCK, Global.ModIdentifier(name)),
                settings -> new IronSignBlock(woodType, settings),
                blockSettings);
        wallBlock = Blocks.register(
                RegistryKey.of(RegistryKeys.BLOCK, Global.ModIdentifier("wall_" + name)),
                settings -> new IronWallSignBlock(woodType, settings),
                blockSettings);
        hangingBlock = Blocks.register(
                RegistryKey.of(RegistryKeys.BLOCK, Global.ModIdentifier("hanging_" + name)),
                settings -> new IronHangingSignBlock(woodType, settings),
                blockSettings);
        hangingWallBlock = Blocks.register(
                RegistryKey.of(RegistryKeys.BLOCK, Global.ModIdentifier("hanging_wall_" + name)),
                settings -> new IronWallHangingSignBlock(woodType, settings),
                blockSettings);
        // Items
        final Item.Settings itemSettings = new Item.Settings().maxCount(64);
        item = Items.register(
                RegistryKey.of(RegistryKeys.ITEM, Global.ModIdentifier(name)),
                settings -> new SignItem(block, wallBlock, settings),
                itemSettings);
        hangingItem = Items.register(
                RegistryKey.of(RegistryKeys.ITEM, Global.ModIdentifier("hanging_" + name)),
                settings -> new SignItem(hangingBlock, hangingWallBlock, settings),
                itemSettings);
        // Item groups
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(
                entries -> {
                    entries.add(item);
                    entries.add(hangingItem);
                });
    }
}
