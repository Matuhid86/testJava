package com.testjava.prices.mappers;

import org.mapstruct.Mapper;

import com.testjava.prices.dtos.ProductDto;
import com.testjava.prices.entities.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product, ProductDto> {

	@Override
	Product toEntity(ProductDto dto);

	@Override
	ProductDto toDto(Product entity);

}
