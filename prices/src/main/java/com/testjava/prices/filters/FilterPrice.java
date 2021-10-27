package com.testjava.prices.filters;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilterPrice extends FilterBase {

	private Long brandId;
	private String productCode;
	private Date applicationDate;
}
