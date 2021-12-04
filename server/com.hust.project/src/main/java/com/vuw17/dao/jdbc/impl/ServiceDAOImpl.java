package com.vuw17.dao.jdbc.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.ServiceDAO;
import com.vuw17.entities.Service;
import org.springframework.stereotype.Component;

@Component
public class ServiceDAOImpl extends BaseDAO<Service> implements ServiceDAO {
    @Override
    public int insertOne(Service service) {
        String sql = "INSERT INTO service(name,note,status,price) VALUES(?,?,?,?)";
        return insertOne(sql,service.getName(),service.getNote(), ConstantVariableCommon.STATUS_SERVICE_1,service.getPrice());
    }
}
