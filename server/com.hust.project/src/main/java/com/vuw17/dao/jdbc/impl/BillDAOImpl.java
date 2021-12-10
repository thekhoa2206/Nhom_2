package com.vuw17.dao.jdbc.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.BillDAO;
import com.vuw17.entities.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillDAOImpl extends BaseDAO<Bill> implements BillDAO {
    @Override
    public int insertOne(Bill bill) {
        String sql = "INSERT INTO bill(status,reduced_fee,additional_fee,note,money_paid,payment_method) VALUES(?,?,?,?,?,?)";
        return insertOne(sql, ConstantVariableCommon.STATUS_BILL_1,bill.getReducedFee(),bill.getAdditionalFee(),bill.getNote(),bill.getMoneyPaid(),bill.isPaymentMethod());
    }
}
