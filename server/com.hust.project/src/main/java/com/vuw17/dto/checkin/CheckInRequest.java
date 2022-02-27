package com.vuw17.dto.checkin;

import com.vuw17.dto.service.ServiceUsedDTORequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CheckInRequest{
    @Size(min = 1,message = "Thieu danh sach khach check in")
    private List<Integer> guestIds;
    private List<ServiceUsedDTORequest> servicesUsed;
    private long checkOutTime;
    private BigDecimal deposit;
    private BigDecimal additionalFee;
    private BigDecimal reducedFee;

    private int roomId;





}
