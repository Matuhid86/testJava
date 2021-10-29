package com.testjava.prices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testjava.prices.dtos.PriceDto;
import com.testjava.prices.entities.Price;
import com.testjava.prices.services.BaseService;
import com.testjava.prices.services.PriceService;

import java.util.List;

@RestController
@RequestMapping(value = "prices")
public class PriceController extends BaseController implements BaseCrudController<PriceDto, Price>  {

	@Autowired
	private PriceService priceService;

	@Override
	public BaseService<PriceDto, Price> getService() {
		return priceService;
	}

	@GetMapping(value = "/{applicationDate}/{productId}/{brandId}")
	public ResponseEntity<PriceDto> getPriceByPriority(@PathVariable String applicationDate,
			@PathVariable String productId, @PathVariable String brandId) throws Exception {
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.priceService.getPriceByPriority(applicationDate, productId, brandId));
	}
}
