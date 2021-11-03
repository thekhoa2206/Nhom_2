package com.vuw17.services;

import com.vuw17.dto.hotel.HotelDTO;
import com.vuw17.dto.user.UserDTOResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {
    //Them moi 1 Hotel
    int insertOne(HotelDTO hotel, UserDTOResponse userDTOResponse);
    //Lay tat ca cac hotels
    List<HotelDTO> findAll();
    //Sua thong tin cua hotel
    boolean updateOne(HotelDTO hotel,UserDTOResponse userDTOResponse);
    //Xoa hotel
    boolean deleteOne(int id,UserDTOResponse userDTOResponse);
    //Lay hotel theo id
    HotelDTO findById(int id);
    //Lay hotel theo address
    HotelDTO findByAddress(String address);
    //Lay hotel theo name
    HotelDTO findByName(String name);
    //Lay hotel theo phone number
    HotelDTO findByPhoneNumber(String phoneNumber);
    //Lay hotel theo status
    List<HotelDTO> findByStatus(int status);
}
