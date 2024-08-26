package com.ceadar.uidashboard.datamodel.entity;

public interface GeneralEnum<S> {
    String getDescription();
    int getType();
    S findByType(int type);
}
