package com.testjava.prices.criterias;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {

	private List<String> attributes = new ArrayList<String>();
	private Object value;
	private SearchOperation operation;

	public SearchCriteria(String attribute, Object value, SearchOperation operation) {
		this.attributes.add(attribute);
		this.value = value;
		this.operation = operation;
	}

	public SearchCriteria(String startAttribute, String endAttribute, Object value, SearchOperation operation) {
		this.attributes.add(startAttribute);
		this.attributes.add(endAttribute);
		this.value = value;
		this.operation = operation;
	}

}
