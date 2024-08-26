package com.ceadar.uidashboard.datamodel.entity;

import java.io.Serializable;

public abstract class GeneralEntity<ID extends Serializable> {
    public GeneralEntity() {
    }
    public abstract void setId(ID id);
    public abstract Long getId();

}
