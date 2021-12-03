package com.vuw17.controllers.admin;

import com.vuw17.controllers.BaseController;
import com.vuw17.dto.InsertResponse;
import com.vuw17.dto.checkin.CheckInRequest;
import com.vuw17.services.CheckInService;
import com.vuw17.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/check-in")
public class CheckInController extends BaseController {
    private final CheckInService checkInService;
    public CheckInController(UserService userService, CheckInService checkInService) {
        super(userService);
        this.checkInService = checkInService;
    }

    @PostMapping
    public ResponseEntity<InsertResponse> checkIn(@Valid @RequestBody CheckInRequest checkInRequest, HttpServletRequest request){
        return ResponseEntity.ok(new InsertResponse(checkInService.checkIn(checkInRequest,getUserDTOResponse(request))));
    }
}
