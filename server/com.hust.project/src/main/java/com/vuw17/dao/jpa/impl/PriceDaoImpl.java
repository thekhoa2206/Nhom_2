package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.PriceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class PriceDaoImpl implements PriceDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(PriceDaoImpl.class.toString());

    //Hàm tìm type price theo keyword và status
    @Override
    public List<Price> findAllPriceByKeywordAndStatus(String keyword, int status){
        String sql = "SELECT * FROM price WHERE 1 = 1 ";

        if(keyword != null && keyword.length() !=0 ){
            sql = sql + " AND  price.name LIKE CONCAT('%',LCASE(:keyword),'%') ";
        }
        if(status > 0){
            sql = sql + " AND price.status = :status";
        }
        Query query = entityManager.createNativeQuery(sql, Price.class);
        if(keyword != null && keyword.length() !=0 ){
            query.setParameter("keyword", keyword);
        }
        if(status > 0){
            query.setParameter("status", status);
        }
        List<Price> prices = query.getResultList();
        return prices;
    }

    //Hàm tìm price theo id
    @Override
    public Price findPriceById(int id){
        String sql = "SELECT * FROM price WHERE 1 = 1 ";
        if (id > 0) {
            sql = sql + " AND price.id = :id";
        }
        Query query = entityManager.createNativeQuery(sql, Price.class);
        if (id > 0) {
            query.setParameter("id", id);
        }
        Price price = (Price) query.getSingleResult();
        return price;
    }

    @Override
    public Price findByPrice(BigDecimal price) {
        String sql = "SELECT * FROM price WHERE price = ?";
        try {
            return (Price) entityManager.createNativeQuery(sql, Price.class).setParameter(1, price).getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }
}
