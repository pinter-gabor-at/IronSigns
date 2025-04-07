package eu.pintergabor.ironsigns;

import eu.pintergabor.ironsigns.datagen.ModBlockLootTableGenerator;
import eu.pintergabor.ironsigns.datagen.ModBlockTagProvider;
import eu.pintergabor.ironsigns.datagen.ModItemTagProvider;
import eu.pintergabor.ironsigns.datagen.ModModelProvider;
import eu.pintergabor.ironsigns.datagen.ModRecipeRunner;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;


public class DataGen implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModBlockLootTableGenerator::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeRunner::new);
	}
}
