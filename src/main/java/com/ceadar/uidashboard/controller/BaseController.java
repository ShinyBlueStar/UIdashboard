package com.ceadar.uidashboard.controller;

import com.ceadar.uidashboard.datamodel.dto.GeneralDTO;
import com.ceadar.uidashboard.datamodel.entity.GeneralEntity;
import com.ceadar.uidashboard.service.BaseService;

import java.io.Serializable;

public abstract class BaseController<E extends GeneralEntity<ID>, D extends GeneralDTO<ID>,
        ID extends Serializable, S extends BaseService> {
    protected S service;


}
