package com.vuw17.dto.payment;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRequest {
    private int billId;
    private BigDecimal reducedFee;
    private BigDecimal additionalFee;
    private String note;
    private boolean paymentMethod;
}
