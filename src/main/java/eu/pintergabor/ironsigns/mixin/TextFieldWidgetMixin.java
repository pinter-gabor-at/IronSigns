package eu.pintergabor.ironsigns.mixin;

import eu.pintergabor.ironsigns.util.FormattingUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.gui.widget.TextFieldWidget;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * Imported from mc-text-utilities, written by chsaw
 * <p>
 * <a href="https://github.com/ChristopherHaws/mc-text-utilities">
 * mc-text-utilities</a>
 * <p>
 * LGPL-3 license
 */
@Environment(EnvType.CLIENT)
@Mixin(TextFieldWidget.class)
public abstract class TextFieldWidgetMixin {
	@Redirect(method = "renderWidget", at = @At(value = "INVOKE", target = "Ljava/lang/String;substring(I)Ljava/lang/String;", ordinal = 1))
	private String appendFormatting(String string, int i) {
		var strings = FormattingUtils.splitWithFormatting(string, i);
		return FormattingUtils.getLastFormattingCodes(strings.getLeft(), 2)
			.concat(strings.getRight());
	}
}
