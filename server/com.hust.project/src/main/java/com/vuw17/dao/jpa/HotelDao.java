package com.vuw17.dao.jpa;

import com.vuw17.dto.hotel.HotelDTORequest;
import com.vuw17.entities.Hotel;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public interface HotelDao {
    //Them moi 1 Hotel
    void insertOne(Hotel hotel);
    //Lay tat ca cac hotels
    List<Hotel> findAll();
    //Sua thong tin cua hotel
    void updateOne(Hotel hotel);
    //Xoa hotel
    void deleteOne(int id);
    //Lay hotel theo id
    Hotel findById(int id);
    //Lay hotel theo address
    Hotel findByAddress(String address);
    //Lay hotel theo name
    Hotel findByName(String name);
    //Lay hotel theo phone number
    Hotel findByPhoneNumber(String phoneNumber);
}
