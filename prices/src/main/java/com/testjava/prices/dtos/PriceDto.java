package com.testjava.prices.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PriceDto extends BaseDto {

	private Long id;
	private BrandDto brand;
	private Date startDate;
	private Date endDate;
	private Long priceList;
	private ProductDto product;
	private Double price;
	private CurrencyDto currency;
}
