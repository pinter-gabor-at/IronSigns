package eu.pintergabor.ironsigns.mixin;

import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;

import net.minecraft.world.level.block.entity.SignBlockEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;


@Mixin(AbstractSignEditScreen.class)
public interface AbstractSignEditScreenAccessor {

	@Accessor()
	SignBlockEntity getSign();
}
