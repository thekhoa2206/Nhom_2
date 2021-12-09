package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jdbc.ServiceDAO;
import com.vuw17.dao.jdbc.TableDiaryDAO;
import com.vuw17.dao.jdbc.TypeActionDAO;
import com.vuw17.dao.jpa.ServiceDao;
import com.vuw17.dao.jpa.TableDiaryDao;
import com.vuw17.dao.jpa.TypeActionDao;
import com.vuw17.dto.service.ServiceDTORequest;
import com.vuw17.dto.service.ServiceDTOResponse;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.services.BaseService;
import com.vuw17.services.CommonService;
import com.vuw17.services.ServiceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceServiceImpl extends CommonService implements ServiceService {
    private final ServiceDAO serviceDAO;
    private final ServiceDao serviceDao;
    public ServiceServiceImpl(TableDiaryDAO tableDiaryDAO, TypeActionDAO typeActionDAO, TypeActionDao typeActionDao, TableDiaryDao tableDiaryDao, BaseService baseService, ServiceDAO serviceDAO, ServiceDao serviceDao) {
        super(tableDiaryDAO, typeActionDAO, typeActionDao, tableDiaryDao, baseService);
        this.serviceDAO = serviceDAO;
        this.serviceDao = serviceDao;
    }

    @Override
    public int insertOne(ServiceDTORequest serviceDTORequest, UserDTOResponse userDTOResponse) {
        if(serviceDao.findByName(serviceDTORequest.getName()) == null){
            com.vuw17.entities.Service service = new com.vuw17.entities.Service();
            service.setName(serviceDTORequest.getName());
            service.setNote(serviceDTORequest.getNote());
            service.setPrice(serviceDTORequest.getPrice());
            int serviceId = serviceDAO.insertOne(service);
            if(serviceId > 0){
                saveDiary(ConstantVariableCommon.TYPE_ACTION_CREATE,serviceId,ConstantVariableCommon.table_service, userDTOResponse.getId());
                return serviceId;
            }
        }
        return -1;
    }

    @Override
    public List<ServiceDTOResponse> findAll() {
        List<com.vuw17.entities.Service> services = serviceDao.findAll();
        List<ServiceDTOResponse> serviceDTOResponses = new ArrayList<>();
        for(int i = 0;i < services.size();i++){
            com.vuw17.entities.Service service = services.get(i);
            ServiceDTOResponse serviceDTOResponse = new ServiceDTOResponse();
            serviceDTOResponse.setName(service.getName());
            serviceDTOResponse.setNote(service.getNote());
            serviceDTOResponse.setStatus(service.getStatus());
            serviceDTOResponse.setPrice(service.getPrice());
            serviceDTOResponses.add(serviceDTOResponse);
        }
        return serviceDTOResponses;
    }
}
