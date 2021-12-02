package com.vuw17.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "reservation")
@Getter
@Setter
public class Reservation extends BaseEntity {
    @Column(name = "note", nullable = true)
    private String note;

    @Column(name = "date_from", nullable = true)
    private long dateFrom;

    @Column(name = "date_to", nullable = true)
    private long dateTo;

    @Column(name = "status", nullable = true)
    private int status;

    @Column(name = "number_room", nullable = true)
    private int numberRoom;

    @Column(name = "guest_id", nullable = true)
    private int guestId;
}
