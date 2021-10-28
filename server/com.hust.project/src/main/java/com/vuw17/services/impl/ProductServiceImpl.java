package com.vuw17.services.impl;

import com.vuw17.dao.jpa.ProductDao;
import com.vuw17.services.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }
}
