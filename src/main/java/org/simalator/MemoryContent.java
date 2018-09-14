package org.simalator;

import org.util.Util;

public class MemoryContent {
	
	//WA para substituir posteriormente o valor do label
	//na lista de Memory content
	private String label;
	
	private byte value;

	public MemoryContent(byte value){
		this.value = value;
	}
	public byte getValue() {
		return value;
	}

	public void setValue(byte value) {
		this.value = value;
	}
	
	@Override
	public String toString(){
		return Util.toHex8(value);
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}
