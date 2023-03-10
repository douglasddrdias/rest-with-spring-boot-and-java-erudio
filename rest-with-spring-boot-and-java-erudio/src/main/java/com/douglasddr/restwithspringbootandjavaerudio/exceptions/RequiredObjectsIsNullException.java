package com.douglasddr.restwithspringbootandjavaerudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RequiredObjectsIsNullException extends RuntimeException {

	private static final long serialVersionUID = -8077536267888928537L;

	public RequiredObjectsIsNullException(String message) {
		super(message);
	}
	
	public RequiredObjectsIsNullException() {
		super("It is not allowed to persist a null object!");
	}

}
