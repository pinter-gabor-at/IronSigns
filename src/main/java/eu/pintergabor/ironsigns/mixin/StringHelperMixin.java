package eu.pintergabor.ironsigns.mixin;

import eu.pintergabor.ironsigns.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.util.Formatting;
import net.minecraft.util.StringHelper;


/**
 * Imported from mc-text-utilities, written by chsaw
 * <p>
 * <a href="https://github.com/ChristopherHaws/mc-text-utilities">
 * mc-text-utilities</a>
 * <p>
 * LGPL-3 license
 * <p>
 * Rewritten by Koi-MC <koipond.minecraft@gmail.com> when the SharedConstants class lost its isValidChar method.
 */
@Mixin(StringHelper.class)
public abstract class StringHelperMixin {
	/**
	 * Unfortunately the easiest way to keep '§' in sign texts is to enable it everywhere
	 * <p>
	 * This may lead to incompatibilities.
	 */
	@Inject(method = "isValidChar", at = @At("HEAD"), cancellable = true)
	private static void isValidChar(char c, CallbackInfoReturnable<Boolean> cir) {
		if (ModConfig.enableSignTextFormatting()) {
			// Allow sign texts to contain the formatting code prefix
			if (c == Formatting.FORMATTING_CODE_PREFIX) {
				cir.setReturnValue(true);
			}
		}
	}
}
