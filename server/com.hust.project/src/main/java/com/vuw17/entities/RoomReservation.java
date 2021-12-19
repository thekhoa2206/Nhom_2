package com.vuw17.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reservation_room")
@Getter
@Setter
public class RoomReservation extends BaseEntity{
    @Column(name = "room_id", nullable = true)
    private Integer roomId;

    @Column(name = "reservation_id", nullable = true)
    private Integer reservationId;

    @Column(name = "status", nullable = true)
    private int status;
}
