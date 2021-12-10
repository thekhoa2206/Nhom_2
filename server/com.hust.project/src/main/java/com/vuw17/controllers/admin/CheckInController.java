package com.vuw17.controllers.admin;

import com.vuw17.controllers.BaseController;
import com.vuw17.dto.InsertResponse;
import com.vuw17.dto.checkin.CheckInRequest;
import com.vuw17.dto.checkin.InsertServiceRequest;
import com.vuw17.dto.room.RoomDTOResponse;
import com.vuw17.services.CheckInService;
import com.vuw17.services.RoomService;
import com.vuw17.services.UserService;
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
    private final RoomService roomService;
    public CheckInController(UserService userService, CheckInService checkInService, RoomService roomService) {
        super(userService);
        this.checkInService = checkInService;
        this.roomService = roomService;
    }
    //OK
    @PostMapping
    public ResponseEntity<InsertResponse> checkIn(@Valid @RequestBody CheckInRequest checkInRequest, HttpServletRequest request){
        return ResponseEntity.ok(new InsertResponse(checkInService.checkIn(checkInRequest,getUserDTOResponse(request))));
    }
    //API them service theo nhu cau cua khach theo phong

    //OK
    @PostMapping("/insert-services")
    public ResponseEntity<RoomDTOResponse> insertServices(@Valid @RequestBody InsertServiceRequest insertServiceRequest, HttpServletRequest request){
        checkInService.insertServicesIntoRoom(insertServiceRequest,getUserDTOResponse(request));
        return ResponseEntity.ok(roomService.findById(insertServiceRequest.getRoomId()));
    }
}
