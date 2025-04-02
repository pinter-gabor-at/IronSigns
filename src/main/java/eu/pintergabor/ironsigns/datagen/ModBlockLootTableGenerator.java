package eu.pintergabor.ironsigns.datagen;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;


public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
	
    public ModBlockLootTableGenerator(
            FabricDataOutput dataOutput,
			CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    /**
     * Generate loot tables.
     */
    @Override
    public void generate() {
        // Iron sign.
        generateSignBlockLoot(Main.ironSign);
        // Color signs.
        for (int i = 0; i < Main.colorSigns.length; i++) {
            generateSignBlockLoot(Main.colorSigns[i]);
        }
    }

    /**
     * A SignBlock and a WallSignBlock drop a SignItem and a HangingSign and a
     * HangingwallSign drop a HangingSignItem.
     *
     * @param sv {@link SignVariant}.
     */
    private void generateSignBlockLoot(SignVariant sv) {
        dropOther(sv.block, sv.item);
        dropOther(sv.wallBlock, sv.item);
        dropOther(sv.hangingBlock, sv.hangingItem);
        dropOther(sv.hangingWallBlock, sv.hangingItem);
    }
}
