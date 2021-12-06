package com.vuw17.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "bill_details")
@Getter
@Setter
public class BillDetails extends BaseEntity{
    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "reduced_fee", nullable = false)
    private BigDecimal reducedFee;

    @Column(name = "additional_fee", nullable = true)
    private BigDecimal additionalFee;

    @Column(name = "note", nullable = true,length = 254)
    private String note;

    @Column(name = "payment_method", nullable = true)
    private boolean paymentMethod;
}
