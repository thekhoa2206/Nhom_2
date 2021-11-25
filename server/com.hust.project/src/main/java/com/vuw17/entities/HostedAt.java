package com.vuw17.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hosted_at")
@Getter
@Setter
public class HostedAt extends BaseEntity {
        @Column(name = "occupied_room_id", nullable = false)
        private int occupiedRoomId;

        @Column(name = "guest_id", nullable = false)
        private int guestId;
}
