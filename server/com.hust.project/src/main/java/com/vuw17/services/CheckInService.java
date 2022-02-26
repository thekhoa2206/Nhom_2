package com.vuw17.services;

import com.vuw17.dto.checkin.CheckInRequest;
import com.vuw17.dto.checkin.CheckInResponse;
import com.vuw17.dto.checkin.InsertServiceRequest;
import com.vuw17.dto.user.UserDTOResponse;

public interface CheckInService {
    //Check in
    int checkIn(CheckInRequest checkinRequest, UserDTOResponse userDTOResponse) throws Exception;
    //Kiem tra xem phong nay co khach hay ko ?
    boolean isOccupied(int roomId);
    //Them service neu theo nhu cau cua khach
    boolean insertServicesIntoRoom(InsertServiceRequest insertServiceRequest,UserDTOResponse userDTOResponse);
}
