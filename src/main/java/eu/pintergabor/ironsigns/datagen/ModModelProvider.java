package eu.pintergabor.ironsigns.datagen;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    /**
     * Generate blockstates, block and item models
     */
    @Override
    public void generateBlockStateModels(
            BlockStateModelGenerator blockStateModelGenerator) {
        // Iron sign
        generateSignBlockStates(blockStateModelGenerator, Main.ironSign);
        // Color signs
        for (int i = 0; i < Main.colorSigns.length; i++) {
            generateSignBlockStates(blockStateModelGenerator, Main.colorSigns[i]);
        }
    }

    /**
     * Generate blockstates, block and item models for one {@link SignVariant}
     *
     * @param sv {@link SignVariant}
     */
    private void generateSignBlockStates(
            BlockStateModelGenerator blockStateModelGenerator, SignVariant sv) {
        // Generate blockstates, block and item models for Sign and WallSign.
        // There is no WoodBlock associated with Sign, so it behaves like a HangingSign,
        // and it is registered the same way as a HangingSign.
        blockStateModelGenerator.registerHangingSign(Blocks.IRON_BLOCK,
                sv.block, sv.wallBlock);
        // Generate blockstates, block and item models for HangingSign and HangingWallSign.
        blockStateModelGenerator.registerHangingSign(Blocks.IRON_BLOCK,
                sv.hangingBlock, sv.hangingWallBlock);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }
}
