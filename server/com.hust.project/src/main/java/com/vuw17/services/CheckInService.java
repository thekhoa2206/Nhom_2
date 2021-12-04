package com.vuw17.services;

import com.vuw17.dto.checkin.CheckInRequest;
import com.vuw17.dto.checkin.CheckInResponse;
import com.vuw17.dto.user.UserDTOResponse;

public interface CheckInService {
    int checkIn(CheckInRequest checkinRequest, UserDTOResponse userDTOResponse);
}
