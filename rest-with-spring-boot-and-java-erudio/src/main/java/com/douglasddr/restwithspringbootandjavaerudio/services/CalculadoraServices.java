package com.douglasddr.restwithspringbootandjavaerudio.services;

import static com.douglasddr.restwithspringbootandjavaerudio.utils.DoubleUtils.ZERO;
import static com.douglasddr.restwithspringbootandjavaerudio.utils.DoubleUtils.convertToDouble;
import static com.douglasddr.restwithspringbootandjavaerudio.utils.DoubleUtils.isNumeric;

import org.springframework.stereotype.Service;

import com.douglasddr.restwithspringbootandjavaerudio.exceptions.UnsupportedMathOperationException;

@Service
public class CalculadoraServices {

	private static final String PLEASE_SET_A_NUMERIC_VALUE = "Please set a numeric value!";
	
	public Double sum(String numberOne, String numberTwo
			) throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(PLEASE_SET_A_NUMERIC_VALUE);
		}
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}
	
	public Double subtract(String numberOne, String numberTwo			
			) {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(PLEASE_SET_A_NUMERIC_VALUE);
		}
		return convertToDouble(numberOne) - convertToDouble(numberTwo);
	}
	
	public Double multiplication(String numberOne, String numberTwo			
			) {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(PLEASE_SET_A_NUMERIC_VALUE);
		}
		return convertToDouble(numberOne) * convertToDouble(numberTwo);
	}
	
	public Double division(String numberOne, String numberTwo			
			) {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(PLEASE_SET_A_NUMERIC_VALUE);
		}
		Double divisor = convertToDouble(numberTwo);
		if (divisor.equals(ZERO)) {
			throw new UnsupportedMathOperationException("Please set a numeric value not equal zero!");
		}
		return convertToDouble(numberOne) / divisor;
	}
	
	public Double mean(String numberOne, String numberTwo			
			) {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(PLEASE_SET_A_NUMERIC_VALUE);
		}
		return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
	}
	
	public Double square(String number		
			) {
		if (!isNumeric(number)) {
			throw new UnsupportedMathOperationException(PLEASE_SET_A_NUMERIC_VALUE);
		}
		return Math.sqrt(convertToDouble(number));
	}	
}
