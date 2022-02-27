package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.TableDiaryDAO;
import com.vuw17.dao.jdbc.TypeActionDAO;
import com.vuw17.dao.jpa.OccupiedRoomDao;
import com.vuw17.dao.jpa.RoomDao;
import com.vuw17.dao.jpa.TableDiaryDao;
import com.vuw17.dao.jpa.TypeActionDao;
import com.vuw17.dto.checkout.CheckOutRequest;
import com.vuw17.dto.checkout.CheckOutResponse;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.OccupiedRoom;
import com.vuw17.services.BaseService;
import com.vuw17.services.CheckOutService;
import com.vuw17.services.CommonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckOutServiceImpl extends CommonService implements CheckOutService {
    private final OccupiedRoomDao occupiedRoomDao;
    private final RoomDao roomDao;

    public CheckOutServiceImpl(TableDiaryDAO tableDiaryDAO, TypeActionDAO typeActionDAO, TypeActionDao typeActionDao, TableDiaryDao tableDiaryDao, BaseService baseService, OccupiedRoomDao occupiedRoomDao, RoomDao roomDao) {
        super(tableDiaryDAO, typeActionDAO, typeActionDao, tableDiaryDao, baseService);
        this.occupiedRoomDao = occupiedRoomDao;
        this.roomDao = roomDao;
    }


    //update status
    //update check out time
    @Override
    public boolean checkOut(CheckOutRequest checkOutRequest, UserDTOResponse userDTOResponse) {
        int roomId = checkOutRequest.getRoomId();
        //kiem tra xem cac phong nay da check out chua,neu roi (tuc la isOccupied == false) thi return null

            if (!isOccupied(roomId)) {
                return false;
            }

        //kiem tra xem co cung bill id khong ?
//        boolean checkSameBillId = isTheSameBill(roomIds);
        //Khi id cac phong truyen len deu chua check out
//        if(checkSameBillId){
            updateDiaryWhenCheckedOut(roomId,userDTOResponse);
            return true;
//       }
    }
    public void updateDiaryWhenCheckedOut(int roomId,UserDTOResponse userDTOResponse){
            //ccupiedRoom occupiedRoom = occupiedRoomDao.findByRoomId(roomId);
            //boolean checkUpdate = occupiedRoomDao.updateStatusAndCheckOutTime(occupiedRoom.getId(),ConstantVariableCommon.STATUS_OCCUPIED_ROOM_2, System.currentTimeMillis());
            roomDao.updateStatus(roomId,ConstantVariableCommon.STATUS_ROOM_3);

//            if (checkUpdate && checkUpdateRoom) {
//                saveDiary(ConstantVariableCommon.TYPE_ACTION_UPDATE, occupiedRoom.getId(),ConstantVariableCommon.table_occupied_room,userDTOResponse.getId());
//                saveDiary(ConstantVariableCommon.TYPE_ACTION_UPDATE, roomId,ConstantVariableCommon.table_room,userDTOResponse.getId());
//            }

    }

    public boolean isOccupied(int roomId) {
        if(roomDao.findById(roomId).getStatus() == ConstantVariableCommon.STATUS_ROOM_2){
            return true;
        }
        return false;
    }
//    public boolean isTheSameBill(List<Integer> roomIds){
////      Lay bill id thong qua id dau tien trong list
//        int billId =  occupiedRoomDao.findByRoomId(roomIds.get(0)).getBillId();
//        for(int i = 0;i < roomIds.size();i++){
//            OccupiedRoom occupiedRoom = occupiedRoomDao.findByRoomId(roomIds.get(i));
//            if(occupiedRoom.getBillId() != billId){
//                return false;
//            }
//        }
//        return true;
//    }

}
