package com.vuw17.controllers.admin;

import com.vuw17.controllers.BaseController;
import com.vuw17.dto.BillResponseDTO;
import com.vuw17.dto.bill.BillResponse;
import com.vuw17.dto.guest.GuestDTO;
import com.vuw17.services.BillService;
import com.vuw17.services.GuestService;
import com.vuw17.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/bills")
public class BillController extends BaseController {

    private final BillService billService;
    private final UserService userService;
    public BillController(BillService billService, UserService userService) {
        super(userService);
        this.userService = userService;
        this.billService = billService;
    }

    @GetMapping
    public ResponseEntity<List<BillResponse>> getAllBills(){
        return ResponseEntity.ok(billService.findAll());
    }
}
