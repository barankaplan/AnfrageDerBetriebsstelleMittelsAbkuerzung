package com.deutschebahn.rest.data.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class OperationOffice {
    @Id
    @Column(nullable = false, length = 6)
    private String code;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "short_name", nullable = false, length = 50)
    private String shortName;

    @Column(name = "typ", length = 15)
    private String typ;

    public String getCode() {
        return code;
    }

    public OperationOffice setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public OperationOffice setName(String name) {
        this.name = name;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public OperationOffice setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public String getTyp() {
        return typ;
    }

    public OperationOffice setTyp(String typ) {
        this.typ = typ;
        return this;
    }

    @Override
    public String toString() {
        return "OperationOffice{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", typ='" + typ + '\'' +
                '}';
    }
}
