package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.ProductDao;
import com.vuw17.dao.jpa.TypeProductDao;
import com.vuw17.dao.jpa.UnitDao;
import com.vuw17.dto.product.ProductRequestDTO;
import com.vuw17.dto.product.ProductResponseDTO;
import com.vuw17.dto.typeproduct.TypeProductResponseDTO;
import com.vuw17.dto.unit.UnitRequestDTO;
import com.vuw17.dto.unit.UnitResponseDTO;
import com.vuw17.repositories.ProductRepository;
import com.vuw17.repositories.UnitRepository;
import com.vuw17.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    private final TypeProductDao typeProductDao;
    private final ProductRepository productRepository;
    private final UnitRepository unitRepository;
    private final UnitDao unitDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class.toString());

    public ProductServiceImpl(ProductDao productDao, TypeProductDao typeProductDao, ProductRepository productRepository, UnitRepository unitRepository, UnitDao unitDao) {
        this.productDao = productDao;
        this.typeProductDao = typeProductDao;
        this.productRepository = productRepository;
        this.unitRepository = unitRepository;
        this.unitDao = unitDao;
    }


    //Hàm lấy list của product theo param
    @Override
    public List<ProductResponseDTO> findAllProductByParam(String keyword, int idTypeProduct, int status) {
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        List<Product> products = productDao.findProductByParam(keyword, idTypeProduct, status);
        for (Product product : products) {
            ProductResponseDTO productResponseDTO = transferProductDTO(product);
            productResponseDTOS.add(productResponseDTO);
        }
        return productResponseDTOS;
    }

    //Hàm chuyển từ product sang productDTO
    private ProductResponseDTO transferProductDTO(Product product){
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

        return productResponseDTO;
    }

    //Hàm tạo product
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createProduct(ProductRequestDTO productRequestDTO){
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setNote(productRequestDTO.getNote());
        if(productRequestDTO.getImportPrice().compareTo(BigDecimal.valueOf(0)) > 0){
            product.setImportPrice(productRequestDTO.getImportPrice());
        }else{
            product.setImportPrice(BigDecimal.valueOf(0));
        }
        product.setQuantity(productRequestDTO.getQuantity());
        product.setSellingPrice(productRequestDTO.getSellingPrice());
        product.setStatus(ConstantVariableCommon.STATUS_PRODUCT_1);
        product.setUnitId(getUnitProduct(productRequestDTO.getUnitRequestDTO()));

        saveProduct(product);
    }

    //Hàm lưu unit theo product
    private int getUnitProduct(UnitRequestDTO unitRequestDTO){
        int idUnit = 0;
        if(unitRequestDTO.getId() > 0){
            idUnit = unitRequestDTO.getId();
        }else {
            Unit unit = new Unit();
            unit.setName(unitRequestDTO.getName());
            unit.setStatus(ConstantVariableCommon.STATUS_UNIT_1);

            idUnit = unitDao.findUnitByName(unitRequestDTO.getName()).getId();
            if(idUnit > 0){
                return idUnit;
            }else {
                saveUnit(unit);
            }
        }
        return idUnit;
    }

    //Hàm lưu product
    private void saveUnit(Unit unit){
        try {
            unitRepository.save(unit);
        }catch (Exception e){
            LOGGER.error("ERROR || Lỗi lưu đơn vị => service product => ", e.getMessage());
        }
    }

    //Hàm lưu product
    private void saveProduct(Product product){
        try {
            productRepository.save(product);
        }catch (Exception e){
            LOGGER.error("ERROR || Lỗi lưu sản phẩm => service product => ", e.getMessage());
        }
    }

    //Hàm tìm product bằng id
    @Override
    public ProductResponseDTO findProductDTOById(int id){
        Product product = productDao.findProductById(id);
        ProductResponseDTO productResponseDTO = transferProductDTO(product);
        return productResponseDTO;
    }

}
