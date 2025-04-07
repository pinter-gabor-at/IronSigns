package eu.pintergabor.ironsigns.datagen;

import java.util.Set;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;


public class ModBlockLootTableGenerator extends BlockLootSubProvider {

	public ModBlockLootTableGenerator(HolderLookup.Provider lookupProvider) {
		super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
	}

	/**
	 * A SignBlock and a WallSignBlock drop a SignItem and a HangingSign and a
	 * HangingWallSign drop a HangingSignItem.
	 *
	 * @param sv {@link SignVariant}.
	 */
	private void generateSignBlockLoot(SignVariant sv) {
		dropOther(sv.standingSign.get(), sv.item.get());
		dropOther(sv.wallSign.get(), sv.item.get());
		dropOther(sv.ceilingHangingSign.get(), sv.hangingItem.get());
		dropOther(sv.wallHangingSign.get(), sv.hangingItem.get());
	}

	@Override
	@NotNull
	protected Iterable<Block> getKnownBlocks() {
		return Main.BLOCKS.getEntries()
			.stream()
			.map(e -> (Block) e.get())
			.toList();
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
