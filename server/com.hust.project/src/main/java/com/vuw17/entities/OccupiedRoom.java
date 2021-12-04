package com.vuw17.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "occupied_room")
@Getter
@Setter
public class OccupiedRoom extends BaseEntity{
    @Column(name = "check_in_time", nullable = false)
    private long checkInTime;

    @Column(name = "check_out_time", nullable = false)
    private long checkOutTime;
    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "deposit", nullable = false)
    private BigDecimal deposit;
    @Column(name = "room_id", nullable = false)
    private int roomId;

    @Column(name = "bill_id")
    private int billId;
}
