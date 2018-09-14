package org.parser.test.parser;

import static org.junit.Assert.*;
import static java.lang.System.out;

import java.util.List;

import org.junit.Test;
import org.parser.Parser;
import org.parser.exception.ParserException;
import org.parser.exception.ScannerException;
import org.simalator.MemoryContent;

public class ParserMovTest {

	@Test
	public void testParseMov7F() {
		Parser parser = new Parser();
		out.println("-----------------------------");
		String program = "mov c c";
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
	public void testParseMov77() {
		Parser parser = new Parser();
		out.println("-----------------------------");
		
		String program = "mov m l\n\n" +
		"mov m b\n\n" +
		"mov x c\n";
		
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
	public void testParseMov7E() {
		Parser parser = new Parser();
		out.println("-----------------------------");
		String program = "\n\nmov r m";
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
