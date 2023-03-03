package com.douglasddr.restwithspringbootandjavaerudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
	private final AtomicLong counter = new AtomicLong();
	
	@GetMapping(value = "/sum/{numberOne}/{numberTwo}")
	public Double sum(
			@PathVariable(value = "numberOne" ) String numberOne,
			@PathVariable(value = "numberTwo" ) String numberTwo
			) {
		return 1D;
	}
}
