package eu.pintergabor.ironsigns.mixin;

import eu.pintergabor.ironsigns.config.ModConfigData;

import net.minecraft.ChatFormatting;

import net.minecraft.util.StringUtil;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


/**
 * Imported from mc-text-utilities, written by chsaw.
 * <p>
 * <a href="https://github.com/ChristopherHaws/mc-text-utilities">
 * mc-text-utilities</a>
 * <p>
 * LGPL-3 license.
 * <p>
 * Rewritten by Koi-MC <koipond.minecraft@gmail.com> when the SharedConstants class lost its isValidChar method.
 */
@Mixin(StringUtil.class)
public abstract class StringUtilMixin {

	/**
	 * Unfortunately the easiest way to keep '§' in sign texts is to enable it everywhere.
	 * <p>
	 * This may lead to incompatibilities.
	 */
	@Inject(method = "isAllowedChatCharacter", at = @At("HEAD"), cancellable = true)
	private static void isAllowedChatCharacter(char c, CallbackInfoReturnable<Boolean> cir) {
		if (ModConfigData.enableSignTextFormatting()) {
			// Allow sign texts to contain the formatting code prefix
			if (c == ChatFormatting.PREFIX_CODE) {
				cir.setReturnValue(true);
			}
		}
	}
}
