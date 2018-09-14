package org.simalator.command;

import org.parser.Token;
import org.simalator.MemoryContent;
import org.simalator.Simulator;

public class CommandPopE1 implements CommandInstruction{
	public static final byte OP_CODE = (byte) 0xE1;
	public void execute(Simulator sim) {
		MemoryContent mcMenos = sim.getBus().read(sim.getRegister16SP().getValue());
		sim.sleep();
		sim.incSP();
		sim.sleep();
		//OW para mostrar a posição correta na tela
		sim.getRegister16SP().setValue(sim.getRegister16SP().getValue());
		sim.getRegister8L().setValue(mcMenos.getValue());
		
		MemoryContent mcMais = sim.getBus().read(sim.getRegister16SP().getValue());
		sim.sleep();
		sim.incSP();
		sim.sleep();	
		//OW para mostrar a posição correta na tela
		sim.getRegister16SP().setValue(sim.getRegister16SP().getValue());
		sim.getRegister8H().setValue(mcMais.getValue());
				
	}
	
	public String toString(){
		return Token.POP.name;
	}

}