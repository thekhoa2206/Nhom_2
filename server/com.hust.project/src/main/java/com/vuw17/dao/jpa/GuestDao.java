package com.vuw17.dao.jpa;

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
}
