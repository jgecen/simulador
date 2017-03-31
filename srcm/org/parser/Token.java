package org.parser;

public enum Token {
	
	/*
	 * Instruções
	 */
	
	CPI("CPI"),   CPE("CPE"),   CP("CP"),     CNZ("CNZ"), CNC("CNC"),   CMP("CMP"),
	CMC("CMC"),   CMA("CMA"),   CM("CM"),     CC("CC"),   CALL("CALL"), ANI("ANI"),
	ANA("ANA"),   ADI("ADI"),   ADD("ADD"),   ADC("ADC"), ACI("ACI"),   INR("INR"),
	IN("IN"),     HLT("HLT"),   EI("EI"),     DI("DI"),   DCX("DCX"),   DCR("DCR"),
	DAD("DAD"),	  DAA("DAA"),   CZ("CZ"),     CPO("CPO"), LDA("LDA"),   JZ("JZ"),
	JPO("JPO"),	  JPE("JPE"),   JP("JP"),     JNZ("JNZ"), JNC("JNC"),   JM("JM"),
	JC("JC"),	  JMP("JMP"),   INX("INX"),   POP("POP"), PCHL("PCHL"), OUT("OUT"),
	ORI("ORI"),	  ORA("ORA"),   NOP("NOP"),   MVI("MVI"), MOV("MOV"),   LXI("LXI"),
	LHLD("LHLD"), LDAX("LDAX"), PUSH("PUSH"), RAL("RAL"), RAR("RAR"),   RET("RET"),
	RC("RC"),     RIM("RIM"),   RM("RM"),     RNC("RNC"), RNZ("RNZ"),   RP("RP"),	
	RPE("RPE"),   RPO("RPO"),   RZ("RZ"),     RLC("RLC"), RRC("RRC"),   RST("RST"),
	SBB("SBB"),   SBI("SBI"),   SHLD("SHLD"), SIM("SIM"), SPHL("SPHL"), STA("STA"),
	STAX("STAX"), STC("STC"),   SUB("SUB"),   SUI("SUI"), XCHG("XCHG"), XRA("XRA"),	
	XRI("XRI"),   XTHL("XTHL"),	ORG("ORG"), 
	/*
	 * Registradores
	 */
	E("E"), ACUMULATOR("A"), FLAGFF("F"), B("B"), C("C"), D("D"),
	H("H"), L("L"), SP("SP"), PSW("PSW"), HL("M"),
	
	
	COMMA, DECLARE_LABEL("DECLARE_LABEL"),LABEL("LABEL") ,BIN, HEXA_0X, DECIMAL,
	
	EOF;
	public String name;

	Token(String name){
		this.name = name;
	}
	Token(){
	}
}
