package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
}
