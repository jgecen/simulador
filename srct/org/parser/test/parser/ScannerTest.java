package org.parser.test.parser;

import org.junit.Test;
import org.parser.Scanner;
import org.parser.Token;
import org.parser.exception.ScannerException;

import junit.framework.TestCase;


public class ScannerTest extends TestCase{

	@Test
	public void testAll() throws ScannerException{
		
		
		Scanner s = new Scanner("CPI CPE \n CP CNZ CNC CMP CMC CMA CM CC " +
				"CALL ANI ANA ADI ADD ADC ACI INR IN HLT EI DI org " +
				"DCX DCR DAD DAA CZ CPO LDA JZ JPO JPE JP JNZ " +
				"JNC JM JC JMP INX POP PCHL OUT ORI ORA NOP MVI " +
				"MOV LXI LHLD LDAX PUSH RAL RAR RET RC RIM RM RNC " +
				"RNZ RP RPE RPO RZ RLC RRC RST SBB SBI SHLD SIM SPHL " +
				"STA STAX STC SUB SUI XCHG XRA XRI XTHL " +
				"E A F B C D H L SP PSW M, 0B1100 0XABC -0XABC xx: xx 09 -1 8");
		while (s.token() != Token.EOF) {
			s.nextToken();
			if(s.token() == Token.BIN || s.token() == Token.DECIMAL || s.token() == Token.HEXA_0X){
				System.out.println("token: " + s.token() + " - lexema: " + s.getLexema() + " - valor numérico: " + s.getNumberValue());
			}else{
				System.out.println("token: " + s.token() + " - lexema: " + s.getLexema());
			}
			
			

		}


	}
}
