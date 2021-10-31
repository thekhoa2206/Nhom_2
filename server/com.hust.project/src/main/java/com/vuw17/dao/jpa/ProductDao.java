package com.vuw17.dao.jpa;

import com.vuw17.entities.Product;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public interface ProductDao {

    //hàm lấy list product theo param
    List<Product> findProductByParam(String keyword, int idTypeProduct, int status);

    //Hàm tìm product theo id;
    Product findProductById(int id);
}
