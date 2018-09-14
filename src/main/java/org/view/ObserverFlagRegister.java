package org.view;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.simalator.FlagRegister;

public class ObserverFlagRegister implements Observer {

	private JTextField jTextFieldSF;

	private JTextField jTextFieldZF;

	private JTextField jTextFieldAC;

	private JTextField jTextFieldPF;

	private JTextField jTextFieldCF;

	private JPanel jPanel;

	public ObserverFlagRegister(JPanel panel, JTextField textFieldSF, JTextField textFieldZF,
			JTextField textFieldAC, JTextField textFieldPF,
			JTextField textFieldCF) {
		super();
		jTextFieldSF = textFieldSF;
		jTextFieldZF = textFieldZF;
		jTextFieldAC = textFieldAC;
		jTextFieldPF = textFieldPF;
		jTextFieldCF = textFieldCF;
		this.jPanel = panel;
	}

	public void update(Observable observable, Object object) {
		FlagRegister flagRegister = (FlagRegister) observable;
		jPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.red, 2), "Flags", 
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
				new Font("Dialog", Font.ITALIC, 12), new Color(51, 51, 51)));
		
		setJText(this.jTextFieldAC, flagRegister.getAuxiliaryCarry());
		setJText(this.jTextFieldCF, flagRegister.getCarryFlag());
		setJText(this.jTextFieldPF, flagRegister.getParityFlag());
		setJText(this.jTextFieldZF, flagRegister.getZeroFlag());
		setJText(this.jTextFieldSF, flagRegister.getSignFlag());	
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jPanel.setBorder(BorderFactory.createTitledBorder(null,
				"Flags", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Dialog",
						Font.ITALIC, 12), new Color(51, 51, 51)));
	}
	
	private void setJText(JTextField refJtext, int value){
			refJtext.setText( Integer.toString(value));		
	}

}
