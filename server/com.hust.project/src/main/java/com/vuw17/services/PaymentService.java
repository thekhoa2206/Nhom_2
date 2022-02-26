package com.vuw17.services;

import com.vuw17.dto.payment.PaymentRequest;
import com.vuw17.dto.user.UserDTOResponse;

public interface PaymentService {
    boolean payment(PaymentRequest paymentRequest, UserDTOResponse userDTOResponse);
}
