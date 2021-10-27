package com.testjava.prices.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto extends BaseDto {

	private String code;
	private String description;
}