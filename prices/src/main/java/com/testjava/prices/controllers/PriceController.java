package com.testjava.prices.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testjava.prices.dtos.BrandDto;
import com.testjava.prices.dtos.PriceDto;
import com.testjava.prices.entities.Price;
import com.testjava.prices.services.BaseService;
import com.testjava.prices.services.PriceService;

@RestController
@RequestMapping(value = "prices")
public class PriceController implements BaseController<PriceDto, Price> {

	@Value("${bindPriceQueue}")
	private String queuePrice;

	@Value("${topicExchangeName}")
	private String exchangePrice;

	@Autowired
	private RabbitTemplate rabbitTemplate;

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

	@PostMapping(value = "/sendPriceToRabbitMQ")
	public ResponseEntity<?> sendToRabbitMQ() throws Exception {
		BrandDto brand = new BrandDto();
		brand.setId(1L);

		PriceDto price = new PriceDto();
		price.setBrand(brand);
		price.setPrice(10.20D);

		ObjectMapper mapper = new ObjectMapper();

		this.rabbitTemplate.convertAndSend(this.exchangePrice, this.queuePrice, mapper.writeValueAsString(price));

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
