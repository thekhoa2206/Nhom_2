package com.vuw17.services;

import com.vuw17.dto.hotel.HotelDTORequest;
import com.vuw17.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {
    //Them moi 1 Hotel
    String insertOne(HotelDTORequest hotel);
    //Lay tat ca cac hotels
    List<HotelDTORequest> findAll();
    //Sua thong tin cua hotel
    String updateOne(HotelDTORequest hotel);
    //Xoa hotel
    String deleteOne(int id);
    //Lay hotel theo id
    HotelDTORequest findById(int id);
    //Lay hotel theo address
    HotelDTORequest findByAddress(String address);
    //Lay hotel theo name
    HotelDTORequest findByName(String name);
    //Lay hotel theo phone number
    HotelDTORequest findByPhoneNumber(String phoneNumber);
}
