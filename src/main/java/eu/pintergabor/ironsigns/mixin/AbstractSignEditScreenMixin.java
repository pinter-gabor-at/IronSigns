package eu.pintergabor.ironsigns.mixin;

import eu.pintergabor.ironsigns.config.ModConfigData;

import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


@Environment(EnvType.CLIENT)
@Mixin(AbstractSignEditScreen.class)
public abstract class AbstractSignEditScreenMixin {
	@Shadow
	@Nullable
	private TextFieldHelper signField;

	/**
	 * Insert '§' when 'Ctrl+[' is pressed.
	 * <p>
	 * Undocumented extra feature for nerds.
	 */
	@Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
	private void keyPessed(
		KeyEvent event, CallbackInfoReturnable<Boolean> cir
	) {
		// Global.LOGGER.info("Keycode: {}, Modifiers: {}", event.key(), event.modifiers());
		if (ModConfigData.enableSignTextFormatting() &&
			event.key() == GLFW.GLFW_KEY_LEFT_BRACKET && ((event.modifiers() & GLFW.GLFW_MOD_CONTROL) != 0)) {
			// Global.LOGGER.info("Ctrl+[");
			if (signField != null) {
				signField.charTyped(new CharacterEvent(ChatFormatting.PREFIX_CODE, 0));
				cir.setReturnValue(true);
			}
		}
	}
}
