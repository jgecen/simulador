package org.parser.test.parser;

import static org.junit.Assert.*;
import static java.lang.System.out;

import java.util.List;

import org.junit.Test;
import org.parser.Parser;
import org.parser.exception.ParserException;
import org.parser.exception.ScannerException;
import org.simalator.MemoryContent;

public class ParserMviTest {

	@Test
	public void testParseMvi() {
		Parser parser = new Parser();
		out.println("-----------------------------");
		
		String program = "mvi b 0x0\n" +
		 "mvi m 0b100\n" +
		 "mvi b 0xa1\n";
		try {
			List<MemoryContent> list = parser.parse(program);
			for (MemoryContent content : list) {
				out.println(content);
			}
		} catch (ScannerException e) {
			fail("Erro Léxico: " + e.getMessage());
		} catch (ParserException e) {
			fail("Erro Sintático: " + e.getMessage());
		}
	}
	
}
