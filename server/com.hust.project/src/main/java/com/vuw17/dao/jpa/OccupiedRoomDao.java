package com.vuw17.dao.jpa;

import com.vuw17.entities.OccupiedRoom;

import java.util.List;

public interface OccupiedRoomDao {
    //lay ra danh sach phong dang co khach
    List<OccupiedRoom> findOccupiedRooms();
    //Lay ra theo room id
    List<OccupiedRoom>  findByRoomId(int roomId);
    //Lay ra theo id va status da check out
    OccupiedRoom findAvailableRoomById(int id);
    //Update status va check out time khi check out thanh cong
    boolean updateStatusAndCheckOutTime(int id,int status,long checkOutTime);
    //lay ra danh sach phong da duoc thue bang bill id
    List<OccupiedRoom> findOccupiedRoomsByBillId(int billId);
    //Lay ra theo occupied id
    OccupiedRoom findById(int id);
    //Lay ra theo bill id
    OccupiedRoom findByBillId(int billId);
}
