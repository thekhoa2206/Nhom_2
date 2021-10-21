package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reservation_guest")
@Getter
@Setter
public class ReservationGuest extends BaseEntity {
    @Column(name = "status", nullable = true)
    private int status;

    @Column(name = "guest_id", nullable = false)
    private int guestId;

    @Column(name = "reservation_id", nullable = false)
    private int reservationId;

}
