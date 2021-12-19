package com.vuw17.services;

import com.vuw17.dto.checkout.CheckOutRequest;
import com.vuw17.dto.checkout.CheckOutResponse;
import com.vuw17.dto.user.UserDTOResponse;

public interface CheckOutService {
    boolean checkOut(CheckOutRequest checkOutRequest, UserDTOResponse userDTOResponse);
}
