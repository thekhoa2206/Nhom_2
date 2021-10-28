package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.ProductDao;
import com.vuw17.dao.jpa.UserDao;
import com.vuw17.entities.Price;
import com.vuw17.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class.toString());

    @Override
    public List<Product> findProductByParam(String keyword, int idTypeProduct, int status){
        String sql = "SELECT * FROM product WHERE 1 = 1 ";

        if(keyword != null && keyword.length() !=0 ){
            sql = sql + " AND  product.name LIKE CONCAT('%',LCASE(:keyword),'%') ";
        }
        if(status > 0){
            sql = sql + " AND product.status = :status";
        }
        if(idTypeProduct > 0){
            sql = sql + " AND product.type_product_id = :idTypeProduct";
        }
        Query query = entityManager.createNativeQuery(sql, Product.class);
        if(keyword != null && keyword.length() !=0 ){
            query.setParameter("keyword", keyword);
        }
        if(status > 0){
            query.setParameter("status", status);
        }
        if(idTypeProduct > 0){
            query.setParameter("idTypeProduct", idTypeProduct);
        }
        List<Product> products = query.getResultList();
        return products;
    }
}
