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
    @Min(value = 1,message = "Thoi gian phai > 0")
    private long checkOutTime;
    @Min(value = 0,message = "Tiền cọc phai lớn hơn hoặc bằng 0")
    private BigDecimal deposit;

    private int roomId;





}
