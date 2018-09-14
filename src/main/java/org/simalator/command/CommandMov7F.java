package org.simalator.command;

import org.parser.Token;
import org.simalator.MemoryContent;
import org.simalator.Register8;
import org.simalator.Simulator;

public class CommandMov7F implements CommandInstruction {
	public static final byte OP_CODE = (byte) 0x7F;
	public void execute(Simulator sim) {
		MemoryContent reg1MC = sim.getBus().read(sim.getRegister16PC().getValue());
		sim.sleep();
		sim.incPC();
		sim.sleep();
		MemoryContent reg2MC = sim.getBus().read(sim.getRegister16PC().getValue());
		sim.sleep();
		sim.incPC();
		sim.sleep();
		Register8 register81 = sim.getRegister8(reg1MC.getValue());
		Register8 register82 = sim.getRegister8(reg2MC.getValue());
		register81.setValue(register82.getValue());
		sim.sleep();
		
	}
	public String toString(){
		return Token.MOV.name;
	}

}
