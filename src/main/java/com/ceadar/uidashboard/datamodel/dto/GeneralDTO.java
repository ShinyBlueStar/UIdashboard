package com.ceadar.uidashboard.datamodel.dto;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public abstract class GeneralDTO<ID extends Serializable> implements Serializable {
    @EqualsAndHashCode.Include
    protected ID id;
    @JsonProperty
    public ID getId(){
        return id;
    }
    @JsonProperty
    public void setId(ID id){
        this.id = id;
    }
    public GeneralDTO() {
    }
}
