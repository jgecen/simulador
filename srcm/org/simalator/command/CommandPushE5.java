package org.simalator.command;

import org.parser.Token;
import org.simalator.MemoryContent;
import org.simalator.Simulator;

public class CommandPushE5 implements CommandInstruction{
	public static final byte OP_CODE = (byte) 0xE5;
	public void execute(Simulator sim) {
		sim.sleep();
		sim.decSP();
		sim.sleep();
		sim.getBus().write(sim.getRegister16SP().getValue(), new MemoryContent(sim.getRegister8H().getValue()));
		sim.sleep();
		sim.decSP();
		sim.getBus().write(sim.getRegister16SP().getValue(), new MemoryContent(sim.getRegister8L().getValue()));
		sim.sleep();
	}
	
	public String toString(){
		return Token.PUSH.name +" "+ Token.H.name;
	}

}