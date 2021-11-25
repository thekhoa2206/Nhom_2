package com.vuw17.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "type_price")
@Getter
@Setter
public class TypePrice extends BaseEntity{
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private int status;
}
