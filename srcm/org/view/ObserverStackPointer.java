package org.view;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextArea;

import org.simalator.Memory;
import org.simalator.MemoryContent;
import org.simalator.Register16;
import org.simalator.Simulator;

public class ObserverStackPointer implements Observer{
	
	private JTextArea textArea;
	private Simulator simulator;
	public ObserverStackPointer(JTextArea ta, Simulator sim) {
		textArea = ta;
		simulator = sim;
	}

	public void update(Observable observable, Object obj) {
		Register16 sp = (Register16) observable;
		Memory memory = simulator.getMemory();
		LinkedList<MemoryContent> list = memory.getAddress();
		String str = "";
		for (int i = sp.getValue(); i < list.size(); i++) {
			str += list.get(i).toString() + "\n";
			textArea.setText(str);
		}
		
	}

}
