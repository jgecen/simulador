package org.util;

public class Util {
	
	private Util(){		
	}
	public static final String toHex8(int value){
		String strReturn = toHex(value);		
		if(strReturn.length() == 1){
			return "0" + strReturn.toUpperCase();
		}else{
			return strReturn.toUpperCase();
		}
	}
	
	public static final String toHex16(int value){
		String strReturn = toHex(value);
		StringBuilder sb = new StringBuilder();		
		for(int i = 0; i < 4 - strReturn.length(); i++){
			sb.append("0");
		}
		sb.append(strReturn);
		return sb.toString().toUpperCase();
	}
	
	public static int stringToHex(String value) {
		return Integer.parseInt(value , 16);
	}
	
	public static int stringToHexRegister(String value){
		if("H".equals(value) || "L".equals(value)){
			return Integer.parseInt(toHex( value.toCharArray()[0] ), 16);
		}
		return Integer.parseInt(value , 16);
	}
	private static String toHex(int value) {
		String strReturn = null;
		if(value >= 0){
			strReturn = Integer.toHexString(value);
		}
		//complemento de 2
		else{
			strReturn = Integer.toHexString((((-value) ^ 0xff) + 1));	
		}
		return strReturn;
	}
}
