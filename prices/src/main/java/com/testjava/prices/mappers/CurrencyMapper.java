package com.testjava.prices.mappers;

import org.mapstruct.Mapper;

import com.testjava.prices.dtos.CurrencyDto;
import com.testjava.prices.entities.Currency;

@Mapper
public interface CurrencyMapper extends BaseMapper<Currency, CurrencyDto> {

	@Override
	Currency toEntity(CurrencyDto dto);

	@Override
	CurrencyDto toDto(Currency entity);

}
