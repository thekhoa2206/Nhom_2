package com.vuw17.dao.jpa;

import com.vuw17.entities.Bill;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional(rollbackOn = Exception.class)
public interface BillDao {

    //Kiem tra xem bill da duoc thanh toan chua thong qua billId
    Bill findPaidBillById(int id);
    //Update Bill By id
    boolean updateOne(Bill bill);
    //Tim bill theo id
    Bill findById(int id);
}
