package com.cleanf3;

import java.util.ArrayList;
import java.util.List;

/** Decides which F3 lines to drop. */
public final class LineFilter {
	/** Every unwanted line (the chart key hints and "To edit: press [F3+F6]") contains this. */
	private static final String MARKER = "[F3+";

	private LineFilter() {}

	private static boolean hidden(Object line) {
		return line != null && String.valueOf(line).contains(MARKER);
	}

	/** Returns the list without the unwanted lines. Never modifies the list it is given. */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<String> filter(List<String> lines) {
		if (lines == null) return null;
		boolean any = false;
		for (Object o : lines) {
			if (hidden(o)) {
				any = true;
				break;
			}
		}
		if (!any) return lines;
		List out = new ArrayList(lines.size());
		for (Object o : lines) {
			if (!hidden(o)) out.add(o);
		}
		return out;
	}
}
