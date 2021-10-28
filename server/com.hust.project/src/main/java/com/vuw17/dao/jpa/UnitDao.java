package com.vuw17.dao.jpa;

import com.vuw17.entities.Unit;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public interface UnitDao {
    //Hàm tìm Unit bằng id
    Unit findUnitById(int id);

    //hàm tìm list Unit
    List<Unit> findAllUnitByStatus();
}
