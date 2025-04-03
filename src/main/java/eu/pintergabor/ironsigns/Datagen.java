package eu.pintergabor.ironsigns;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import eu.pintergabor.ironsigns.datagen.ModBlockLootTableGenerator;
import eu.pintergabor.ironsigns.datagen.ModModelProvider;
import eu.pintergabor.ironsigns.datagen.ModRecipeRunner;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Global.MODID, value = Dist.CLIENT)
public class DataGen {

	@SubscribeEvent
	public static void init(GatherDataEvent.Client event) {
		// Create recipes.
		final DataGenerator generator = event.getGenerator();
		final PackOutput output = generator.getPackOutput();
		final CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		event.addProvider(new ModRecipeRunner(output, lookupProvider));
		// Create models.
		event.addProvider(new ModModelProvider(output, Global.MODID));

//        pack.addProvider(ModBlockTagProvider::new);
//        pack.addProvider(ModItemTagProvider::new);
		// Create loot tables.
		event.addProvider(new LootTableProvider(output, Set.of(), List.of(
			new LootTableProvider.SubProviderEntry(
				ModBlockLootTableGenerator::new,
				LootContextParamSets.BLOCK)), lookupProvider));
	}
}
