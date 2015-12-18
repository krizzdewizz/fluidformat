package com.o3tt3rli.fluidformat;

/**
 * @author krizz
 */
public class FluidFormat {

	public static String format(final String s, String indent) {
		String ret = "";
		int parenDepth = 0;
		for (char c : s.toCharArray()) {
			if (c == '.' && parenDepth == 0) {
				ret += " //\n" + indent;
			} else if (c == '(') {
				parenDepth++;
			} else if (c == ')') {
				parenDepth--;
			}
			ret += c;
		}

		return ret;
	}
}
