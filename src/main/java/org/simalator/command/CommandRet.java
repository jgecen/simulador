package org.simalator.command;

import org.parser.Token;
import org.simalator.MemoryContent;
import org.simalator.Simulator;
import org.util.Util;

public class CommandRet implements CommandInstruction{
	public static final byte OP_CODE = (byte) 0xC9;
	public void execute(Simulator sim) {
		MemoryContent mcMenos = sim.getBus().read(sim.getRegister16SP().getValue());
		sim.sleep();
		sim.incSP();
		sim.sleep();
		//OW para mostrar a posição correta na tela
		sim.getRegister16SP().setValue(sim.getRegister16SP().getValue());
		
		MemoryContent mcMais = sim.getBus().read(sim.getRegister16SP().getValue());
		sim.sleep();
		sim.incSP();
		sim.sleep();	
		//OW para mostrar a posição correta na tela
		sim.getRegister16SP().setValue(sim.getRegister16SP().getValue());
		
		String strHexMenos = Util.toHex8(mcMenos.getValue());
		String strHexMais = Util.toHex8(mcMais.getValue());
		short pcValue = (short) Util.stringToHex(strHexMais + strHexMenos);
		sim.sleep();
		sim.getRegister16PC().setValue(pcValue);
		sim.sleep();
		
	}
	
	public String toString(){
		return Token.RET.name;
	}

}