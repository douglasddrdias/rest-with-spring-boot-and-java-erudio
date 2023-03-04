package com.douglasddr.restwithspringbootandjavaerudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException{

	public UnsupportedMathOperationException(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = -4481339315433832272L;
}
