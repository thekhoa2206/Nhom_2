package com.vuw17.services;

import com.vuw17.dto.UpdateResponse;
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
    //Tim theo id
    GuestDTO findById(int id);
    //Update Thong tin cua khach
    GuestDTO update(GuestDTO guestDTO,UserDTOResponse userDTOResponse);
    //Xoa thong tin cua khach
    UpdateResponse delete(int id, UserDTOResponse userDTOResponse);
}
