package eu.pintergabor.ironsigns.datagen;

import java.util.Arrays;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import org.jspecify.annotations.NonNull;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;


public class ModModelProvider extends ModelProvider {

	public ModModelProvider(PackOutput output) {
		super(output, Global.MODID);
	}

	/**
	 * Generate blockstates, block and item models for one {@link SignVariant}.
	 *
	 * @param sv {@link SignVariant}.
	 */
	private void generateSignBlockStates(
		@NonNull BlockModelGenerators blockStateModelGenerator,
		@NonNull SignVariant sv
	) {
		// Generate blockstates, block and item models for StandingSign and WallSign.
		// There is no WoodBlock associated with Sign, so it behaves like a HangingSign,
		// and it is registered the same way as a HangingSign.
		blockStateModelGenerator.createHangingSign(Blocks.IRON_BLOCK,
			sv.standingSign.get(), sv.wallSign.get());
		// Generate blockstates, block and item models for CeilingHangingSign and WallHangingSign.
		blockStateModelGenerator.createHangingSign(Blocks.IRON_BLOCK,
			sv.ceilingHangingSign.get(), sv.wallHangingSign.get());
	}

	/**
	 * Generate blockstates, block and item models.
	 */
	@Override
	protected void registerModels(
		@NonNull BlockModelGenerators blockModels,
		@NonNull ItemModelGenerators itemModels
	) {
		// Iron sign.
		generateSignBlockStates(blockModels, Main.ironSign);
		// Color signs.
		Arrays.stream(Main.colorSigns)
			.forEach(sv -> generateSignBlockStates(blockModels, sv));
	}
}
