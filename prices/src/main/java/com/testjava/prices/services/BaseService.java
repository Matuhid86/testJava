package com.testjava.prices.services;

import java.util.ArrayList;
import java.util.List;

import com.testjava.prices.dtos.BaseDto;
import com.testjava.prices.entities.BaseEntity;
import com.testjava.prices.filters.FilterBase;
import com.testjava.prices.mappers.BaseMapper;
import com.testjava.prices.repositories.BaseRepository;

public abstract class BaseService<P extends BaseDto, E extends BaseEntity> {

	protected abstract BaseRepository<E> getRepository();

	protected abstract BaseMapper<E, P> getMapper();

	protected void validateSave(P dto) throws Exception {
	}

	protected void validateDelete(P dto) throws Exception {
	}

	protected List<E> getEntitiesByFilter(FilterBase filter) throws Exception {
		return new ArrayList<E>();
	}

	public List<P> find(FilterBase filter) throws Exception {
		List<E> entities = this.getEntitiesByFilter(filter);

		if (entities != null && entities.size() > 0)
			return this.getMapper().toDtos(entities);

		return new ArrayList<P>();
	}

	public List<P> findAll() throws Exception {
		List<E> entities = this.getRepository().findAll();

		if (entities != null && entities.size() > 0)
			return this.getMapper().toDtos(entities);

		return new ArrayList<P>();
	}

	public P findOne(Object id) throws Exception {
		E entity = this.getRepository().findById(id).get();

		if (entity != null)
			return this.getMapper().toDto(entity);

		return null;
	}

	public void save(P dto) throws Exception {
		this.validateSave(dto);

		this.getRepository().save(this.getMapper().toEntity(dto));
	}

	public void delete(Object id) throws Exception {
		E entity = this.getRepository().findById(id).get();
		P dto = this.getMapper().toDto(entity);

		this.delete(dto);
	}

	private void delete(P dto) throws Exception {
		this.validateDelete(dto);

		this.getRepository().delete(this.getMapper().toEntity(dto));
	}
}
