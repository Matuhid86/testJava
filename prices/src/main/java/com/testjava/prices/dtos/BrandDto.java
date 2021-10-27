package com.testjava.prices.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrandDto extends BaseDto {

	private Long id;
	private String description;
}
