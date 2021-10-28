package com.vuw17.services.impl;

import com.vuw17.dao.jpa.ProductDao;
import com.vuw17.dao.jpa.TypeProductDao;
import com.vuw17.dao.jpa.UnitDao;
import com.vuw17.dto.product.ProductResponseDTO;
import com.vuw17.dto.typeproduct.TypeProductResponseDTO;
import com.vuw17.dto.unit.UnitResponseDTO;
import com.vuw17.entities.Product;
import com.vuw17.entities.TypeProduct;
import com.vuw17.entities.Unit;
import com.vuw17.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    private final TypeProductDao typeProductDao;
    private final UnitDao unitDao;

    public ProductServiceImpl(ProductDao productDao, TypeProductDao typeProductDao, UnitDao unitDao) {
        this.productDao = productDao;
        this.typeProductDao = typeProductDao;
        this.unitDao = unitDao;
    }


    //Hàm lấy list của product theo param
    public List<ProductResponseDTO> findAllProductByParam(String keyword, int idTypeProduct, int status) {
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        List<Product> products = productDao.findProductByParam(keyword, idTypeProduct, status);
        for (Product product : products) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setName(product.getName());
            productResponseDTO.setNote(product.getNote());
            productResponseDTO.setImportPrice(product.getImportPrice());
            productResponseDTO.setQuantity(product.getQuantity());
            productResponseDTO.setSellingPrice(product.getSellingPrice());

            TypeProduct typeProduct = typeProductDao.findTypeProductById(product.getTypeProductId());
            TypeProductResponseDTO typeProductResponseDTO = new TypeProductResponseDTO();
            typeProductResponseDTO.setId(typeProduct.getId());
            typeProductResponseDTO.setName(typeProduct.getName());
            productResponseDTO.setTypeProductResponseDTO(typeProductResponseDTO);

            Unit unit = unitDao.findUnitById(product.getUnitId());
            UnitResponseDTO unitResponseDTO = new UnitResponseDTO();
            unitResponseDTO.setId(unit.getId());
            unitResponseDTO.setName(unit.getName());
            productResponseDTO.setUnitResponseDTO(unitResponseDTO);

            productResponseDTOS.add(productResponseDTO);
        }
        return productResponseDTOS;
    }
}
