package com.testjava.prices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.testjava.prices.criterias.SearchSpecification;
import com.testjava.prices.entities.BaseEntity;
import com.testjava.prices.filters.FilterBase;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Object>, JpaSpecificationExecutor<E> {

	default List<E> find(FilterBase filter) {

		if (filter == null)
			return this.findAll();

		SearchSpecification<E> searchSpecification = this.getSearchSpecifications(filter);

		return this.findAll(searchSpecification);
	}

	default SearchSpecification<E> getSearchSpecifications(FilterBase filterBase) {
		return null;
	}
}
