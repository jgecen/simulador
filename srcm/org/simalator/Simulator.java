package org.simalator;

import java.util.HashMap;
import java.util.Map;

import org.parser.Token;
import org.simalator.command.CommandInstruction;
import org.simalator.command.TableCommand;
import org.util.Util;

public class Simulator {
	
	private int sleepValue = 50;
	public final void sleep(){
		try {
			Thread.sleep( 30000/sleepValue );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private Register8 register8H = new Register8();

	private Register8 register8L = new Register8();

	private Register8 register8D = new Register8();

	private Register8 register8E = new Register8();

	private Register8 register8B = new Register8();

	private Register8 register8C = new Register8();

	private Register16 register16PC = new Register16();

	private RI register8RI = new RI();

	private Register16 register16SP = new Register16();
	
	private Register16 register16A = new Register16();
	
	private FlagRegister flagRegister = new FlagRegister();

	private Bus bus;

	Map<Integer, Register8> mapRegister8 = new HashMap<Integer, Register8>();
	
	TableCommand tableCommand = new TableCommand();

	Memory memory = new Memory();

	private boolean stop;

	private boolean isTrap;

	private int addresTrap;

	public Simulator() {
		register16PC.setValue(0);
		register16SP.setValue(0xffff);
		
		bus = new Bus(memory);
		
		mapRegister8.put(Util.stringToHexRegister(Token.B.name), register8B);
		mapRegister8.put(Util.stringToHexRegister(Token.C.name), register8C);
		mapRegister8.put(Util.stringToHexRegister(Token.D.name), register8D);
		mapRegister8.put(Util.stringToHexRegister(Token.E.name), register8E);
		mapRegister8.put(Util.stringToHexRegister(Token.H.name), register8H);
		mapRegister8.put(Util.stringToHexRegister(Token.L.name), register8L);
	}
	public Register8 getRegister8(int idRegister){
		return mapRegister8.get(idRegister);
	}
	
	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public Register8 getRegister8B() {
		return register8B;
	}

	public Register8 getRegister8C() {
		return register8C;
	}

	public Register8 getRegister8D() {
		return register8D;
	}

	public Register8 getRegister8E() {
		return register8E;
	}

	public Register8 getRegister8H() {
		return register8H;
	}

	public Register8 getRegister8L() {
		return register8L;
	}

	public Bus getBus() {
		return bus;
	}

	public Register16 getRegister16PC() {
		return register16PC;
	}

	public RI getRegister8RI() {
		return register8RI;
	}

	public Register16 getRegister16SP() {
		return register16SP;
	}
	public Register16 getRegister16A() {
		return register16A;
	}

	public FlagRegister getFlagRegister() {
		return flagRegister;
	}

	public void setSleepValue(int sleepValue) {
		if(sleepValue > 0){
			this.sleepValue = sleepValue;
		}
		
	}
	public void incPC(){
		register16PC.setValue((register16PC.getValue() + 1));
	}
	public void incSP(){
		register16SP.setValue((register16SP.getValue() + 1));
	}
	public void decSP(){
		register16SP.setValue((register16SP.getValue() - 1));
	}
	
	public MemoryContent search(){
		int addresNextInst = register16PC.getValue();
		sleep();
		MemoryContent content = bus.readInstruction(addresNextInst);
		register8RI.setValue(content.getValue());
		sleep();
		incPC();
		sleep();
		return content;
	}
	public void stopSimulation(){
		this.stop = true;
	}
	public void simulate() throws SimulatorExeption{
		this.stop = false;
		while(!stop){
			
			if(isTrap){
				executeTrap();				
			}
			
			MemoryContent mc = search();
			CommandInstruction ci = tableCommand.get(mc.getValue());
			if(ci != null){
				ci.execute(this);	
			}else{
				throw new SimulatorExeption("Instrução não existente.");
			}
			
			
		}
		return;
	}
	
	private void executeTrap() {
		short pcValueSP = (short) getRegister16PC().getValue();
		//tratando sp
		String pcValueSPHex = Util.toHex16(pcValueSP);		
		byte posMemMenos = (byte) Util.stringToHex(pcValueSPHex.substring(2, 4));
		byte posMemMais = (byte) Util.stringToHex(pcValueSPHex.substring(0, 2));
		
		sleep();
		decSP();
		sleep();
		//OW para mostrar a posição correta na tela
		getRegister16SP().setValue(getRegister16SP().getValue());
		
		getBus().write(getRegister16SP().getValue(), new MemoryContent(posMemMais));
		sleep();
		decSP();
		sleep();
		getBus().write(getRegister16SP().getValue(), new MemoryContent(posMemMenos));
		//OW para mostrar a posição correta na tela
		getRegister16SP().setValue(getRegister16SP().getValue());
		
		this.getRegister16PC().setValue(this.addresTrap);
		this.isTrap = false;
	}
	
	public int getAddresTrap() {
		return addresTrap;
	}
	public void setAddresTrap(int addresTrap) {
		this.addresTrap = addresTrap;
	}
	public boolean isTrap() {
		return isTrap;
	}
	public void setTrap(boolean isTrap) {
		this.isTrap = isTrap;
	}
}
