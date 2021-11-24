package com.vuw17.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "service_used")
@Getter
@Setter
public class ServiceUsed extends BaseEntity{
    @Column(name = "occupied_room_id", nullable = false)
    private int occupiedRoomId;

    @Column(name = "service_id", nullable = false)
    private int serviceId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "paid", nullable = false)
    private boolean paid;
}
