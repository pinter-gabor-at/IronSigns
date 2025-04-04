package eu.pintergabor.ironsigns.datagen;

import java.util.Set;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import org.checkerframework.checker.units.qual.N;
import org.jetbrains.annotations.NotNull;


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
		dropOther(sv.block.get(), sv.item.get());
		dropOther(sv.wallBlock.get(), sv.item.get());
		dropOther(sv.hangingBlock.get(), sv.hangingItem.get());
		dropOther(sv.hangingWallBlock.get(), sv.hangingItem.get());
	}

	@Override
	@NotNull
	protected Iterable<Block> getKnownBlocks() {
		// The contents of our DeferredRegister.
		return Main.BLOCKS.getEntries()
			.stream()
			// Cast to Block here, otherwise it will be a ? extends Block and Java will complain.
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
