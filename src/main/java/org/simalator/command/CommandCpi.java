package org.simalator.command;

import org.parser.Token;
import org.simalator.MemoryContent;
import org.simalator.Simulator;

public class CommandCpi implements CommandInstruction{
	public static final byte OP_CODE = (byte) 0xFE;
	public void execute(Simulator sim) {
		MemoryContent mc = sim.getBus().read(sim.getRegister16PC().getValue());
		sim.sleep();
		sim.incPC();
		sim.sleep();
		if(sim.getRegister16A().getValue() < mc.getValue()){
			sim.getFlagRegister().setCarryFlag(1);
			sim.getFlagRegister().setZeroFlag(0);
			sim.sleep();
		}else if (sim.getRegister16A().getValue() == mc.getValue()){
			sim.getFlagRegister().setZeroFlag(1);
			sim.getFlagRegister().setCarryFlag(0);
			sim.sleep();
		}else{
			sim.getFlagRegister().setZeroFlag(0);
			sim.getFlagRegister().setCarryFlag(0);		
			sim.sleep();
		}
	}
	
	public String toString(){
		return Token.CPI.name;
	}

}