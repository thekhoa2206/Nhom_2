package com.vuw17.dao.jdbc.impl;

import com.vuw17.common.Common;
import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.TypePriceDAO;
import com.vuw17.entities.TypePrice;
import org.springframework.stereotype.Component;

@Component
public class TypePriceDAOImpl extends BaseDAO<TypePrice> implements TypePriceDAO {
    @Override
    public int insertOne(TypePrice typePrice) {
        String sql = "INSERT INTO type_price(name,status) VALUES(?,?)";
        return insertOne(sql,typePrice.getName(), ConstantVariableCommon.STATUS_TYPE_PRICE_1);
    }
}
