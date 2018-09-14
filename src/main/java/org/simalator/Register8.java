package org.simalator;

import java.util.Observable;

import org.util.Util;

public class Register8 extends Observable{
	private byte value;

	public byte getValue() {
		return value;
	}

	public void setValue(byte value) {
		this.value = value;
		this.setChanged();
		this.notifyObservers();
	}
	
	@Override
	public String toString(){
		return Util.toHex8(getValue());
	}
}
