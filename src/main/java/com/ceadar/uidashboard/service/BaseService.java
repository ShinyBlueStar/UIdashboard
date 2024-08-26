package com.ceadar.uidashboard.service;

import java.io.Serializable;
import java.util.List;

import com.ceadar.uidashboard.datamodel.dto.GeneralDTO;
import com.ceadar.uidashboard.datamodel.entity.GeneralEntity;

public interface BaseService<D extends GeneralDTO<ID>, E extends GeneralEntity<ID>, ID extends Serializable> {


    List<E> findAll();
    E findById(ID id);
    D toDto(E e);
    E toEntity(D d);
    E addTask(E e);
    Long count();
    E onUpdate(E e);
    List<E> saveAll(List<E> e);
}
