package eu.pintergabor.ironsigns.util;

import eu.pintergabor.ironsigns.Global;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public final class Register {
    private Register() {
        // Static class
    }

    /**
     * Register mod item
     *
     * @param name Name, as in lang/*.json files
     * @param item Item
     * @return The same item
     */
    @SuppressWarnings("UnusedReturnValue")
    public static Item registerItem(String name, Item item) {
        return Registry.register(
                Registries.ITEM,
                Global.ModIdentifier(name),
                item);
    }

    /**
     * Register mod block without blockitem
     *
     * @param name  Name, as in lang/*.json files
     * @param block Block
     * @return Block for chaining
     */
    @SuppressWarnings("UnusedReturnValue")
    public static Block registerBlock(String name, Block block) {
        return Registry.register(
                Registries.BLOCK,
                Global.ModIdentifier(name),
                block);
    }

    /**
     * Register blockitem
     *
     * @param name  Name, as in lang/*.json files
     * @param block Block
     */
    public static void registerBlockItem(String name, Block block) {
        Registry.register(
                Registries.ITEM,
                Global.ModIdentifier(name),
                new BlockItem(block, new Item.Settings()));
    }

    /**
     * Register mod block with blockitem
     *
     * @param name  Name, as in lang/*.json files
     * @param block Block
     * @return Block for chaining
     */
    @SuppressWarnings("UnusedReturnValue")
    public static Block registerBlockAndItem(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(
                Registries.BLOCK,
                Global.ModIdentifier(name),
                block);
    }
}
