package eu.pintergabor.ironsigns.datagen;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
	public ModBlockLootTableGenerator(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	/**
	 * Generate loot tables
	 */
	@Override
	public void generate() {
		// Iron sign
		generateSignBlockLoot(Main.getIronSign());
		// Color signs
		for (int i = 0; i < Main.getColorSignLength(); i++) {
			generateSignBlockLoot(Main.getColorSign(i));
		}
	}

	/**
	 * A SignBlock and a WallSignBlock drop a SignItem and
	 * a HangingSign and a HangingwallSign drop a HangingSignItem.
	 * @param sv {@link SignVariant}
	 */
	private void generateSignBlockLoot(SignVariant sv) {
		addDrop(sv.getBlock(), sv.getItem());
		addDrop(sv.getWallBlock(), sv.getItem());
		addDrop(sv.getHangingBlock(), sv.getHangingItem());
		addDrop(sv.getHangingWallBlock(), sv.getHangingItem());
	}
}
