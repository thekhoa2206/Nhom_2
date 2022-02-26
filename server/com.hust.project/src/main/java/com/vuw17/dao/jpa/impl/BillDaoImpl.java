package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.BillDao;
import com.vuw17.entities.Bill;
import com.vuw17.entities.Guest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional(rollbackOn = Exception.class)
public class BillDaoImpl implements BillDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(GuestDaoImpl.class.toString());

    @Override
    public Bill findPaidBillById(int id) {
        String sql = "SELECT * FROM bill WHERE status = 2 AND id = ?";
        try{
            return (Bill) entityManager.createNativeQuery(sql,Bill.class).setParameter(1,id).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean updateOne(Bill bill) {
        String sql = "UPDATE bill SET status = ?,reduced_fee = ?,additional_fee = ?,note = ?,payment_method = ?,money_paid = ? WHERE id = ?";
        try{
            entityManager.createNativeQuery(sql).setParameter(1,bill.getStatus()).setParameter(2,bill.getReducedFee()).setParameter(3,bill.getAdditionalFee()).setParameter(4,bill.getNote()).setParameter(5,bill.isPaymentMethod()).setParameter(6,bill.getMoneyPaid()).setParameter(7,bill.getId()).executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Bill findById(int id) {
        String sql = "SELECT * FROM bill WHERE id = ?";
        try{
            return (Bill) entityManager.createNativeQuery(sql,Bill.class).setParameter(1,id).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
