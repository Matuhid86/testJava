package com.testjava.prices.criterias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCriteria {

	private String attribute;
	private String value;

	public OrderCriteria(String attribute, String value) {
		this.attribute = attribute;
		this.value = value;
	}
}
