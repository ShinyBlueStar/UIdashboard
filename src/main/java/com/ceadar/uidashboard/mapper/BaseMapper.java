package com.ceadar.uidashboard.mapper;

import java.io.Serializable;

import com.ceadar.uidashboard.datamodel.dto.GeneralDTO;
import com.ceadar.uidashboard.datamodel.entity.GeneralEntity;

public interface BaseMapper<E extends GeneralEntity, D extends GeneralDTO, ID extends Serializable> {

    E dtoToEntity(D d);
    D entityToDto(E e);
    Class<E> getEntityType();
    Class<D> getDtoType();

}
