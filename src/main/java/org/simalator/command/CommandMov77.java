package org.simalator.command;

import org.parser.Token;
import org.simalator.MemoryContent;
import org.simalator.Register8;
import org.simalator.Simulator;
import org.util.Util;

public class CommandMov77 implements CommandInstruction {
	public static final byte OP_CODE = (byte) 0x77;
	public void execute(Simulator sim) {
		sim.sleep();
		String strHL = sim.getRegister8H().toString() + sim.getRegister8L().toString();		
		int posMemory = Util.stringToHex(strHL);				
		MemoryContent mcReg8 = sim.getBus().read(sim.getRegister16PC().getValue());
		sim.sleep();
		sim.incPC();
		sim.sleep();
		Register8 register8 = sim.getRegister8(mcReg8.getValue());
		sim.getBus().write(posMemory, new MemoryContent( register8.getValue() ) );
		sim.sleep();
		
	}
	public String toString(){
		return Token.MOV.name;
	}
}
