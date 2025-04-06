package eu.pintergabor.ironsigns.mixin;

import eu.pintergabor.ironsigns.Global;
import eu.pintergabor.ironsigns.config.ModConfig;
import eu.pintergabor.ironsigns.util.FormatButtonsHandler;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.network.chat.Component;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;


@OnlyIn(Dist.CLIENT)
@Mixin(AbstractSignEditScreen.class)
public abstract class AbstractSignEditScreenMixin {
	@Shadow
	@Nullable
	private TextFieldHelper signField;

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
	private void keyPessed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
		Global.LOGGER.info("Keycode: {}, Modifiers: {}", keyCode, modifiers);
		if (ModConfig.enableSignTextFormatting() &&
			keyCode == GLFW.GLFW_KEY_LEFT_BRACKET && ((modifiers & GLFW.GLFW_MOD_CONTROL) != 0)) {
			Global.LOGGER.info("Ctrl+[");
			if (signField != null) {
				signField.charTyped(ChatFormatting.PREFIX_CODE);
				cir.setReturnValue(true);
			}
		}
	}
}
