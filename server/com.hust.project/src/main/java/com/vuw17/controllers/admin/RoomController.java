package com.vuw17.controllers.admin;

import com.vuw17.services.RoomService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
}
