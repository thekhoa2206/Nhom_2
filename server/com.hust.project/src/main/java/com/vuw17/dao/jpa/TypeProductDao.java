package com.vuw17.dao.jpa;

import com.vuw17.entities.Product;
import com.vuw17.entities.TypeProduct;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public interface TypeProductDao {
    //Hàm tìm type product bằng id
    TypeProduct findTypeProductById(int id);

    //hàm tìm list type product theo param
    List<TypeProduct> findAllTypeProductByParam(String keyword, int status);
}
