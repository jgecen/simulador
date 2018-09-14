package org.parser.test.parser;

import static java.lang.System.out;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.parser.Parser;
import org.parser.exception.ParserException;
import org.parser.exception.ScannerException;
import org.simalator.MemoryContent;

public class ParseDeclareLabelTest {

	@Test
	public void test(){
		Parser parser = new Parser();
		out.println("-----------------------------");
		String program = "label1:  label1 label2: label2";
		try {
			List<MemoryContent> list = parser.parse(program);
			for (MemoryContent content : list) {
				out.println(content.getValue());
			}
		} catch (ScannerException e) {
			fail("Erro Léxico: " + e.getMessage());
		} catch (ParserException e) {
			fail("Erro Sintático: " + e.getMessage());
		}
	}
}
