package org.simalator;

import java.util.Observable;

public class Bus extends Observable{
	private boolean read;
	private boolean write;
	private Memory memory;
	private MemoryContent memoryContent;
	private boolean instruction;
	
	public Bus(Memory memory){
		this.memory = memory;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
		this.write = !read;
		this.setChanged();
		this.notifyObservers();
	}
	public boolean isWrite() {
		return write;
	}
	public void setWrite(boolean write) {
		this.write = write;
		this.read = !write;
		this.setChanged();
		this.notifyObservers();
		
	}
	public MemoryContent readInstruction(int addresMem) {		
		this.setInstruction(true);
		this.setRead(true);
		return memory.load(addresMem);
	}
	public void write(int posMemory, MemoryContent mc ){
		setWrite(true);
		this.memory.setMemoryContent(posMemory, mc);
	}
	public MemoryContent read(int addresMem) {
		this.memoryContent = memory.load(addresMem);
		this.setInstruction(false);
		this.setRead(true);
		return this.memoryContent;
	}
	public MemoryContent getMemoryContent() {
		return memoryContent;
	}
	public void setMemoryContent(MemoryContent memoryContent) {
		this.memoryContent = memoryContent;
	}
	public boolean isInstruction() {
		return instruction;
	}
	public void setInstruction(boolean instruction) {
		this.instruction = instruction;
	}
}