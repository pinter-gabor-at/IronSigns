package eu.pintergabor.ironsigns.util;

import java.util.Optional;
import java.util.Stack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.util.Formatting;
import net.minecraft.util.Pair;

/**
 * Imported from mc-text-utilities, written by chsaw
 * <p>
 * <a href="https://github.com/ChristopherHaws/mc-text-utilities">
 * mc-text-utilities</a>
 * <p>
 * LGPL-3 license
 */
public class FormattingUtils {
	private FormattingUtils() {
	}

	public static Optional<Formatting> getFormattingCode(
		@Nullable String string, int startIndex) {
		if (string == null) {
			return Optional.empty();
		}

		if (startIndex < 0) {
			return Optional.empty();
		}

		var length = string.length();
		var lastIndex = length - 1;
		var nextIndex = startIndex + 1;

		if (startIndex > lastIndex || nextIndex > lastIndex) {
			return Optional.empty();
		}

		var left = string.charAt(startIndex);
		if (left != Formatting.FORMATTING_CODE_PREFIX) {
			return Optional.empty();
		}

		var right = string.charAt(nextIndex);
		var formatting = Formatting.byCode(right);
		if (formatting == null) {
			return Optional.empty();
		}

		return Optional.of(formatting);
	}

	public static @NotNull Pair<String, String> splitWithFormatting(
		String string, int index) {
		var previousIndex = index - 1;
		var formattingCode = getFormattingCode(string, previousIndex);
		if (formattingCode.isPresent()) {
			return new Pair<>(
				string.substring(0, previousIndex),
				string.substring(previousIndex)
			);
		}

		return new Pair<>(
			string.substring(0, index),
			string.substring(index)
		);
	}

	public static @NotNull String getLastFormattingCodes(
		@Nullable String string, int count) {
		if (string == null || string.isEmpty()) {
			return "";
		}

		if (count <= 0) {
			return "";
		}

		var formattingCodes = new Stack<Formatting>();

		for (var rightIndex = string.length() - 1; rightIndex >= 0; rightIndex--) {
			if (formattingCodes.size() == count) {
				break;
			}

			var leftIndex = rightIndex - 1;
			if (leftIndex < 0) {
				break;
			}

			var leftChar = string.charAt(leftIndex);
			if (leftChar != Formatting.FORMATTING_CODE_PREFIX) {
				continue;
			}

			var rightChar = string.charAt(rightIndex);
			var formatting = Formatting.byCode(rightChar);
			if (formatting == null) {
				continue;
			}

			formattingCodes.push(formatting);
		}

		var sb = new StringBuilder();

		while (!formattingCodes.empty()) {
			var formattingCode = formattingCodes.pop();
			sb.append(formattingCode.toString());
		}

		var lastChar = string.charAt(string.length() - 1);
		if (lastChar == Formatting.FORMATTING_CODE_PREFIX) {
			sb.append(lastChar);
		}

		return sb.toString();
	}
}
