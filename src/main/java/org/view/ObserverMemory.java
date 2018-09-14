package org.view;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.simalator.Memory;
import org.simalator.MemoryContent;

public class ObserverMemory implements Observer{
	
	private JTable table;
	private Memory memory;
	
	public ObserverMemory(JTable table){
		this.table = table;
		this.table.setModel(new DefaultTableModel( new Object[16][16], new Object[16] ));
		this.table.setTableHeader(null);
		removeRows();
		showMemoryInit();			

	}
	public void update(Observable observable, Object object) {
		this.memory = (Memory)observable;
		Boolean isWrite = (Boolean) object;
		
		if(isWrite != null){
			if(isWrite.booleanValue()){
				DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
				//resto é a coluna;
				//linha é o quociente
				int line = this.memory.getPosMemoryReadOrWrite() / 16;
				int col = this.memory.getPosMemoryReadOrWrite() % 16;
				dtm.setValueAt(this.memory.getMemoryContentCurrent().toString(), line, col);
			}			
		}else{
			//removeRows();
			showMemory();						
		}
		/*
		*/		
	}
	private void showMemory() {
		DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
		int line = 0;
		int col = 0;
		LinkedList<MemoryContent> address = this.memory.getAddress();
		for(int i =0; i < this.memory.getUpdateUntil(); i++ ){
			line = i / 16;
			col = i % 16;
			dtm.setValueAt(address.get(i).toString(), line, col);
			
		}
	}
	private void removeRows(){
		DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
		while(dtm.getRowCount() > 0){
			dtm.removeRow(0);
		}
		
	}
	private void showMemoryInit() {
		DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
		String ini = "00";
		String [] strMemory = new String[]{ini, ini, ini, ini, 
										   ini, ini, ini, ini, 
										   ini, ini, ini, ini, 
										   ini, ini, ini, ini};
		for(int i = 0; i < 0x1000; i++){
			dtm.addRow(strMemory);
		}
	}	
}
