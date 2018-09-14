package org.parser.test;

import org.junit.Test;
import org.util.Util;

public class UtilTest {

	@Test
	public void testToHex8() {
		System.out.println(Util.toHex8(10));
		System.out.println(Util.toHex8(-10));
	}
	@Test
	public void testToHex16() {
		System.out.println(Util.toHex16(10));
		System.out.println(Util.toHex16(-10));
	}

}
