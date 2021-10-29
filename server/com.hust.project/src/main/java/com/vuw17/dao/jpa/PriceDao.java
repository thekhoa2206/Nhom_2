package com.vuw17.dao.jpa;

import com.vuw17.entities.Price;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public interface PriceDao {
    //Hàm tìm type price theo keyword và status
    List<Price> findAllPriceByKeywordAndStatus(String keyword, int status);

    //Hàm tìm price bằng id
    Price findPriceById(int id);
    //Get price
    Price findByPrice(BigDecimal price);
}
