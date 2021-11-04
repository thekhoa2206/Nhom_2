package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type_room")
@Getter
@Setter
public class TypeRoom extends BaseEntity {
    @Column(name = "name", nullable = true, length = 30)
    private String name;

    @Column(name = "note", nullable = true, length = 254)
    private String note;

    @Column(name = "number_children", nullable = true)
    private int numberChildren;

    @Column(name = "number_adult", nullable = true)
    private int numberAdult;

    @Column(name = "status", nullable = true)
    private int status;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "room_price", joinColumns = @JoinColumn(name = "type_room_id"), inverseJoinColumns = @JoinColumn(name = "type_price_id"))
//    private List<Price> prices = new ArrayList<Price>();
}
