package com.vuw17.dto.bill;

import com.vuw17.dto.guest.GuestDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class BillResponse {
//    bill_id, tên khách, phòng, ngày h vào, ngày h ra, tổng tiền
    private int id;
    private List<GuestDTO> guests;
    private String roomName;
    private Long checkInTime;
    private Long checkOutTime;
    private BigDecimal totalPrice;
    private int status;
}
