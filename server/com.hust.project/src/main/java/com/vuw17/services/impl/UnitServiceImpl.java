//package com.vuw17.services.impl;
//
//import com.vuw17.dao.jpa.UnitDao;
//import com.vuw17.dto.unit.UnitResponseDTO;
//import com.vuw17.repositories.UnitRepository;
//import com.vuw17.services.UnitService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UnitServiceImpl implements UnitService {
//    private final UnitDao unitDao;
//    private final UnitRepository unitRepository;
//    private static final Logger LOGGER = LoggerFactory.getLogger(UnitServiceImpl.class.toString());
//
//    public UnitServiceImpl(UnitDao unitDao, UnitRepository unitRepository) {
//        this.unitDao = unitDao;
//        this.unitRepository = unitRepository;
//    }
//
//    // Hàm lấy list unit
//    @Override
//    public List<UnitResponseDTO> listUnit(){
//        List<Unit> units = unitDao.findAllUnitByStatus();
//        List<UnitResponseDTO> unitResponseDTOS = new ArrayList<>();
//        for (Unit unit: units) {
//            UnitResponseDTO unitResponseDTO = new UnitResponseDTO();
//            unitResponseDTO.setName(unit.getName());
//            unitResponseDTO.setId(unit.getId());
//            unitResponseDTOS.add(unitResponseDTO);
//        }
//        return unitResponseDTOS;
//    }
//
//    //Hàm tạo unit
//    @Override
//    public void createUnit(UnitResponseDTO unitResponseDTO){
//        Unit unit = new Unit();
//        unit.setName(unitResponseDTO.getName());
//        try {
//            unitRepository.save(unit);
//        }catch (Exception e){
//            LOGGER.error("ERROR || Lỗi lưu unit service ", e.getMessage());
//        }
//    }
//}
