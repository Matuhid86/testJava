package com.testjava.prices.criterias;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("serial")
public class SearchSpecification<T> implements Specification<T> {

	private List<SearchCriteria> searchCriterias;
	private List<OrderCriteria> orderCriterias;

	public SearchSpecification() {
		this.searchCriterias = new ArrayList<>();
		this.orderCriterias = new ArrayList<>();
	}

	public SearchSpecification(List<OrderCriteria> orderCriterias) {
		this.searchCriterias = new ArrayList<>();
		this.orderCriterias = orderCriterias;
	}

	public void addOrder(OrderCriteria orderCriteria) {
		orderCriterias.add(orderCriteria);
	}

	public void addSearch(SearchCriteria searchCriteria) {
		searchCriterias.add(searchCriteria);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		List<Order> orders = new ArrayList<Order>();

		for (SearchCriteria criteria : this.searchCriterias) {
			Expression expression1 = null;
			Expression expression2 = null;

			if (criteria.getAttributes().get(0).contains(".")) {
				Integer indexPoint = criteria.getAttributes().get(0).indexOf(".");
				expression1 = root.join(criteria.getAttributes().get(0).substring(0, indexPoint))
						.get(criteria.getAttributes().get(0).substring(indexPoint + 1));

				if (criteria.getAttributes().size() == 2)
					expression2 = root.join(criteria.getAttributes().get(1).substring(0, indexPoint))
							.get(criteria.getAttributes().get(1).substring(indexPoint + 1));
			} else {
				expression1 = root.get(criteria.getAttributes().get(0));

				if (criteria.getAttributes().size() == 2)
					expression2 = root.get(criteria.getAttributes().get(1));
			}

			switch (criteria.getOperation()) {
			case GREATER_THAN:
				predicates.add(builder.greaterThan(expression1, criteria.getValue().toString()));
				break;
			case LESS_THAN:
				predicates.add(builder.lessThan(expression1, criteria.getValue().toString()));
				break;
			case GREATER_THAN_EQUAL:
				predicates.add(builder.greaterThanOrEqualTo(expression1, criteria.getValue().toString()));
				break;
			case LESS_THAN_EQUAL:
				predicates.add(builder.lessThanOrEqualTo(expression1, criteria.getValue().toString()));
				break;
			case NOT_EQUAL:
				predicates.add(builder.notEqual(expression1, criteria.getValue()));
				break;
			case EQUAL:
				predicates.add(builder.equal(expression1, criteria.getValue()));
				break;
			case MATCH:
				predicates.add(builder.like(builder.lower(expression1),
						"%" + criteria.getValue().toString().toLowerCase() + "%"));
				break;
			case MATCH_END:
				predicates.add(
						builder.like(builder.lower(expression1), criteria.getValue().toString().toLowerCase() + "%"));
				break;
			case MATCH_START:
				predicates.add(
						builder.like(builder.lower(expression1), "%" + criteria.getValue().toString().toLowerCase()));
				break;
			case IN:
				predicates.add(builder.in(expression1).value(criteria.getValue()));
				break;
			case NOT_IN:
				predicates.add(builder.not(expression1).in(criteria.getValue()));
			case BETWEEN:
				predicates.add(builder.lessThanOrEqualTo(expression1, (Date) criteria.getValue()));
				predicates.add(builder.greaterThanOrEqualTo(expression2, (Date) criteria.getValue()));
				break;
			}
		}

		for (OrderCriteria orderCriteria : this.orderCriterias) {
			if (orderCriteria.getValue().toUpperCase().equals("DESC"))
				orders.add(builder.desc(root.get(orderCriteria.getAttribute())));
			else
				orders.add(builder.asc(root.get(orderCriteria.getAttribute())));
		}

		query.orderBy(orders);

		return builder.and(predicates.toArray(new Predicate[0]));
	}
}
