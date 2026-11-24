package eu.pintergabor.ironsigns;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import eu.pintergabor.ironsigns.datagen.ModBlockLootTableGenerator;
import eu.pintergabor.ironsigns.datagen.ModBlockTagProvider;
import eu.pintergabor.ironsigns.datagen.ModItemTagProvider;
import eu.pintergabor.ironsigns.datagen.ModModelProvider;
import eu.pintergabor.ironsigns.datagen.ModRecipeGenerator;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;


public final class ModDataGenerator {

	private ModDataGenerator() {
		// Static class.
	}

	public static void listen0(@NonNull GatherDataRegistryEntriesEvent event) {
		event
			// Create recipes.
			.add(ModRecipeGenerator.create())
			// Create loot tables.
			.add(Registries.LOOT_TABLE, new LootTableProvider(Set.of(), List.of(
				new LootTableProvider.SubProviderEntry(ModBlockLootTableGenerator::new, LootContextParamSets.BLOCK)
			)));
	}

	public static void listen1(GatherDataEvent.@NonNull Client event) {
		final DataGenerator generator = event.getGenerator();
		final PackOutput output = generator.getPackOutput();
		final CompletableFuture<HolderLookup.Provider> lookup =
			event.getReloadableLookupProvider();
		// Create models.
		generator.addProvider(true, new ModModelProvider(output));
		// Create tags.
		generator.addProvider(true, new ModBlockTagProvider(output, lookup));
		generator.addProvider(true, new ModItemTagProvider(output, lookup));
	}
}
