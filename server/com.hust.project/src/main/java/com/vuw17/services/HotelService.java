package com.vuw17.services;

import com.vuw17.dto.hotel.HotelDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {
    //Them moi 1 Hotel
    int insertOne(HotelDTO hotel);
    //Lay tat ca cac hotels
    List<HotelDTO> findAll();
    //Sua thong tin cua hotel
    boolean updateOne(HotelDTO hotel);
    //Xoa hotel
    boolean deleteOne(int id);
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
