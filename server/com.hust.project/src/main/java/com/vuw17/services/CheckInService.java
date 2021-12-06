package com.vuw17.services;

import com.vuw17.dto.checkin.CheckInRequest;
import com.vuw17.dto.checkin.CheckInResponse;
import com.vuw17.dto.user.UserDTOResponse;

public interface CheckInService {
    //Check in
    int checkIn(CheckInRequest checkinRequest, UserDTOResponse userDTOResponse);
    //Kiem tra xem phong nay co khach hay ko ?
    boolean isOccupied(int roomId);
}
