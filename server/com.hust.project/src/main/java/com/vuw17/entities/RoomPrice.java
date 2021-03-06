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
@Table(name = "room_price")
@Getter
@Setter
@AllArgsConstructor
public class RoomPrice extends BaseEntity {
    public RoomPrice() {
    }

    @Column(name = "type_price_id", nullable = false)
    private int typePriceId;

    @Column(name = "type_room_id", nullable = false)
    private int typeRoomId;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

}
