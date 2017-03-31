package org.simalator.command;

import org.parser.Token;
import org.simalator.MemoryContent;
import org.simalator.Simulator;

public class CommandAni implements CommandInstruction{
	public static final byte OP_CODE = (byte) 0xE6;
	public void execute(Simulator sim) {
		MemoryContent mc = sim.getBus().read(sim.getRegister16PC().getValue());
		sim.sleep();
		sim.incPC();
		sim.sleep();
		sim.getRegister16A().setValue( sim.getRegister16A().getValue() & mc.getValue()  );
		
		if(sim.getRegister16A().getValue() == 0){
			sim.getFlagRegister().setZeroFlag(1);
		}else {
			sim.getFlagRegister().setZeroFlag(0);
		}
			
		if(sim.getRegister16A().getValue() < 0){
			sim.getFlagRegister().setSignFlag(1);
		}else{
			sim.getFlagRegister().setSignFlag(0);
		}
		//se o numero de bits for par
		if((Integer.bitCount(sim.getRegister16A().getValue()) % 2) == 0){
			sim.getFlagRegister().setParityFlag(1);
		}else{
			sim.getFlagRegister().setParityFlag(0);
		}
		
		sim.getFlagRegister().setCarryFlag(0);
		sim.getFlagRegister().setAuxiliaryCarry(1);
		sim.sleep();		
	}
	
	public String toString(){
		return Token.ANI.name;
	}

}