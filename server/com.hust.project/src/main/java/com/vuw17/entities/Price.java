package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "price")
@Getter
@Setter
public class Price extends BaseEntity {
    public Price() {

    }
    public Price(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @Column(name = "name", nullable = true, length = 50)
    private String name;

    @Column(name = "status", nullable = true)
    private int status;

    @Column(name = "price", nullable = true)
    private BigDecimal price;

}
