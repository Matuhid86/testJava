package com.testjava.prices.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.testjava.prices.dtos.BaseDto;
import com.testjava.prices.dtos.ErrorDto;
import com.testjava.prices.entities.BaseEntity;
import com.testjava.prices.exceptions.ValidationException;
import com.testjava.prices.services.BaseService;

public interface BaseController<P extends BaseDto, E extends BaseEntity> {

	public abstract BaseService<P, E> getService();

	@GetMapping(value = "/{id}")
	default public ResponseEntity<P> getOne(@PathVariable String id) throws Exception {

		if (id != null)
			return ResponseEntity.status(HttpStatus.OK).body(this.getService().findOne(Long.parseLong(id)));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@GetMapping
	default public ResponseEntity<List<P>> getAll() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.getService().findAll());
	}

	@PostMapping
	default public ResponseEntity<P> save(@RequestBody P dto) throws Exception {

		if (dto != null) {
			this.getService().save(dto);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@PutMapping
	default public ResponseEntity<P> update(@RequestBody P dto) throws Exception {

		if (dto != null) {
			this.getService().save(dto);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@DeleteMapping(value = "/{id}")
	default public ResponseEntity<?> delete(@PathVariable String id) throws Exception {

		if (id != null) {
			this.getService().delete(Long.parseLong(id));
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@ExceptionHandler({ Exception.class })
	default public ResponseEntity<ErrorDto> handleException(Exception exception) {
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
