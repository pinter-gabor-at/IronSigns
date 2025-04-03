package eu.pintergabor.ironsigns.util;

import java.util.ArrayList;
import java.util.List;

import eu.pintergabor.ironsigns.config.ModConfig;
import eu.pintergabor.ironsigns.entities.HangingIronSignBlockEntity;
import eu.pintergabor.ironsigns.entities.IronSignBlockEntity;
import eu.pintergabor.ironsigns.mixin.AbstractSignEditScreenAccessor;
import org.jetbrains.annotations.NotNull;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;


@Environment(EnvType.CLIENT)
public class FormatButtonsHandler {

	/**
	 * An array of all color formatting enums, defined in {@link ChatFormatting}.
	 */
	private static final ChatFormatting[] colorFormattings = {
		ChatFormatting.BLACK,
		ChatFormatting.DARK_GRAY,
		ChatFormatting.DARK_BLUE,
		ChatFormatting.BLUE,
		ChatFormatting.DARK_GREEN,
		ChatFormatting.GREEN,
		ChatFormatting.DARK_AQUA,
		ChatFormatting.AQUA,
		ChatFormatting.DARK_RED,
		ChatFormatting.RED,
		ChatFormatting.DARK_PURPLE,
		ChatFormatting.LIGHT_PURPLE,
		ChatFormatting.GOLD,
		ChatFormatting.YELLOW,
		ChatFormatting.GRAY,
		ChatFormatting.WHITE
	};

	/**
	 * An array of all style formatting enums, defined in {@link ChatFormatting}.
	 */
	private static final ChatFormatting[] modifierFormattings = {
		ChatFormatting.BOLD,
		ChatFormatting.ITALIC,
		ChatFormatting.UNDERLINE,
		ChatFormatting.STRIKETHROUGH,
		ChatFormatting.RESET
	};

	/**
	 * Register {@link #onScreenOpened(Screen)} callback after opening the screen.
	 */
	public static void init() {
		// But only if Text ChatFormatting is enabled
		if (ModConfig.enableSignTextFormatting()) {
			ScreenEvents.AFTER_INIT.register((client, screen, width, height) ->
				onScreenOpened(screen)
			);
		}
	}

	/**
	 * Called after opening the screen.
	 * <p>
	 * Add color and formatting buttons to the screen.
	 *
	 * @param screen Edit screen.
	 */
	private static void onScreenOpened(Screen screen) {
		// A quick check to see if it is a sign edit screen.
		if (screen instanceof AbstractSignEditScreen es) {
			// Check configuration and add buttons if enabled.
			var config = ModConfig.getInstance();
			if ((config.enableIronSignTextFormatting && isIronSign(es)) ||
				(config.enableWoodenSignTextFormatting && isWoodenSign(es))) {
				addButtonsToScreen(es);
			}
		}
	}

	/**
	 * @param screen edit screen
	 * @return true if the edit screen is associated with an Iron Sign or with a Hanging Iron Sign
	 */
	private static boolean isIronSign(AbstractSignEditScreen screen) {
		final var sbeclass =
			((AbstractSignEditScreenAccessor) screen)
				.getSign()
				.getClass();
		return (sbeclass == IronSignBlockEntity.class) ||
			(sbeclass == HangingIronSignBlockEntity.class);
	}

	/**
	 * @param screen edit screen.
	 * @return true if the edit screen is associated with a Wooden Sign or with a Hanging Wooden Sign.
	 */
	private static boolean isWoodenSign(AbstractSignEditScreen screen) {
		final var sbeclass =
			((AbstractSignEditScreenAccessor) screen)
				.getSign()
				.getClass();
		return (sbeclass == SignBlockEntity.class) ||
			(sbeclass == HangingSignBlockEntity.class);
	}

	/**
	 * Add color and style formatting button to screen.
	 *
	 * @param es edit screen.
	 */
	private static void addButtonsToScreen(AbstractSignEditScreen es) {
		// Color buttons, 4x4
		final var colorButtons = getFormatButtons(
			es, colorFormattings,
			(es.width / 2) - 170, 70, 4);
		// Style formatting buttons, 1x5
		final var modifierButtons = getFormatButtons(
			es, modifierFormattings,
			(es.width / 2) + 50, 70, modifierFormattings.length);
		// Add them to the screen
		var screenButtons = Screens.getButtons(es);
		screenButtons.addAll(colorButtons);
		screenButtons.addAll(modifierButtons);
	}

	/**
	 * Create a list of color buttons.
	 *
	 * @param screen  Edit screen.
	 * @param formats List of formatting codes.
	 * @param xOffset Left X of the button field.
	 * @param yOffset Top Y of the button field.
	 * @param rows    Number of rows.
	 * @return The list.
	 */
	@SuppressWarnings("SameParameterValue")
	private static @NotNull List<Button> getFormatButtons(
		Screen screen, ChatFormatting[] formats,
		int xOffset, int yOffset,
		int rows) {
		List<Button> list = new ArrayList<>();
		final int gap = 0;
		final int buttonSize = 20;
		for (int i = 0; i < formats.length; i++) {
			int buttonX = xOffset + (i / rows + 1) * (buttonSize + gap);
			int buttonY = i % rows * (buttonSize + gap) + yOffset;
			list.add(
				getFormatButton(
					screen,
					buttonX, buttonY,
					buttonSize, buttonSize,
					formats[i]));
		}
		return list;
	}

	/**
	 * Create one button.
	 *
	 * @param screen       Edit screen.
	 * @param buttonX      Left X of the button.
	 * @param buttonY      Top Y of the button.
	 * @param buttonWidth  Button width.
	 * @param buttonHeight Button height.
	 * @param formatting   A formatting enum.
	 * @return The button.
	 */
	@SuppressWarnings("SameParameterValue")
	private static Button getFormatButton(
		Screen screen,
		int buttonX, int buttonY,
		int buttonWidth, int buttonHeight,
		ChatFormatting formatting) {
		// Build a button that emulates the typing of two characters:
		// The first is the formatting prefix '§',
		// the second is the formatting code.
		if (formatting.isFormat() || formatting == ChatFormatting.RESET) {
			// Text is the name of the formatting, prefixed with the formatting code.
			final String label = formatting.toString().concat(formatting.getName());
			// Build a wide button.
			return Button
				.builder(
					Component.literal(label),
					cod -> {
						screen.charTyped(ChatFormatting.PREFIX_CODE, 0);
						screen.charTyped(formatting.getChar(), 0);
					}
				)
				.pos(buttonX, buttonY)
				.size(buttonWidth * 4, buttonHeight)
				.tooltip(Tooltip.create(Component.literal(label)))
				.build();
		}
		// Text is a square, prefixed with the formatting code.
		final String label = formatting.toString().concat("⬛");
		final String tooltip = formatting.toString().concat(formatting.getName());
		// Build a normal button.
		return Button
			.builder(
				Component.literal(label),
				cod -> {
					screen.charTyped(ChatFormatting.PREFIX_CODE, 0);
					screen.charTyped(formatting.getChar(), 0);
				}
			)
			.pos(buttonX, buttonY)
			.size(buttonWidth, buttonHeight)
			.tooltip(Tooltip.create(Component.literal(tooltip)))
			.build();
	}
}
