package eu.pintergabor.ironsigns.mixin;

import eu.pintergabor.ironsigns.config.ModConfig;

import net.minecraft.SharedConstants;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
	@Inject(method = "isValidChar", at = @At("HEAD"), cancellable = true)
	private static void isValidChar(char p, CallbackInfoReturnable<Boolean> ci) {
		if (ModConfig.getInstance().enableIronSignTextFormatting) {
			// Allow items and signs to contain the formatting code prefix
			if (p == Formatting.FORMATTING_CODE_PREFIX) {
				ci.setReturnValue(true);
			}
		}
	}
}
