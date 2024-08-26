package com.ceadar.uidashboard.service;

import com.ceadar.uidashboard.datamodel.dto.GeneralDTO;
import com.ceadar.uidashboard.datamodel.entity.GeneralEntity;
import com.ceadar.uidashboard.mapper.BaseMapper;
import com.ceadar.uidashboard.repository.BaseRepository;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractGeneralService<D extends GeneralDTO<ID>, E extends GeneralEntity<ID>, ID extends Serializable>
        implements BaseService<D, E, ID> {

    protected BaseRepository<E, ID> repository;
    protected BaseMapper<E, D, ID> mapper;

    public AbstractGeneralService(BaseRepository<E, ID> repository, BaseMapper<E, D, ID> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public abstract Long onSave(E e);

    @Override
    public List<E> findAll() {
        return List.of();
    }

    @Override
    public E findById(ID id) {
        return null;
    }

    @Override
    public D toDto(E e) {
        return null;
    }

    @Override
    public E toEntity(D d) {
        return null;
    }

    @Override
    public List<E> saveAll(List<E> e){return null;}
}
