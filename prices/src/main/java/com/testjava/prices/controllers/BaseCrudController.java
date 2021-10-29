package com.testjava.prices.controllers;

import com.testjava.prices.dtos.BaseDto;
import com.testjava.prices.entities.BaseEntity;
import com.testjava.prices.services.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BaseCrudController<P extends BaseDto, E extends BaseEntity> {

	BaseService<P, E> getService();

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
}
