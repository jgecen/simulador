package org.view;

import java.awt.Color;
import java.awt.SystemColor;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.simalator.Bus;

public class ObserverBus implements Observer{
	
	private JLabel jLabelR;
	private JLabel jLabelW;
	private JTextField jTextField;
	
	public ObserverBus(JLabel labelR, JLabel labelW, JTextField textField) {
		super();
		jLabelR = labelR;
		jLabelW = labelW;
		jTextField = textField;
	}
	public void update(Observable observable, Object object) {
		Bus bus = (Bus)observable;
		if(bus.isRead()){
			jLabelR.setForeground(Color.red);
			jLabelW.setForeground(Color.black);			
			sleepAndBlackOp();
			if(!bus.isInstruction()){
				jTextField.setBorder(BorderFactory.createLineBorder(new Color(204, 0, 0), 3));
				jTextField.setText(jTextField.getText() + " " + bus.getMemoryContent().toString());
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}		
				jTextField.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaptionBorder, 2));
				
			}
			
		}else{
			jLabelR.setForeground(Color.black);
			jLabelW.setForeground(Color.red);
			sleepAndBlackOp();
		}
	}
	private void sleepAndBlackOp() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jLabelR.setForeground(Color.black);
		jLabelW.setForeground(Color.black);
	}

}
