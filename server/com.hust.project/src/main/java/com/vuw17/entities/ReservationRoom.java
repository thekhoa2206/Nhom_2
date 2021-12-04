package com.vuw17.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "reservation_room")
public class ReservationRoom extends BaseEntity {
    @Column(name = "room_id", nullable = false)
    private int roomId;
    @Column(name = "reservation_id", nullable = false)
    private int reservationId;

    @Column(name = "status", nullable = true)
    private int status;
}
