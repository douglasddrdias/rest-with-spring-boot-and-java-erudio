package com.douglasddr.restwithspringbootandjavaerudio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.douglasddr.restwithspringbootandjavaerudio.services.CalculadoraServices;

@RestController
public class MathController {
		
	@Autowired
	CalculadoraServices calculadoraService;
	
	@GetMapping(value = "/sum/{numberOne}/{numberTwo}")
	public Double sum(
			@PathVariable(value = "numberOne" ) String numberOne,
			@PathVariable(value = "numberTwo" ) String numberTwo
			) throws Exception {
		return calculadoraService.sum(numberOne, numberTwo);
	}
	
	@GetMapping(value = "/subtract/{numberOne}/{numberTwo}")
	public Double subtract(
			@PathVariable(value = "numberOne" ) String numberOne,
			@PathVariable(value = "numberTwo" ) String numberTwo			
			) {
		return calculadoraService.subtract(numberOne, numberTwo);
	}
	
	@GetMapping(value = "/multiplication/{numberOne}/{numberTwo}")
	public Double multiplication(
			@PathVariable(value = "numberOne" ) String numberOne,
			@PathVariable(value = "numberTwo" ) String numberTwo			
			) {
		return calculadoraService.multiplication(numberOne, numberTwo);
	}
	
	@GetMapping(value = "/division/{numberOne}/{numberTwo}")
	public Double division(
			@PathVariable(value = "numberOne" ) String numberOne,
			@PathVariable(value = "numberTwo" ) String numberTwo			
			) {
		return calculadoraService.division(numberOne, numberTwo);
	}
	
	@GetMapping(value = "/mean/{numberOne}/{numberTwo}")
	public Double mean(
			@PathVariable(value = "numberOne" ) String numberOne,
			@PathVariable(value = "numberTwo" ) String numberTwo			
			) {
		return calculadoraService.mean(numberOne, numberTwo);
	}
	
	@GetMapping(value = "/square/{number}")
	public Double square(
			@PathVariable(value = "number" ) String number		
			) {
		return calculadoraService.square(number);
	}
}
