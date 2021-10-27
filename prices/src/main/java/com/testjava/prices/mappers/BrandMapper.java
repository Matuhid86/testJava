package com.testjava.prices.mappers;

import org.mapstruct.Mapper;

import com.testjava.prices.dtos.BrandDto;
import com.testjava.prices.entities.Brand;

@Mapper
public interface BrandMapper extends BaseMapper<Brand, BrandDto> {

	@Override
	Brand toEntity(BrandDto dto);

	@Override
	BrandDto toDto(Brand entity);

}
