package com.douglasddr.restwithspringbootandjavaerudio.utils;

import java.util.regex.Pattern;


public final class DoubleUtils {

	private static final Pattern ONLY_NUMBERS = Pattern.compile("[+-]?[0-9]*\\.?[0-9]+");
	public static final Double ZERO = 0D;
	private DoubleUtils() {
	}
	
	public static boolean isNumeric(String strNumber) {
		if (strNumber == null) {
			return false;		
		}		
		String number = strNumber.replaceAll(",", ".");		
		return ONLY_NUMBERS.matcher(number).matches();
	}
	
	public static Double convertToDouble(String strNumber) {
		if (strNumber == null) {
			return ZERO;
		}
		String number = strNumber.replaceAll(",", ".");
		if (isNumeric(number)) {
			return Double.parseDouble(number);
		}
		return ZERO;
	}	
}
