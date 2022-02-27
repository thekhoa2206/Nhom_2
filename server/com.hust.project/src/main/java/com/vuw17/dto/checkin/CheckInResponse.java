package com.vuw17.dto.checkin;

import com.vuw17.dto.guest.GuestDTO;
import com.vuw17.dto.room.RoomDTO;
import com.vuw17.dto.room.RoomDTOResponse;
import com.vuw17.dto.service.ServiceUsedDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
public class CheckInResponse {
    private RoomDTOResponse checkInResponse;

}
