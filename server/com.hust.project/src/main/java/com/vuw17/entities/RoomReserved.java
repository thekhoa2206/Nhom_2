package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "room_reserved")
@Getter
@Setter
public class RoomReserved extends BaseEntity {

    @Column(name = "reservation_id", nullable = false)
    private int reservationId;

    @Column(name = "room_id", nullable = false)
    private int roomId;

    @Column(name = "status", nullable = true)
    private int status;

    @Column(name = "cleaner_id", nullable = true)
    private int cleanerId;


}
