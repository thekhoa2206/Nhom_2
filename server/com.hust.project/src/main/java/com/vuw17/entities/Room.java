package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "room")
@Getter
@Setter
public class Room extends BaseEntity {
    @Column(name = "hotel_id", nullable = false)
    private int hotelId;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "type_room_id", nullable = false)
    private int typeRoomId;

    @Column(name = "note", nullable = true)
    private String note;

    @Column(name = "status", nullable = true)
    private int status;
}
