package org.simalator;

import java.util.LinkedList;
import java.util.Observable;

public class Memory extends Observable{
	private static final int MAX_MEMORY_POS = 0xffff;
	private LinkedList<MemoryContent> address = new LinkedList<MemoryContent>();
	private MemoryContent memoryContentCurrent;
	private int posMemoryReadOrWrite;
	private int updateUntil;
	
	public Memory(){
		init();
	}

	public void init() {
		for(int i =0; i <= MAX_MEMORY_POS; i++){
			address.add( new MemoryContent((byte)0));
		}
	}
	
	public MemoryContent load(int pos){
		this.setPosMemoryReadOrWrite(pos);
		if(pos >= 0 && pos <= MAX_MEMORY_POS){
			this.setChanged();
			this.notifyObservers( Boolean.FALSE );
			return address.get(pos);
		}else{
			throw new RuntimeException("Posição não existe");
		}
		
	}
	public void setMemoryContent(int posMemory, MemoryContent mc){
		this.setPosMemoryReadOrWrite(posMemory);
		address.set(posMemory, mc);
		this.memoryContentCurrent = mc;
		this.setChanged();
		this.notifyObservers( Boolean.TRUE );
		
	}
	public void setAddress(LinkedList<MemoryContent> newAddress) {
		if(newAddress.size() <= MAX_MEMORY_POS){
			for(int i = 0; i < newAddress.size(); i++){
				this.address.set(i, newAddress.get(i));
			}
			this.updateUntil = newAddress.size();
			this.setChanged();
			this.notifyObservers();
		}else{
			throw new RuntimeException("Tamanho maior que a capacidade.");
		}
	}

	public LinkedList<MemoryContent> getAddress() {
		return address;
	}

	public MemoryContent getMemoryContentCurrent() {
		return memoryContentCurrent;
	}

	public int getPosMemoryReadOrWrite() {
		return posMemoryReadOrWrite;
	}

	public void setPosMemoryReadOrWrite(int posMemoryReadOrWrite) {
		this.posMemoryReadOrWrite = posMemoryReadOrWrite;
	}

	public int getUpdateUntil() {
		return updateUntil;
	}
}
