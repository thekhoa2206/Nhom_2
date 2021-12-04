package com.vuw17.services;

import com.vuw17.dto.guest.GuestDTO;
import com.vuw17.dto.user.UserDTOResponse;

import java.util.List;

public interface GuestService {
    //insert 1 guest
    int insertOne(GuestDTO guestDTO, UserDTOResponse userDTOResponse);
    //Get List Guest
    List<GuestDTO> findAll();
    //Search Guest by keyword : phone number,id card
    List<GuestDTO> findByKeyword(String keyword);
}
