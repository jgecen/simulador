package org.view;

import java.awt.Color;
import java.awt.SystemColor;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import org.simalator.Register8;

public class ObserverRegister8 implements Observer{
	
	private JTextField jTextField;
	
	public ObserverRegister8(JTextField textField) {
		super();
		jTextField = textField;
	}

	public void update(Observable observable, Object object) {
		Register8 register8 = (Register8) observable;
		jTextField.setBorder(BorderFactory.createLineBorder(new Color(204, 0, 0), 3));
		jTextField.setText( register8.toString());		
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jTextField.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaptionBorder, 2));		
	}

}
