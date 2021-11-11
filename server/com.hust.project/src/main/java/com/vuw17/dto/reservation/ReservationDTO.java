package com.vuw17.dto.reservation;

import com.vuw17.dto.guest.GuestDTO;
import com.vuw17.dto.product.ProductResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ReservationDTO {
    private BigDecimal total;

    private BigDecimal deposit;

    private byte paymentMethod;

    private String note;

    private BigDecimal reduceFee;

    private BigDecimal additionalFee;

    private long dateFrom;

    private long dateTo;

    private int status;
    private List<ProductResponseDTO> products;
    private List<GuestDTO> guests;
}
