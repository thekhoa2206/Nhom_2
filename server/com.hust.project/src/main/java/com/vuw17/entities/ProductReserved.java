package com.vuw17.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_reserved")
@Getter
@Setter
public class ProductReserved extends BaseEntity {
    @Column(name = "status", nullable = true)
    private int status;

    @Column(name = "quantity", nullable = true)
    private int quantity;

    @Column(name = "reservation_id", nullable = false)
    private int reservationId;

    @Column(name = "product_id", nullable = false)
    private int productId;
}
