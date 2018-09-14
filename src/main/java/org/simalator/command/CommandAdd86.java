package org.simalator.command;

import org.parser.Token;
import org.simalator.MemoryContent;
import org.simalator.Register16;
import org.simalator.Simulator;
import org.util.Util;

public class CommandAdd86 implements CommandInstruction{
	public static final byte OP_CODE = (byte) 0x86;
	public void execute(Simulator sim) {
		sim.sleep();
		String strHL = sim.getRegister8H().toString() + sim.getRegister8L().toString();		
		int posMemory = Util.stringToHex(strHL);		
		MemoryContent mc = sim.getBus().read(posMemory);		
		Register16 register16A = sim.getRegister16A();		
		short soma = (short)(register16A.getValue() + mc.getValue());
		
		if(soma > 0xFF){
			sim.getFlagRegister().setCarryFlag(1);
			
		}else{
			sim.getFlagRegister().setCarryFlag(0);
		}
		
		
		int ia = sim.getRegister16A().getValue() & 0x0000000f;
		int im = mc.getValue() & 0x0f;
		if((ia + im) > 15){
			sim.getFlagRegister().setAuxiliaryCarry(1);
		}else{
			sim.getFlagRegister().setAuxiliaryCarry(0);
		}
		register16A.setValue((byte) soma );
		sim.sleep();
	}
	
	public String toString(){
		return Token.ADD.name;
	}

}