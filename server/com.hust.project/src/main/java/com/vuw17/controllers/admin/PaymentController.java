package com.vuw17.controllers.admin;

import com.vuw17.controllers.BaseController;
import com.vuw17.dto.checkout.CheckOutRequest;
import com.vuw17.dto.payment.PaymentRequest;
import com.vuw17.services.CheckOutService;
import com.vuw17.services.PaymentService;
import com.vuw17.services.RoomService;
import com.vuw17.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/payment")
public class PaymentController extends BaseController {
    private PaymentService paymentService;
    public PaymentController(UserService userService, PaymentService paymentService) {
        super(userService);
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Boolean> payment(@Valid @RequestBody PaymentRequest paymentRequest, HttpServletRequest request){
        return ResponseEntity.ok(paymentService.payment(paymentRequest,getUserDTOResponse(request)));

    }
}
