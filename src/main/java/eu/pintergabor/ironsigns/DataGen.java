package eu.pintergabor.ironsigns;

import java.util.List;
import java.util.Set;

import eu.pintergabor.ironsigns.datagen.ModBlockLootTableGenerator;
import eu.pintergabor.ironsigns.datagen.ModBlockTagProvider;
import eu.pintergabor.ironsigns.datagen.ModItemTagProvider;
import eu.pintergabor.ironsigns.datagen.ModModelProvider;
import eu.pintergabor.ironsigns.datagen.ModRecipeRunner;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Global.MODID, value = Dist.CLIENT)
public class DataGen {

	public DataGen() {
		// Static class.
	}

	@SubscribeEvent
	public static void init(GatherDataEvent.Client event) {
		// Create recipes.
		event.createProvider(ModRecipeRunner::new);
		// Create models.
		event.createProvider(ModModelProvider::new);
		// Create tags.
		event.createBlockAndItemTags(ModBlockTagProvider::new, ModItemTagProvider::new);
		// Create loot tables.
		event.createProvider((output, lookupProvider) ->
			new LootTableProvider(output, Set.of(), List.of(
				new LootTableProvider.SubProviderEntry(
					ModBlockLootTableGenerator::new,
					LootContextParamSets.BLOCK)), lookupProvider));
	}
}
