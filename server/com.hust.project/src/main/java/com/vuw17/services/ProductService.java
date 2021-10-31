package com.vuw17.services;

import com.vuw17.dto.product.ProductRequestDTO;
import com.vuw17.dto.product.ProductResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    //Hàm lấy list product theo param
    List<ProductResponseDTO> findAllProductByParam(String keyword,int idTypeProduct,int status);

    //Hàm tạo product
    void createProduct(ProductRequestDTO productRequestDTO);

    //hàm tìm product bằng id
    ProductResponseDTO findProductDTOById(int id);
}
