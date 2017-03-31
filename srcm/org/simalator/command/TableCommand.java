package org.simalator.command;

import java.util.HashMap;

public class TableCommand {
	HashMap<Byte, CommandInstruction> hashMap = new HashMap<Byte, CommandInstruction>();

	public TableCommand() {
		super();
		this.hashMap.put(CommandNop.OP_CODE, new CommandNop());
		this.hashMap.put(CommandAdd86.OP_CODE, new CommandAdd86());
		this.hashMap.put(CommandAdd87.OP_CODE, new CommandAdd87());
		this.hashMap.put(CommandMov77.OP_CODE, new CommandMov77());
		this.hashMap.put(CommandMov7E.OP_CODE, new CommandMov7E());
		this.hashMap.put(CommandMov7F.OP_CODE, new CommandMov7F());
		this.hashMap.put(CommandMvi36.OP_CODE, new CommandMvi36());
		this.hashMap.put(CommandMvi3E.OP_CODE, new CommandMvi3E());
		this.hashMap.put(CommandCall.OP_CODE, new CommandCall());
		this.hashMap.put(CommandPushE5.OP_CODE, new CommandPushE5());
		this.hashMap.put(CommandRet.OP_CODE, new CommandRet());
		this.hashMap.put(CommandJp.OP_CODE, new CommandJp());
		this.hashMap.put(CommandCpi.OP_CODE, new CommandCpi());
		this.hashMap.put(CommandAni.OP_CODE, new CommandAni());
		this.hashMap.put(CommandJz.OP_CODE, new CommandJz());
		this.hashMap.put(CommandJnz.OP_CODE, new CommandJnz());
		this.hashMap.put(CommandPopE1.OP_CODE, new CommandPopE1());
		
	}
	public CommandInstruction get(byte opCode){
		
		return this.hashMap.get(opCode);
	}
	
}
