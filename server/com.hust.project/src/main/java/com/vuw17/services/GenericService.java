package com.vuw17.services;

import com.vuw17.dto.hotel.HotelDTORequest;

public interface GenericService<T>{
    String checkInput(T t);
}
