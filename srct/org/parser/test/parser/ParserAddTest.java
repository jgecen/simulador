package org.parser.test.parser;

import static org.junit.Assert.*;
import static java.lang.System.out;

import java.util.List;

import org.junit.Test;
import org.parser.Parser;
import org.parser.exception.ParserException;
import org.parser.exception.ScannerException;
import org.simalator.MemoryContent;

public class ParserAddTest {

	@Test
	public void testParseAdd1() {
		Parser parser = new Parser();
		out.println("-----------------------------");
		String program = "add c";
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
	@Test
	public void testParseAdd2() {
		Parser parser = new Parser();
		out.println("-----------------------------");
		String program = "add w";
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
	@Test
	public void testParseAddAll() {
		Parser parser = new Parser();
		out.println("-----------------------------");
		String program = "add m\n" +
						 "add c";
		try {
			List<MemoryContent> list = parser.parse(program);
			for (MemoryContent content : list) {
				out.println(content);
				out.println(content.getValue());
			}
		} catch (ScannerException e) {
			fail("Erro Léxico: " + e.getMessage());
		} catch (ParserException e) {
			fail("Erro Sintático: " + e.getMessage());
		}

	}

}
