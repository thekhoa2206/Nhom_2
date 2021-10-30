package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hotel")
@Getter
@Setter
@ToString
public class Hotel extends BaseEntity{
    @Column(name = "name", nullable = true, length = 20)
    private String name;

    @Column(name = "address", nullable = true, length = 150)
    private String address;

    @Column(name = "note", nullable = true, length = 254)
    private String note;

    @Column(name = "phone_number", nullable = true, length = 10)
    private String phoneNumber;

    @Column(name = "status", nullable = true)
    private int status;
}
