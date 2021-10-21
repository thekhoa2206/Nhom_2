package com.vuw17.entities;

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
    @Column(name = "total", nullable = true)
    private BigDecimal total;

    @Column(name = "deposit", nullable = true)
    private BigDecimal deposit;

    @Column(name = "payment_method", nullable = true)
    private byte paymentMethod;

    @Column(name = "note", nullable = true)
    private String note;

    @Column(name = "reduce_fee", nullable = true)
    private BigDecimal reduceFee;

    @Column(name = "additional_fee", nullable = true)
    private BigDecimal additionalFee;

    @Column(name = "date_from", nullable = true)
    private long dateFrom;

    @Column(name = "date_to", nullable = true)
    private long dateTo;

    @Column(name = "status", nullable = true)
    private int status;
}
