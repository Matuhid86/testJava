package com.testjava.prices.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testjava.prices.dtos.PriceDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class PriceControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@SuppressWarnings("unchecked")
	@Test
	public void getAllPrices() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/prices").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Integer status = result.getResponse().getStatus();

		assertEquals(200, status);

		List<PriceDto> prices = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);

		assertNotNull(prices);
		assertTrue(prices.size() > 0);
	}

	@Test
	public void getOne() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/prices/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Integer status = result.getResponse().getStatus();

		assertEquals(200, status);

		PriceDto price = objectMapper.readValue(result.getResponse().getContentAsString(), PriceDto.class);

		assertNotNull(price);

		assertEquals(1, price.getId());
	}

	@Test
	public void test1() throws Exception {
		String uri = "/prices/2020-06-14 10:00:00/35455/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Integer status = result.getResponse().getStatus();

		assertEquals(200, status);

		PriceDto price = objectMapper.readValue(result.getResponse().getContentAsString(), PriceDto.class);

		assertNotNull(price);

		assertEquals(35.50, price.getPrice());
		assertEquals("EUR", price.getCurrency().getCode());
		assertEquals("35455", price.getProduct().getCode());
		assertEquals(1, price.getBrand().getId());
	}

	@Test
	public void test2() throws Exception {
		String uri = "/prices/2020-06-14 16:00:00/35455/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Integer status = result.getResponse().getStatus();

		assertEquals(200, status);

		PriceDto price = objectMapper.readValue(result.getResponse().getContentAsString(), PriceDto.class);

		assertNotNull(price);

		assertEquals(25.45, price.getPrice());
		assertEquals("EUR", price.getCurrency().getCode());
		assertEquals("35455", price.getProduct().getCode());
		assertEquals(1, price.getBrand().getId());
	}

	@Test
	public void test3() throws Exception {
		String uri = "/prices/2020-06-14 21:00:00/35455/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Integer status = result.getResponse().getStatus();

		assertEquals(200, status);

		PriceDto price = objectMapper.readValue(result.getResponse().getContentAsString(), PriceDto.class);

		assertNotNull(price);

		assertEquals(35.50, price.getPrice());
		assertEquals("EUR", price.getCurrency().getCode());
		assertEquals("35455", price.getProduct().getCode());
		assertEquals(1, price.getBrand().getId());
	}

	@Test
	public void test4() throws Exception {
		String uri = "/prices/2020-06-15 10:00:00/35455/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Integer status = result.getResponse().getStatus();

		assertEquals(200, status);

		PriceDto price = objectMapper.readValue(result.getResponse().getContentAsString(), PriceDto.class);

		assertNotNull(price);

		assertEquals(30.50, price.getPrice());
		assertEquals("EUR", price.getCurrency().getCode());
		assertEquals("35455", price.getProduct().getCode());
		assertEquals(1, price.getBrand().getId());
	}

	@Test
	public void test5() throws Exception {
		String uri = "/prices/2020-06-16 21:00:00/35455/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Integer status = result.getResponse().getStatus();

		assertEquals(200, status);

		PriceDto price = objectMapper.readValue(result.getResponse().getContentAsString(), PriceDto.class);

		assertNotNull(price);

		assertEquals(38.95, price.getPrice());
		assertEquals("EUR", price.getCurrency().getCode());
		assertEquals("35455", price.getProduct().getCode());
		assertEquals(1, price.getBrand().getId());
	}
}
