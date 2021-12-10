package com.vuw17.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "bill")
@Getter
@Setter
public class Bill extends BaseEntity{
    public Bill(){

    }
    public Bill(BigDecimal reducedFee,BigDecimal additionalFee,String note,BigDecimal moneyPaid,boolean paymentMethod){
        this.reducedFee = reducedFee;
        this.additionalFee = additionalFee;
        this.note = note;
        this.moneyPaid = moneyPaid;
        this.paymentMethod = paymentMethod;


    }
    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "reduced_fee", nullable = true)
    private BigDecimal reducedFee;

    @Column(name = "additional_fee", nullable = true)
    private BigDecimal additionalFee;

    @Column(name = "note", nullable = true,length = 254)
    private String note;

    @Column(name = "money_paid", nullable = true,length = 254)
    private BigDecimal moneyPaid;

    @Column(name = "payment_method", nullable = true)
    private boolean paymentMethod;

}
