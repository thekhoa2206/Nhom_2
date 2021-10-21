package com.vuw17.services.impl;

import com.vuw17.dao.jpa.HotelDao;
import com.vuw17.services.HotelService;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelDao hotelDao;

    public HotelServiceImpl(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

}
