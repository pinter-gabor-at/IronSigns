package eu.pintergabor.ironsigns.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;


public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {

	public ModBlockLootTableGenerator(
		FabricDataOutput dataOutput,
		CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	/**
	 * A SignBlock and a WallSignBlock drop a SignItem and a HangingSign and a
	 * HangingWallSign drop a HangingSignItem.
	 *
	 * @param sv {@link SignVariant}.
	 */
	private void generateSignBlockLoot(SignVariant sv) {
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
		for (int i = 0; i < Main.colorSigns.length; i++) {
			generateSignBlockLoot(Main.colorSigns[i]);
		}
	}
}
