package com.vuw17.dao.jpa;

import com.vuw17.dto.guest.GuestDTO;
import com.vuw17.entities.Guest;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public interface GuestDao {
    //Lay tat ca cac guests theo room id,kiem tra check in time <= thoi gian hien tai <= check out time
    List<Guest> findByRoomId(int roomId);
    //Tim guest theo id
    Guest findById(int id);
    //Tim guest theo id card
    Guest findByIdCard(String idCard);
    //Tim guest theo phone number
    Guest findByPhoneNumber(String phoneNumber);
    //Lay tat ca guest
    List<Guest> findAll();
    //Search Guest by keyword : phone number,id card
    List<Guest> findByKeyword(String keyword);
    //Update thong tin cua Guest
    boolean update(Guest guest);
    //Xoa thong tin cua Guest
    boolean delete(int id);
}
