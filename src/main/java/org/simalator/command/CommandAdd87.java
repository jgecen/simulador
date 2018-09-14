package org.simalator.command;

import org.parser.Token;
import org.simalator.MemoryContent;
import org.simalator.Register8;
import org.simalator.Simulator;

public class CommandAdd87 implements CommandInstruction{
	public static final byte OP_CODE = (byte) 0X87;
	
	public void execute(Simulator sim) {
		sim.sleep();
		int address = sim.getRegister16PC().getValue();
		MemoryContent registerId = sim.getBus().read(address);
		sim.sleep();
		sim.incPC();
		sim.sleep();
		Register8 register8 = sim.getRegister8(registerId.getValue());
		
		short soma = (short)(sim.getRegister16A().getValue() + register8.getValue());
		
		
		if(soma > 0xFF){
			sim.getFlagRegister().setCarryFlag(1);
		}else{
			sim.getFlagRegister().setCarryFlag(0);
		}
		
		
		int ia = sim.getRegister16A().getValue() & 0x0000000f;
		int im = register8.getValue() & 0x0f;
		
		if(ia + im > 15){
			sim.getFlagRegister().setAuxiliaryCarry(1);
		}else{
			sim.getFlagRegister().setAuxiliaryCarry(0);
		}
		sim.getRegister16A().setValue((byte)soma);
		sim.sleep();
	}

	@Override
	public String toString(){
		return Token.ADD.name;
	}

}
