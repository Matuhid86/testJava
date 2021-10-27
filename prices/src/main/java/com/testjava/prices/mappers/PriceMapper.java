package com.testjava.prices.mappers;

import org.mapstruct.Mapper;

import com.testjava.prices.dtos.PriceDto;
import com.testjava.prices.entities.Price;

@Mapper
public interface PriceMapper extends BaseMapper<Price, PriceDto> {

	@Override
	Price toEntity(PriceDto dto);

	@Override
	PriceDto toDto(Price entity);

}
