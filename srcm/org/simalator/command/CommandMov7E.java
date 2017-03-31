package org.simalator.command;

import org.parser.Token;
import org.simalator.MemoryContent;
import org.simalator.Register8;
import org.simalator.Simulator;
import org.util.Util;

public class CommandMov7E implements CommandInstruction {
	public static final byte OP_CODE = (byte) 0x7E;
	public void execute(Simulator sim) {
		sim.sleep();
		MemoryContent mcRegister = sim.getBus().read(sim.getRegister16PC().getValue());
		Register8 register8 = sim.getRegister8(mcRegister.getValue());
		sim.sleep();
		sim.incPC();
		sim.sleep();
		String strHL = sim.getRegister8H().toString() + sim.getRegister8L().toString();		
		int posMemory = Util.stringToHex(strHL);
		MemoryContent mcData = sim.getBus().read(posMemory);				
		register8.setValue(mcData.getValue());
		sim.sleep();
		
	}
	public String toString(){
		return Token.MOV.name;
	}

}
