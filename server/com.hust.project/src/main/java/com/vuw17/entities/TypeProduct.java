package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "type_product")
@Getter
@Setter
public class TypeProduct extends BaseEntity {
    @Column(name = "name", nullable = true, length = 20)
    private String name;

    @Column(name = "note", nullable = true, length = 254)
    private String note;

    @Column(name = "status", nullable = true)
    private int status;
}
