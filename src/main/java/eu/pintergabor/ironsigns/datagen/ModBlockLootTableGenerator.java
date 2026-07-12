package eu.pintergabor.ironsigns.datagen;

import java.util.Arrays;
import java.util.Set;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;


public class ModBlockLootTableGenerator extends BlockLootSubProvider {

	public ModBlockLootTableGenerator(final HolderLookup.Provider lookupProvider) {
		super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
	}

	/**
	 * A SignBlock and a WallSignBlock drop a SignItem and a HangingSign and a
	 * HangingWallSign drop a HangingSignItem.
	 *
	 * @param sv {@link SignVariant}.
	 */
	private void generateSignBlockLoot(final @NonNull SignVariant sv) {
		dropOther(sv.standingSign.get(), sv.item.get());
		dropOther(sv.wallSign.get(), sv.item.get());
		dropOther(sv.ceilingHangingSign.get(), sv.hangingItem.get());
		dropOther(sv.wallHangingSign.get(), sv.hangingItem.get());
	}

	@Override
	@NonNull
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
		Arrays.stream(Main.colorSigns).forEach(this::generateSignBlockLoot);
	}
}
