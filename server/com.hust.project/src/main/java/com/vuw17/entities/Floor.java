package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "floor")
@Getter
@Setter
public class Floor extends BaseEntity{
    @Column(name = "hotel_id", nullable = false)
    private int hotelId;

    @Column(name = "name", nullable = true, length = 10)
    private String name;

    @Column(name = "note", nullable = true, length = 254)
    private String note;

    @Column(name = "status", nullable = true)
    private int status;
}
