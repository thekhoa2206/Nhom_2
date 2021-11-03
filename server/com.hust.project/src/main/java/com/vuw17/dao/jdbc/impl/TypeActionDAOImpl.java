package com.vuw17.dao.jdbc.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.TypeActionDAO;
import com.vuw17.entities.TypeAction;
import org.springframework.stereotype.Component;

@Component
public class TypeActionDAOImpl extends BaseDAO<TypeAction> implements TypeActionDAO {
    @Override
    public int insertOne(TypeAction typeAction) {
        String sql = "INSERT INTO type_action(name,note,status) VALUES(?,?,?)";
        return insertOne(sql,typeAction.getName(),typeAction.getNote(), ConstantVariableCommon.ACTION_CREATE);
    }
}
