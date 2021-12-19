package com.vuw17.controllers.admin;

import com.vuw17.controllers.BaseController;
import com.vuw17.dto.checkin.CheckInRequest;
import com.vuw17.dto.checkout.CheckOutRequest;
import com.vuw17.services.CheckOutService;
import com.vuw17.services.RoomService;
import com.vuw17.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/check-out")
public class CheckOutController extends BaseController {
    private final CheckOutService checkOutService;
    private final RoomService roomService;
    public CheckOutController(UserService userService, CheckOutService checkOutService, RoomService roomService) {
        super(userService);
        this.checkOutService = checkOutService;
        this.roomService = roomService;
    }
    @PutMapping
    public ResponseEntity<Boolean> checkOut(@Valid @RequestBody CheckOutRequest checkOutRequest, HttpServletRequest request){
        return ResponseEntity.ok(checkOutService.checkOut(checkOutRequest,getUserDTOResponse(request)));

    }
}
