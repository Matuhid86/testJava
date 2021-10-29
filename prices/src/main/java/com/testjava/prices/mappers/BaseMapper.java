package com.testjava.prices.mappers;

import java.util.List;

import com.testjava.prices.dtos.BaseDto;
import com.testjava.prices.entities.BaseEntity;

public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {

	E toEntity(D dto);

	D toDto(E entity);

	List<E> toEntities(List<D> dtos);

	List<D> toDtos(List<E> entities);

}
