package eu.pintergabor.ironsigns.mixin;

import eu.pintergabor.ironsigns.config.ModConfigData;
import eu.pintergabor.ironsigns.util.FormatButtonsHandler;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;


@Mixin(AbstractSignEditScreen.class)
public abstract class AbstractSignEditScreenMixin {
	@Shadow
	@Nullable
	private TextFieldHelper signField;

	/**
	 * Create a hook at the end of init to add more screen widgets to the screen.
	 */
	@Inject(method = "init", at = @At("TAIL"))
	private void init(CallbackInfo ci) {
		FormatButtonsHandler.onScreenOpened((AbstractSignEditScreen) (Object) this);
	}

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
				signField.charTyped(new CharacterEvent(ChatFormatting.PREFIX_CODE));
				cir.setReturnValue(true);
			}
		}
	}
}
