package org.simalator.command;

import org.simalator.Simulator;

public class CommandNop implements CommandInstruction {
	public static final byte OP_CODE = (byte) 0x00;
	public void execute(Simulator sim) {
		sim.sleep();
	}
	public String toString(){
		return "NOP";
	}
}
