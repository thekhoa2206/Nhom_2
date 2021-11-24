package com.vuw17.dao.jpa.impl;

import com.vuw17.dao.jpa.TypeProductDao;
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
public class TypeProductDaoImpl implements TypeProductDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeProductDaoImpl.class.toString());

    @Override
    public TypeProduct findTypeProductById(int id){
        String sql = "SELECT * FROM type_product WHERE 1 = 1 ";
        if(id > 0){
            sql = sql + " AND type_product.id = :id";
        }
        Query query = entityManager.createNativeQuery(sql, TypeProduct.class);
        if(id > 0){
            query.setParameter("id", id);
        }
        System.out.println(sql);
        TypeProduct typeProduct = (TypeProduct) query.getSingleResult();
        return typeProduct;
    }

    @Override
    public List<TypeProduct> findAllTypeProductByParam(String keyword, int status){
        String sql = "SELECT * FROM type_product WHERE 1 = 1 ";

        if(keyword != null && keyword.length() !=0 ){
            sql = sql + " AND  type_product.name LIKE CONCAT('%',LCASE(:keyword),'%') ";
        }
        if(status > 0){
            sql = sql + " AND type_product.status = :status";
        }
        Query query = entityManager.createNativeQuery(sql, TypeProduct.class);
        if(keyword != null && keyword.length() !=0 ){
            query.setParameter("keyword", keyword);
        }
        if(status > 0){
            query.setParameter("status", status);
        }
        List<TypeProduct> typeProducts = query.getResultList();
        return typeProducts;
    }

}
