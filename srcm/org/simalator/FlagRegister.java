package org.simalator;

import java.util.Observable;

public class FlagRegister extends Observable{
	public static final int ACTIVATED_FLAG = 1;
	public static final int DISACTIVATED_FLAG = 0;
	private int signFlag = ACTIVATED_FLAG;
	private int zeroFlag = ACTIVATED_FLAG;
	private int auxiliaryCarry = ACTIVATED_FLAG;
	private int parityFlag = ACTIVATED_FLAG;
	private int carryFlag = ACTIVATED_FLAG;
	
	private void setChanAndNotify() {
		this.setChanged();
		this.notifyObservers();
	}
	
	public int getAuxiliaryCarry() {
		return auxiliaryCarry;
	}
	public void setAuxiliaryCarry(int auxiliaryCarry) {
		this.auxiliaryCarry = auxiliaryCarry;
		setChanAndNotify();
	}
	public int getCarryFlag() {
		return carryFlag;
	}
	public void setCarryFlag(int carryFlag) {
		this.carryFlag = carryFlag;
		setChanAndNotify();
	}
	public int getParityFlag() {
		return parityFlag;
	}
	public void setParityFlag(int parityFlag) {
		this.parityFlag = parityFlag;
		setChanAndNotify();
	}
	public int getSignFlag() {
		return signFlag;
	}
	public void setSignFlag(int signFlag) {
		this.signFlag = signFlag;
		setChanAndNotify();
	}
	public int getZeroFlag() {
		return zeroFlag;
	}
	public void setZeroFlag(int zeroFlag) {
		this.zeroFlag = zeroFlag;
		setChanAndNotify();
	}
}
