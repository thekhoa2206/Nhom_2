package com.vuw17.dto.room;

import com.vuw17.dto.BaseDTO;
import com.vuw17.dto.guest.GuestDTO;
import com.vuw17.dto.product.ProductResponseDTO;
import com.vuw17.dto.reservation.ReservationDTO;
import com.vuw17.dto.service.ServiceUsedDTOResponse;
import com.vuw17.dto.typeprice.PriceDTO;
import com.vuw17.entities.Guest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
public class RoomDTOResponse extends BaseDTO {
    private String roomName;
    private int status;
    private String typeRoomName;
    private long checkInTime;
    private long checkOutTime;
    private BigDecimal deposit;
    private List<ServiceUsedDTOResponse> servicesUsed;
    private List<GuestDTO> guests;
    private BigDecimal sumOfPrices;

}
