package com.vuw17.controllers.admin;

import com.vuw17.services.TypeRoomService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/type_rooms")
public class TypeRoomController {
    private final TypeRoomService typeRoomService;

    public TypeRoomController(TypeRoomService typeRoomService) {
        this.typeRoomService = typeRoomService;
    }
}
