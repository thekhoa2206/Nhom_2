package com.vuw17.dao.jdbc.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.PriceDAO;
import org.springframework.stereotype.Component;

@Component
public class PriceDAOImpl extends BaseDAO<Price> implements PriceDAO {

    @Override
    public int insertOne(Price price) {
        String sql = "INSERT INTO price(name,price,status) VALUES(?,?,?)";
        return insertOne(sql,price.getName(),price.getPrice(), ConstantVariableCommon.STATUS_PRICE_1);
    }
}
