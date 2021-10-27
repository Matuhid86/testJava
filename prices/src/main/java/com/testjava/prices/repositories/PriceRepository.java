package com.testjava.prices.repositories;

import org.springframework.stereotype.Repository;

import com.testjava.prices.criterias.SearchCriteria;
import com.testjava.prices.criterias.SearchOperation;
import com.testjava.prices.criterias.SearchSpecification;
import com.testjava.prices.entities.Price;
import com.testjava.prices.filters.FilterBase;
import com.testjava.prices.filters.FilterPrice;

@Repository
public interface PriceRepository extends BaseRepository<Price> {

	@Override
	default SearchSpecification<Price> getSearchSpecifications(FilterBase filterBase) {
		FilterPrice filter = (FilterPrice) filterBase;
		SearchSpecification<Price> searchSpecifications = new SearchSpecification<Price>(filterBase.getOrders());

		if (filter.getBrandId() != null)
			searchSpecifications.addSearch(new SearchCriteria("brand.id", filter.getBrandId(), SearchOperation.EQUAL));

		if (filter.getProductCode() != null)
			searchSpecifications
					.addSearch(new SearchCriteria("product.code", filter.getProductCode(), SearchOperation.EQUAL));

		if (filter.getApplicationDate() != null)
			searchSpecifications.addSearch(
					new SearchCriteria("startDate", "endDate", filter.getApplicationDate(), SearchOperation.BETWEEN));

		return searchSpecifications;
	}

}
