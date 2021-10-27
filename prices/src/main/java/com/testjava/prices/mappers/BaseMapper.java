package com.testjava.prices.mappers;

import java.util.ArrayList;
import java.util.List;

import com.testjava.prices.dtos.BaseDto;
import com.testjava.prices.entities.BaseEntity;

public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {

	E toEntity(D dto);

	D toDto(E entity);

	default List<E> toEntities(List<D> dtos) {
		List<E> entities = new ArrayList<E>();

		for (D dto : dtos)
			entities.add(this.toEntity(dto));

		return entities;
	}

	default List<D> toDtos(List<E> entities) {
		List<D> dtos = new ArrayList<D>();

		for (E entity : entities)
			dtos.add(this.toDto(entity));

		return dtos;
	}

}
