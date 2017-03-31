package org.simalator;

import java.util.Observable;

import org.util.Util;

public class Register16 extends Observable {
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		this.setChanged();
		this.notifyObservers();
	}
	
	@Override
	public String toString(){
		return Util.toHex16(this.getValue());
	}
}
