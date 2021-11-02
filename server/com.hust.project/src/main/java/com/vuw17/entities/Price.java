package com.vuw17.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

//    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "prices")
//    private List<TypeRoom> typeRooms = new ArrayList<TypeRoom>();

}
