package org.simalator.command;

import org.parser.Token;
import org.simalator.MemoryContent;
import org.simalator.Simulator;
import org.util.Util;

public class CommandMvi36 implements CommandInstruction {
	public static final byte OP_CODE = (byte) 0x36;
	public void execute(Simulator sim) {
		sim.sleep();
		String strHL = sim.getRegister8H().toString() + sim.getRegister8L().toString();		
		int posMemory = Util.stringToHex(strHL);	
		MemoryContent mcData = sim.getBus().read(sim.getRegister16PC().getValue());
		sim.sleep();
		sim.incPC();
		sim.sleep();
		sim.getBus().write(posMemory, mcData);
		sim.sleep();		
	}
	public String toString(){
		return Token.MVI.name;
	}

}
