package eu.pintergabor.ironsigns.datagen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;


public final class ModBlockLootTableGenerator extends FabricBlockLootSubProvider {

	public ModBlockLootTableGenerator(
		FabricPackOutput dataOutput,
		CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	/**
	 * A SignBlock and a WallSignBlock drop a SignItem and a HangingSign and a
	 * HangingWallSign drop a HangingSignItem.
	 *
	 * @param sv {@link SignVariant}.
	 */
	private void generateSignBlockLoot(final @NonNull SignVariant sv) {
		dropOther(sv.standingSign, sv.item);
		dropOther(sv.wallSign, sv.item);
		dropOther(sv.ceilingHangingSign, sv.hangingItem);
		dropOther(sv.wallHangingSign, sv.hangingItem);
	}

	/**
	 * Generate loot tables.
	 */
	@Override
	public void generate() {
		// Iron sign.
		generateSignBlockLoot(Main.ironSign);
		// Color signs.
		Arrays.stream(Main.colorSigns).forEach(this::generateSignBlockLoot);
	}
}
