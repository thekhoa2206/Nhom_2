package com.vuw17.services;

import com.vuw17.dto.typeprice.TypePriceDTO;
import com.vuw17.dto.typeroom.TypeRoomDTO;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.TypePrice;

public interface TypePriceService {
    //Them moi 1 TypePrice
    int insertOne(TypePriceDTO typePriceDTO, UserDTOResponse userDTOResponse);
    TypePriceDTO findByName(String name);
}
