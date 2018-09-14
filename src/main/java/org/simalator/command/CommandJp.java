package org.simalator.command;

import org.parser.Token;
import org.simalator.MemoryContent;
import org.simalator.Simulator;
import org.util.Util;

public class CommandJp implements CommandInstruction{
	public static final byte OP_CODE = (byte) 0xF2;
	public void execute(Simulator sim) {
		MemoryContent mcMenos = sim.getBus().read(sim.getRegister16PC().getValue());
		sim.sleep();
		sim.incPC();
		sim.sleep();
		MemoryContent mcMais = sim.getBus().read(sim.getRegister16PC().getValue());
		sim.sleep();
		sim.incPC();
		sim.sleep();
		
		String strHexMenos = Util.toHex8(mcMenos.getValue());
		String strHexMais = Util.toHex8(mcMais.getValue());
		short pcValueJp = (short) Util.stringToHex(strHexMais + strHexMenos);
		sim.sleep();
		sim.getRegister16PC().setValue(pcValueJp);
		sim.sleep();
	}
	
	public String toString(){
		return Token.JP.name;
	}

}