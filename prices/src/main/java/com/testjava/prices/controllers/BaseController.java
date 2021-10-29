package com.testjava.prices.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.testjava.prices.dtos.ErrorDto;
import com.testjava.prices.exceptions.ValidationException;

public abstract class BaseController {

	@ExceptionHandler({ Exception.class })
  public ResponseEntity<ErrorDto> handleException(Exception exception) {
		ErrorDto response = new ErrorDto();
		response.setCode("999");
		response.setMessage(exception.getMessage());

		if (exception instanceof ValidationException) {
			ValidationException validationException = (ValidationException) exception;
			response.setCode(validationException.getCode());
			response.setMessage(validationException.getDescription());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
