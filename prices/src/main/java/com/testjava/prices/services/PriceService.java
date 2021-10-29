package com.testjava.prices.services;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testjava.prices.criterias.OrderCriteria;
import com.testjava.prices.dtos.PriceDto;
import com.testjava.prices.entities.Price;
import com.testjava.prices.exceptions.ValidationException;
import com.testjava.prices.filters.FilterBase;
import com.testjava.prices.filters.FilterPrice;
import com.testjava.prices.mappers.BaseMapper;
import com.testjava.prices.mappers.PriceMapper;
import com.testjava.prices.repositories.BaseRepository;
import com.testjava.prices.repositories.PriceRepository;

@Service
public class PriceService extends BaseService<PriceDto, Price> {

	@Autowired
	private PriceRepository priceRepository;

	@Override
	protected BaseRepository<Price> getRepository() {
		return priceRepository;
	}

	@Override
	protected BaseMapper<Price, PriceDto> getMapper() {
		return Mappers.getMapper(PriceMapper.class);
	}

	public PriceDto getPriceByPriority(String applicationDate, String productId, String brandId) throws Exception {
		FilterPrice filter = this.getFilterByGetPrices(applicationDate, productId, brandId);

		return this.find(filter).get(0);
	}

	public List<PriceDto> getPriceByBrand(String brandId) throws Exception {
		FilterPrice filter = new FilterPrice();
		filter.setBrandId(Long.parseLong(brandId));

		return this.find(filter);
	}

	private FilterPrice getFilterByGetPrices(String applicationDate, String productCode, String brandId) {
		FilterPrice filter = new FilterPrice();

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			filter.setApplicationDate(dateFormat.parse(applicationDate));
		} catch (Exception ex) {
			throw new ValidationException("400",
					"Parameter: applicationDate - The date format is incorrect. The correct format is 'yyyy-MM-dd HH:mm:ss'");
		}
		try {
			filter.setBrandId(Long.parseLong(brandId));
		} catch (Exception ex) {
			throw new ValidationException("400", "Parameter: brandId - It's must be a number.");
		}

		filter.setProductCode(productCode);

		filter.getOrders().add(new OrderCriteria("priority", "desc"));

		return filter;
	}
}
