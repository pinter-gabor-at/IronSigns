package eu.pintergabor.ironsigns.mixin;

import eu.pintergabor.ironsigns.config.ModConfigData;
import eu.pintergabor.ironsigns.util.StringUtil2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.Util;


@Mixin(Util.class)
public abstract class UtilMixin {

	/**
	 * Rewrite {@link Util#offsetByCodepoints} to work the same way as the original one, but skip formatting codes too.
	 */
	@Inject(method = "offsetByCodepoints", at = @At("HEAD"), cancellable = true)
	private static void newMoveCursor(
		String string, int cursor, int delta,
		CallbackInfoReturnable<Integer> cir
	) {
		if (ModConfigData.enableSignTextFormatting()) {
			cir.setReturnValue(StringUtil2.moveCursor(string, cursor, delta));
		}
	}
}
