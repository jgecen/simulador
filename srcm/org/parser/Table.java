package org.parser;

import java.util.HashMap;
import java.util.Map;

public class Table {
	private Map<String, Token> mapInstruction = new HashMap<String, Token>();
	private Map<String, Token> mapRegister = new HashMap<String, Token>();
	private Map<String, Token> mapAll = new HashMap<String, Token>();
	
	public Table(){
		loadInstruction();
		loadRegister();
		mapAll.putAll(mapInstruction);
		mapAll.putAll(mapRegister);
	}
	
	private void loadRegister() {
		mapRegister.put(Token.E.name, Token.E);
		//mapRegister.put(Token.ACUMULATOR.name, Token.ACUMULATOR);
		mapRegister.put(Token.FLAGFF.name, Token.FLAGFF);
		mapRegister.put(Token.B.name, Token.B);
		mapRegister.put(Token.C.name, Token.C);
		mapRegister.put(Token.D.name, Token.D);
		mapRegister.put(Token.H.name, Token.H);
		mapRegister.put(Token.L.name, Token.L);
		mapRegister.put(Token.HL.name, Token.HL);
		mapRegister.put(Token.SP.name, Token.SP);
		mapRegister.put(Token.PSW.name, Token.PSW);
	}

	private void loadInstruction() {
		mapInstruction.put(Token.CPI.name, Token.CPI);
		mapInstruction.put(Token.CPE.name, Token.CPE);
		mapInstruction.put(Token.CP.name, Token.CP);
		mapInstruction.put(Token.CNZ.name, Token.CNZ);
		mapInstruction.put(Token.CNC.name, Token.CNC);
		mapInstruction.put(Token.CMP.name, Token.CMP);
		mapInstruction.put(Token.CMC.name, Token.CMC);
		mapInstruction.put(Token.CMA.name, Token.CMA);
		mapInstruction.put(Token.CM.name, Token.CM);
		mapInstruction.put(Token.CC.name, Token.CC);
		mapInstruction.put(Token.CALL.name, Token.CALL);
		mapInstruction.put(Token.ANI.name, Token.ANI);			
		mapInstruction.put(Token.ANA.name, Token.ANA);
		mapInstruction.put(Token.ADI.name, Token.ADI);
		mapInstruction.put(Token.ADD.name, Token.ADD);
		mapInstruction.put(Token.ADC.name, Token.ADC);
		mapInstruction.put(Token.ACI.name, Token.ACI);
		mapInstruction.put(Token.INR.name, Token.INR);
		mapInstruction.put(Token.IN.name, Token.IN);
		mapInstruction.put(Token.HLT.name, Token.HLT);
		mapInstruction.put(Token.EI.name, Token.EI);
		mapInstruction.put(Token.DI.name, Token.DI);
		mapInstruction.put(Token.DCX.name, Token.DCX);
		mapInstruction.put(Token.DCR.name, Token.DCR);
		mapInstruction.put(Token.DAD.name, Token.DAD);
		mapInstruction.put(Token.DAA.name, Token.DAA);
		mapInstruction.put(Token.CZ.name, Token.CZ);
		mapInstruction.put(Token.CPO.name, Token.CPO);
		mapInstruction.put(Token.LDA.name, Token.LDA);
		mapInstruction.put(Token.JZ.name, Token.JZ);
		mapInstruction.put(Token.JPO.name, Token.JPO);
		mapInstruction.put(Token.JPE.name, Token.JPE);
		mapInstruction.put(Token.JP.name, Token.JP);
		mapInstruction.put(Token.JNZ.name, Token.JNZ);
		mapInstruction.put(Token.JNC.name, Token.JNC);
		mapInstruction.put(Token.JM.name, Token.JM);						
		mapInstruction.put(Token.JC.name, Token.JC);
		mapInstruction.put(Token.JMP.name, Token.JMP);
		mapInstruction.put(Token.INX.name, Token.INX);
		mapInstruction.put(Token.POP.name, Token.POP);
		mapInstruction.put(Token.PCHL.name, Token.PCHL);
		mapInstruction.put(Token.OUT.name, Token.OUT);
		mapInstruction.put(Token.ORI.name, Token.ORI);
		mapInstruction.put(Token.ORA.name, Token.ORA);
		mapInstruction.put(Token.NOP.name, Token.NOP);
		mapInstruction.put(Token.MVI.name, Token.MVI);
		mapInstruction.put(Token.MOV.name, Token.MOV);
		mapInstruction.put(Token.LXI.name, Token.LXI);
		mapInstruction.put(Token.LHLD.name, Token.LHLD);
		mapInstruction.put(Token.LDAX.name, Token.LDAX);
		mapInstruction.put(Token.PUSH.name, Token.PUSH);
		mapInstruction.put(Token.RAL.name, Token.RAL);
		mapInstruction.put(Token.RAR.name, Token.RAR);
		mapInstruction.put(Token.RET.name, Token.RET);
		mapInstruction.put(Token.RC.name, Token.RC);
		mapInstruction.put(Token.RIM.name, Token.RIM);
		mapInstruction.put(Token.RNC.name, Token.RNC);
		mapInstruction.put(Token.RNZ.name, Token.RNZ);
		mapInstruction.put(Token.RP.name, Token.RP);
		mapInstruction.put(Token.RPE.name, Token.RPE);
		mapInstruction.put(Token.RZ.name, Token.RZ);
		mapInstruction.put(Token.RLC.name, Token.RLC);
		mapInstruction.put(Token.RRC.name, Token.RRC);
		mapInstruction.put(Token.RST.name, Token.RST);
		mapInstruction.put(Token.SBB.name, Token.SBB);
		mapInstruction.put(Token.SBI.name, Token.SBI);
		mapInstruction.put(Token.SHLD.name, Token.SHLD);
		mapInstruction.put(Token.SIM.name, Token.SIM);
		mapInstruction.put(Token.SPHL.name, Token.SPHL);
		mapInstruction.put(Token.STA.name, Token.STA);		
		mapInstruction.put(Token.STAX.name, Token.STAX);
		mapInstruction.put(Token.STC.name, Token.STC);
		mapInstruction.put(Token.SUB.name, Token.SUB);
		mapInstruction.put(Token.SUI.name, Token.SUI);
		mapInstruction.put(Token.XCHG.name, Token.XCHG);
		mapInstruction.put(Token.XRA.name, Token.XRA);
		mapInstruction.put(Token.XRI.name, Token.XRI);
		mapInstruction.put(Token.XTHL.name, Token.XTHL);
		mapInstruction.put(Token.RPO.name, Token.RPO);
		mapInstruction.put(Token.RM.name, Token.RM);
		mapInstruction.put(Token.ORG.name, Token.ORG);
	}
	
	public boolean isInstruction(String name){
		Token t = mapInstruction.get(name);
		if(t != null)
			return true;
		return false;
	}
	public boolean isRegister(String name){
		Token t = mapRegister.get(name);	
		if(t != null)
			return true;
		return false;
	}
	public boolean isToken(String name){
		Token t = mapAll.get(name);	
		if(t != null)
			return true;
		return false;
		
	}
	public Token getToken(String name){
		return mapAll.get(name);
	}
	
}
