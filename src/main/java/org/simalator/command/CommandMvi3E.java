package org.simalator.command;

import org.parser.Token;
import org.simalator.MemoryContent;
import org.simalator.Register8;
import org.simalator.Simulator;

public class CommandMvi3E implements CommandInstruction {
	public static final byte OP_CODE = (byte) 0x3E;

	public void execute(Simulator sim) {
		sim.sleep();
		MemoryContent mcRegister = sim.getBus().read(sim.getRegister16PC().getValue());
		Register8 register8 = sim.getRegister8(mcRegister.getValue());
		sim.sleep();
		sim.incPC();
		sim.sleep();
		MemoryContent mcData = sim.getBus().read(sim.getRegister16PC().getValue());
		sim.sleep();
		sim.incPC();
		sim.sleep();		
		register8.setValue(mcData.getValue());
		sim.sleep();		

	}

	public String toString() {
		return Token.MVI.name;
	}

}
