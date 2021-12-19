package com.vuw17.dto.checkout;

import com.vuw17.dto.room.RoomDTOResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CheckOutResponse {
    private List<RoomDTOResponse> list;
}
