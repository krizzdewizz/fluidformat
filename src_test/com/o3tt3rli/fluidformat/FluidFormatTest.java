package com.o3tt3rli.fluidformat;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author krizz
 */
public class FluidFormatTest {

	interface Builder {
		Builder foo(int x);

		Builder bar(int x);

		String build();
	}

	@SuppressWarnings("all")
	void someSampleCode() {
		Builder b = null;
		String s = b.foo(1).bar(2).build();
	}

	@Test
	public void format() {
		String format = FluidFormat.format("b.foo(1).bar(2).build();", "__");
		System.out.println(format);
		assertEquals("b //\n__.foo(1) //\n__.bar(2) //\n__.build();", format);
	}

	@Test
	public void format2() {
		String format = FluidFormat.format("b.foo(Honk.q()).bar(Honk.q()).build();", "__");
		System.out.println(format);
		assertEquals("b //\n__.foo(Honk.q()) //\n__.bar(Honk.q()) //\n__.build();", format);
	}

	@Test
	public void format3() {
		String format = FluidFormat.format("b.foo(Honk.q).bar(Honk.q).build();", "__");
		System.out.println(format);
		assertEquals("b //\n__.foo(Honk.q) //\n__.bar(Honk.q) //\n__.build();", format);
	}

}
