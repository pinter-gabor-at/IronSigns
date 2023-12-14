package eu.pintergabor.ironsigns.util;

import eu.pintergabor.ironsigns.Global;

public class StringUtil {
	private StringUtil() {
	}

	/**
	 * Moves the {@code cursor} in the {@code string} by a {@code delta} amount.
	 * Skips surrogate characters.
	 */
	public static int moveCursor(String string, int cursor, int delta) {
		Global.LOGGER.info(string, cursor, delta);
		int len = string.length();
		if (delta >= 0) {
			for (int j = 0; cursor < len && j < delta; ++j) {
				if (!Character.isHighSurrogate(string.charAt(cursor++)) ||
					cursor >= len ||
					!Character.isLowSurrogate(string.charAt(cursor))) continue;
				++cursor;
			}
		} else {
			for (int j = delta; cursor > 0 && j < 0; ++j) {
				if (!Character.isLowSurrogate(string.charAt(--cursor)) ||
					cursor <= 0 ||
					!Character.isHighSurrogate(string.charAt(cursor - 1))) continue;
				--cursor;
			}
		}
		return cursor;
	}
}
