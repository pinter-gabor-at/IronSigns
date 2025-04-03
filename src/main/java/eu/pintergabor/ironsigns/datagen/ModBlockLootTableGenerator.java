package eu.pintergabor.ironsigns.datagen;

import java.util.Set;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;


public class ModBlockLootTableGenerator extends BlockLootSubProvider {

	public ModBlockLootTableGenerator(HolderLookup.Provider lookupProvider) {
		super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
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
