package eu.pintergabor.ironsigns.datagen;

import eu.pintergabor.ironsigns.main.Main;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(
            FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        FabricTagBuilder tagBuilder =
                getOrCreateTagBuilder(Main.getIronSignItemTag());
        FabricTagBuilder hangingTagBuilder =
                getOrCreateTagBuilder(Main.getHangingIronSignItemTag());
        // Iron sign
        tagBuilder.add(Main.getIronSign().getItem());
        hangingTagBuilder.add(Main.getIronSign().getHangingItem());
        // Color signs
        for (int i = 0; i < Main.getColorSignLength(); i++) {
            tagBuilder.add(Main.getColorSign(i).getItem());
            hangingTagBuilder.add(Main.getColorSign(i).getHangingItem());
        }
    }
}
