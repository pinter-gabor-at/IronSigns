package eu.pintergabor.ironsigns.mixin;

import eu.pintergabor.ironsigns.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.SharedConstants;
import net.minecraft.util.Formatting;

/**
 * Imported from mc-text-utilities, written by chsaw
 * <p>
 * <a href="https://github.com/ChristopherHaws/mc-text-utilities">
 * mc-text-utilities</a>
 * <p>
 * LGPL-3 license
 */
@Mixin(SharedConstants.class)
public abstract class SharedConstantsMixin {
	/**
	 * Unfortunately the easiest way to keep '§' in sign texts is to enable it everywhere
	 * <p>
	 * This may lead to incompatibilities.
	 */
	@Inject(method = "isValidChar", at = @At("HEAD"), cancellable = true)
	private static void isValidChar(char c, CallbackInfoReturnable<Boolean> cir) {
		if (ModConfig.enableTextFormatting()) {
			// Allow sign texts to contain the formatting code prefix
			if (c == Formatting.FORMATTING_CODE_PREFIX) {
				cir.setReturnValue(true);
			}
		}
	}
}
