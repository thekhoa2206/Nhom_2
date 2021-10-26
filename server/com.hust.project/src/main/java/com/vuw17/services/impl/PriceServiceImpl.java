package com.vuw17.services.impl;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dao.jpa.PriceDao;
import com.vuw17.dto.base.DiaryDTO;
import com.vuw17.dto.price.PriceDTORequest;
import com.vuw17.dto.price.PriceDTOResponse;
import com.vuw17.entities.Price;
import com.vuw17.repositories.PriceRepository;
import com.vuw17.services.BaseService;
import com.vuw17.services.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceDao priceDao;
    private final BaseService baseService;
    private final PriceRepository  priceRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PriceServiceImpl.class.toString());

    public PriceServiceImpl(PriceDao priceDao, BaseService baseService, PriceRepository priceRepository) {
        this.priceDao = priceDao;
        this.baseService = baseService;
        this.priceRepository = priceRepository;
    }


    //Hàm tìm tất cả type_price theo keyword và status
    @Override
    public List<PriceDTOResponse> findAllPrice(String keyword, int status){
        List<Price> prices = priceDao.findAllPriceByKeywordAndStatus(keyword, status);
        List<PriceDTOResponse> priceDTOResponses = new ArrayList<>();
        for (Price price : prices) {
            PriceDTOResponse priceDTOResponse = new PriceDTOResponse();
            priceDTOResponse.setName(price.getName());
            priceDTOResponse.setPrice(price.getPrice());
            priceDTOResponses.add(priceDTOResponse);
        }
        return priceDTOResponses;
    }

    //Hàm tạo mới price
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createPrice(PriceDTORequest priceDTORequest){
        Price price = new Price();
        price.setName(priceDTORequest.getName());
        price.setPrice(priceDTORequest.getPrice());
        price.setStatus(ConstantVariableCommon.STATUS_PRICE_1);


        savePrice(price);
    }

    //Hàm lưu price
    private void savePrice(Price price){
        try {
            priceRepository.save(price);
        }catch (Exception e){
            LOGGER.error("ERROR || Lưu giá phòng lỗi ", e.getMessage());
        }
    }

    //Hàm cập nhật price
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updatePrice(int id, PriceDTORequest priceDTORequest){
        Price price = priceDao.findPriceById(id);
        price.setName(priceDTORequest.getName());
        price.setPrice(priceDTORequest.getPrice());
        savePrice(price);
    }

    //hàm tìm price theo id
    @Override
    public PriceDTOResponse findPriceById(int id){
        Price price = priceDao.findPriceById(id);
        PriceDTOResponse priceDTOResponse = new PriceDTOResponse();
        priceDTOResponse.setName(price.getName());
        priceDTOResponse.setPrice(price.getPrice());
        return priceDTOResponse;
    }

    //Hàm thay đổi trạng thái giá thành không áp dụng
    @Override
    public void  changeStatusPrice(int id){
        Price price = priceDao.findPriceById(id);
        price.setStatus(ConstantVariableCommon.STATUS_PRICE_2);
        savePrice(price);
    }

    @Override
    public void deletePrice(int id){
        Price price = priceDao.findPriceById(id);
        price.setStatus(ConstantVariableCommon.STATUS_PRICE_3);
        savePrice(price);
    }


}
