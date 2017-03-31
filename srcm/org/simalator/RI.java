package org.simalator;

import org.simalator.command.TableCommand;

public class RI extends Register8{
	TableCommand command = new TableCommand();
	
	public String toString(){
		return command.get(getValue()).toString();
	}
	
}
