package com.vuw17.dto.checkin;

import com.vuw17.dto.guest.GuestDTO;
import com.vuw17.dto.room.RoomDTO;
import com.vuw17.dto.service.ServiceUsedDTOResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class CheckInResponse {

    private RoomDTO roomDTO;

    private List<GuestDTO> guests;

    private List<ServiceUsedDTOResponse> services;

}
