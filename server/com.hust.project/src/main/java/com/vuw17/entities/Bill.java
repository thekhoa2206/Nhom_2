package com.vuw17.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
@Getter
@Setter
public class Bill extends BaseEntity{
    @Column(name = "occupied_room_id", nullable = false)
    private int occupiedRoomId;

    @Column(name = "bill_details_id", nullable = false)
    private int billDetailsId;

    @Column(name = "status", nullable = false)
    private int status;

}
