package com.vuw17.controllers.admin;

import com.vuw17.controllers.BaseController;
import com.vuw17.dto.InsertResponse;
import com.vuw17.dto.UpdateResponse;
import com.vuw17.dto.room.RoomDTO;
import com.vuw17.dto.room.RoomDTOResponse;
import com.vuw17.services.RoomService;
import com.vuw17.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/rooms")
public class RoomController extends BaseController {
    private final RoomService roomService;

    public RoomController(RoomService roomService, UserService userService) {
        super(userService);
        this.roomService = roomService;
    }

    @PostMapping()
    public ResponseEntity<InsertResponse> insertRoom(@Valid @RequestBody RoomDTO roomDTO, HttpServletRequest request) {
        return ResponseEntity.ok(new InsertResponse(roomService.insertOne(roomDTO,getUserDTOResponse(request))));
    }

    @GetMapping()
    public ResponseEntity<List<RoomDTOResponse>> getAllRooms() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTOResponse> getRoom(@PathVariable int id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @PutMapping()
    public ResponseEntity<UpdateResponse> updateRoom(@Valid @RequestBody RoomDTO roomDTO,HttpServletRequest request) {
        return ResponseEntity.ok(new UpdateResponse(roomService.updateOne(roomDTO,getUserDTOResponse(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UpdateResponse> deleteRoom(@PathVariable int id,HttpServletRequest request) {
        return ResponseEntity.ok(new UpdateResponse(roomService.deleteOne(id,getUserDTOResponse(request))));
    }
    @GetMapping("/type-room/{typeRoomId}")
    public ResponseEntity<List<RoomDTOResponse>> getRoomsByTypeRoomId(@PathVariable int typeRoomId){
        return ResponseEntity.ok(roomService.findByTypeRoomId(typeRoomId));
    }
}

