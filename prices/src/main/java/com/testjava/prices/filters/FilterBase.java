package com.testjava.prices.filters;

import java.util.ArrayList;
import java.util.List;

import com.testjava.prices.criterias.OrderCriteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class FilterBase {

	private List<OrderCriteria> orders = new ArrayList<OrderCriteria>();

}
