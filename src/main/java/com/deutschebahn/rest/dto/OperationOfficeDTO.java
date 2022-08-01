package com.deutschebahn.rest.dto;

import com.deutschebahn.rest.data.entity.OperationOffice;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class OperationOfficeDTO {
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Kurzname")
    private String shortName;

    @JsonProperty("Typ")
    private String typ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}
