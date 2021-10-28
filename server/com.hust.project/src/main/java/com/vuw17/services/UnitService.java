package com.vuw17.services;

import com.vuw17.dto.unit.UnitResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UnitService {

    // Hàm lấy list unit
    List<UnitResponseDTO> listUnit();

    //Hàm tạo unit
    void createUnit(UnitResponseDTO unitResponseDTO);
}
