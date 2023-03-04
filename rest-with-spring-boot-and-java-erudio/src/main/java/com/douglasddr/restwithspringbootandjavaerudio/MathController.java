package com.douglasddr.restwithspringbootandjavaerudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.douglasddr.restwithspringbootandjavaerudio.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {
	private final AtomicLong counter = new AtomicLong();
	
	@GetMapping(value = "/sum/{numberOne}/{numberTwo}")
	public Double sum(
			@PathVariable(value = "numberOne" ) String numberOne,
			@PathVariable(value = "numberTwo" ) String numberTwo
			) throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}

	private Double convertToDouble(String strNumber) {
		if (strNumber == null) {
			return 0D;
		}
		// BR 10,25 US 10.25
		String number = strNumber.replaceFirst(",", ".");
		if (isNumeric(number)) {
			return Double.parseDouble(number);
		}
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		if (strNumber == null) {
			return false;		
		}		
		String number = strNumber.replaceFirst(",", ".");		
		return number.matches("[+-]?[0-9]*\\.?[0-9]+");
	}
}
