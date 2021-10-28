package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.TypeProductDao;
import com.vuw17.dto.typeproduct.TypeProductRequestDTO;
import com.vuw17.dto.typeproduct.TypeProductResponseListDTO;
import com.vuw17.entities.TypeProduct;
import com.vuw17.repositories.TypeProductRepository;
import com.vuw17.services.TypeProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TypeProductServiceImpl implements TypeProductService {
    private final TypeProductDao typeProductDao;
    private final TypeProductRepository typeProductRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeProductServiceImpl.class.toString());

    public TypeProductServiceImpl(TypeProductDao typeProductDao, TypeProductRepository typeProductRepository) {
        this.typeProductDao = typeProductDao;
        this.typeProductRepository = typeProductRepository;
    }

    @Override
    //Hàm lấy list type product theo param
    public List<TypeProductResponseListDTO> findAllTypeProductByParam(String keyword, int status){
        List<TypeProductResponseListDTO> typeProductDTOS = new ArrayList<>();
        List<TypeProduct> typeProducts = typeProductDao.findAllTypeProductByParam(keyword, status);
        for (TypeProduct typeProduct: typeProducts) {
            TypeProductResponseListDTO typeProductDTO = new TypeProductResponseListDTO();
            typeProductDTO.setName(typeProduct.getName());
            typeProductDTO.setNote(typeProduct.getNote());
            typeProductDTOS.add(typeProductDTO);
        }
        return typeProductDTOS;
    }

    //Hàm tạo mới type product
    @Override
    public void createTypeProduct(TypeProductRequestDTO typeProductRequestDTO){
        TypeProduct typeProduct = new TypeProduct();
        typeProduct.setName(typeProductRequestDTO.getName());
        typeProduct.setNote(typeProductRequestDTO.getNote());
        typeProduct.setStatus(ConstantVariableCommon.STATUS_TYPE_PRODUCT_1);

        saveTypeProduct(typeProduct);
    }

    //Hàm lưu typeProduct
    @Transactional(rollbackOn = Exception.class)
    public void saveTypeProduct(TypeProduct typeProduct){
        try{
            typeProductRepository.save(typeProduct);
        }catch (Exception e){
            LOGGER.error("ERROR || Lỗi không lưu được type product trong service", e.getMessage());
        }
    }

    //Hàm tìm type product bằng id
    @Override
    public TypeProduct getTypeProductById(int id){
        TypeProduct typeProduct = typeProductDao.findTypeProductById(id);
        return typeProduct;
    }

    //Hàm tìm type product bằng id
    @Override
    public void updateTypeProduct(int id,TypeProductRequestDTO typeProductRequestDTO){
        TypeProduct typeProduct = typeProductDao.findTypeProductById(id);
        typeProduct.setName(typeProductRequestDTO.getName());
        typeProduct.setNote(typeProductRequestDTO.getNote());

        saveTypeProduct(typeProduct);
    }


    //Hàm thay đổi trạng thái type product => status =2
    @Override
    public void changeStatusTypeProduct(int id){
        TypeProduct typeProduct = typeProductDao.findTypeProductById(id);
        typeProduct.setStatus(ConstantVariableCommon.STATUS_TYPE_PRODUCT_2);

        saveTypeProduct(typeProduct);
    }

    //hàm xóa type product => status =3
    @Override
    public void deleteTypeProduct(int id){
        TypeProduct typeProduct = typeProductDao.findTypeProductById(id);
        typeProduct.setStatus(ConstantVariableCommon.STATUS_TYPE_PRODUCT_3);

        saveTypeProduct(typeProduct);
    }
}
