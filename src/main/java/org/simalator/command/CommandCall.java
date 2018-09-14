package org.simalator.command;

import org.parser.Token;
import org.simalator.MemoryContent;
import org.simalator.Simulator;
import org.util.Util;

public class CommandCall implements CommandInstruction{
	public static final byte OP_CODE = (byte) 0xCD;
	public void execute(Simulator sim) {
		MemoryContent mcMenos = sim.getBus().read(sim.getRegister16PC().getValue());
		sim.sleep();
		sim.incPC();
		sim.sleep();
		MemoryContent mcMais = sim.getBus().read(sim.getRegister16PC().getValue());
		sim.sleep();
		sim.incPC();
		sim.sleep();
		
		
		short pcValueSP = (short) sim.getRegister16PC().getValue();
		//tratando sp
		String pcValueSPHex = Util.toHex16(pcValueSP);		
		byte posMemMenos = (byte) Util.stringToHex(pcValueSPHex.substring(2, 4));
		byte posMemMais = (byte) Util.stringToHex(pcValueSPHex.substring(0, 2));
		
		sim.sleep();
		sim.decSP();
		sim.sleep();
		//OW para mostrar a posição correta na tela
		sim.getRegister16SP().setValue(sim.getRegister16SP().getValue());
		
		sim.getBus().write(sim.getRegister16SP().getValue(), new MemoryContent(posMemMais));
		sim.sleep();
		sim.decSP();
		sim.sleep();
		sim.getBus().write(sim.getRegister16SP().getValue(), new MemoryContent(posMemMenos));
		//OW para mostrar a posição correta na tela
		sim.getRegister16SP().setValue(sim.getRegister16SP().getValue());
		
		
		String strHexMenos = Util.toHex8(mcMenos.getValue());
		String strHexMais = Util.toHex8(mcMais.getValue());
		short pcValueCall = (short) Util.stringToHex(strHexMais + strHexMenos);
		sim.sleep();
		sim.getRegister16PC().setValue(pcValueCall);
		sim.sleep();
	}
	
	public String toString(){
		return Token.CALL.name;
	}

}