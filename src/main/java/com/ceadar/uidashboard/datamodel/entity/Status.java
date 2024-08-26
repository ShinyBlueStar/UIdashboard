package com.ceadar.uidashboard.datamodel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status implements GeneralEnum<Status> {
    NOTSTARTED(0,"not started"),
    ONPROGRESS(1,"on progress"),
    ONHOLD(2,"on hold"),
    FINISHED(3,"finished"),
    TERMINATED(4,"terminated");

    private final int type;
    private final String description;

    Status(int type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public Status findByType(int type) {
        return Status.values()[type];
    }
}
