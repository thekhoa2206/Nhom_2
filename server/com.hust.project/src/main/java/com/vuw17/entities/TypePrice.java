package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "type_price")
@Getter
@Setter
public class TypePrice extends BaseEntity {

    @Column(name = "name", nullable = true, length = 50,  insertable = true, updatable = true)
    private String name;

    @Column(name = "status", nullable = true,  insertable = true, updatable = true)
    private int status;

}
