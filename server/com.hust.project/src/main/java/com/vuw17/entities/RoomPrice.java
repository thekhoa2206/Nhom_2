package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "room_price")
@Getter
@Setter
public class RoomPrice extends BaseEntity {
    @Column(name = "type_price_id", nullable = false)
    private int typePriceId;

    @Column(name = "type_room_id", nullable = false)
    private int typeRoomId;

}
