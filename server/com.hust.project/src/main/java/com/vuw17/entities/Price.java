package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "price")
@Getter
@Setter
public class Price extends  BaseEntity {
    @Column(name = "name", nullable = true, length = 50)
    private String name;
    @Column(name ="status", nullable = true)
    private int status;

}
