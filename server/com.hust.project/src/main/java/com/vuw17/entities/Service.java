package com.vuw17.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "service")
@Getter
@Setter
public class Service extends BaseEntity{
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "note", nullable = false)
    private String note;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
