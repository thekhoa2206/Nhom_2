package com.vuw17.services;

import com.vuw17.dto.price.PriceDTORequest;
import com.vuw17.dto.price.PriceDTOResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PriceService {

    //Hàm tìm tất cả đang sử dụng theo keyword
    List<PriceDTOResponse> findAllPrice(String keyword, int status);

    //Hàm tạo mới giá
    void createPrice(PriceDTORequest priceDTORequest);

    //Hàm cập nhật giá
    void updatePrice(int id, PriceDTORequest priceDTORequest);

    //hàm tìm giá theo id
    PriceDTOResponse findPriceById(int id);
}
