//package com.vuw17.services.impl;
//
//import com.vuw17.dao.jpa.TypeActionDao;
//import com.vuw17.entities.TypeAction;
//import com.vuw17.services.TypeActionService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TypeActionServiceImpl implements TypeActionService {
//    private final TypeActionDao typeActionDao;
//
//    public TypeActionServiceImpl(TypeActionDao typeActionDao) {
//        this.typeActionDao = typeActionDao;
//    }
//
//    @Override
//    public int findByName(String name) {
//        TypeAction typeAction = typeActionDao.findByName(name);
//        if(typeAction != null){
//            return typeAction.getId();
//        }
//        return 0;
//    }
//}
