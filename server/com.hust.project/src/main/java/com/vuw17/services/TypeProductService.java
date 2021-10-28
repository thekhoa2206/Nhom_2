package com.vuw17.services;

import com.vuw17.dto.typeproduct.TypeProductRequestDTO;
import com.vuw17.dto.typeproduct.TypeProductResponseDTO;
import com.vuw17.dto.typeproduct.TypeProductResponseListDTO;
import com.vuw17.entities.TypeProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeProductService {
    //Hàm lấy list type product theo param
    List<TypeProductResponseListDTO> findAllTypeProductByParam(String keyword, int status);

    //hàm tạo type product
    void createTypeProduct(TypeProductRequestDTO typeProductRequestDTO);

    //Hàm tìm type product bằng id
    TypeProduct getTypeProductById(int id);

    //Hàm tìm type product bằng id
    void updateTypeProduct(int id,TypeProductRequestDTO typeProductRequestDTO);

    //Hàm thay đổi trạng thái type product => status =2
    void changeStatusTypeProduct(int id);

    //hàm xóa type product => status =3
    void deleteTypeProduct(int id);
}
