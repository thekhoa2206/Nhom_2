package com.vuw17.services.impl;

import com.vuw17.dao.jpa.PriceDao;
import com.vuw17.dto.price.PriceDTOResponse;
import com.vuw17.entities.Price;
import com.vuw17.services.PriceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceDao priceDao;

    public PriceServiceImpl(PriceDao priceDao) {
        this.priceDao = priceDao;
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

}
