package org.parser;

import org.util.Util;

public class DeclareLabel{
	private String key;
	private short posMemory;
	public DeclareLabel(String key, short posMemory) {
		super();
		this.key = key;
		this.posMemory = posMemory;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public short getPosMemory() {
		return posMemory;
	}
	public void setPosMemory(short posMemory) {
		this.posMemory = posMemory;
	}
	public boolean equals(Object other){
		if(other != null 
				&& other instanceof DeclareLabel 
				&& ((DeclareLabel)other).key.equals(this.key)){
			return true;
		}
		return false;
		
	}
	
	public String toString(){
		return this.key + "-" + Util.toHex16(posMemory);
	}
}