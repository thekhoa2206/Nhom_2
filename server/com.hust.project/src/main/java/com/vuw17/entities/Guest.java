package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "guest")
@Getter
@Setter
public class Guest extends BaseEntity {
    @Column(name = "first_name", nullable = true, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = true, length = 30)
    private String lastName;

    @Column(name = "date_of_birth", nullable = true)
    private long dateOfBirth;

    @Column(name = "nationality", nullable = true, length = 30)
    private String nationality;

    @Column(name = "address", nullable = true, length = 150)
    private String address;

    @Column(name = "phone_number", nullable = true, length = 15)
    private String phoneNumber;

    @Column(name = "email", nullable = true, length = 100)
    private String email;

    @Column(name = "id_card", nullable = true, length = 50)
    private String idCard;



}
