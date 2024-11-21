package eu.pintergabor.ironsigns;

import eu.pintergabor.ironsigns.datagen.*;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;


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
//        pack.addProvider(ModBlockLootTableGenerator::new);
    }
}
