package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.TableDiaryDAO;
import com.vuw17.dao.jdbc.TypeActionDAO;
import com.vuw17.dao.jpa.*;
import com.vuw17.dto.payment.PaymentRequest;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.Bill;
import com.vuw17.entities.OccupiedRoom;
import com.vuw17.services.BaseService;
import com.vuw17.services.CheckOutService;
import com.vuw17.services.CommonService;
import com.vuw17.services.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends CommonService implements PaymentService {

//    private final RoomDao roomDao;
    private final BillDao billDao;

    public PaymentServiceImpl(TableDiaryDAO tableDiaryDAO, TypeActionDAO typeActionDAO, TypeActionDao typeActionDao, TableDiaryDao tableDiaryDao, BaseService baseService, BillDao billDao,OccupiedRoomDao occupiedRoomDao) {
        super(tableDiaryDAO, typeActionDAO, typeActionDao, tableDiaryDao, baseService);
        this.billDao = billDao;
//        this.occupiedRoomDao = occupiedRoomDao;
    }

    @Override
    public boolean payment(PaymentRequest paymentRequest, UserDTOResponse userDTOResponse) {
        //Kiem tra bill Id phai o trang thai chua thanh toan
        if(billDao.findPaidBillById(paymentRequest.getBillId()) == null){
            Bill bill = billDao.findById(paymentRequest.getBillId());
            //Chua thanh toan thi update thong tin
            bill.setAdditionalFee(paymentRequest.getAdditionalFee());
            bill.setNote(paymentRequest.getNote());
            bill.setPaymentMethod(paymentRequest.isPaymentMethod());
            bill.setReducedFee(paymentRequest.getReducedFee());
            bill.setStatus(ConstantVariableCommon.STATUS_BILL_2);

            boolean checkUpdate = billDao.updateOne(bill);

            if(checkUpdate){
                saveDiary(ConstantVariableCommon.TYPE_ACTION_UPDATE, bill.getId(),ConstantVariableCommon.table_bill,userDTOResponse.getId());
                return true;
            }

        }
        return false;
    }
}
