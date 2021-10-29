package com.vuw17.controllers.admin;

import com.vuw17.dto.room.RoomDTO;
import com.vuw17.services.RoomService;
import com.vuw17.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/rooms")
public class RoomController {
    private final RoomService roomService;
    private final UserService userService;

    public RoomController(RoomService roomService, UserService userService) {
        this.roomService = roomService;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<String> insertRoom(@Valid @RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok(roomService.insertOne(roomDTO));
    }

    @GetMapping()
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable int id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @PutMapping()
    public ResponseEntity<String> updateRoom(@Valid @RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok(roomService.updateOne(roomDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable int id) {
        return ResponseEntity.ok(roomService.deleteOne(id));
    }
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomDTO>> getRoomsByHotelId(@PathVariable int hotelId){
        return ResponseEntity.ok(roomService.findByHotelId(hotelId));
    }
    @GetMapping("/type-room/{typeRoomId}")
    public ResponseEntity<List<RoomDTO>> getRoomsByTypeRoomId(@PathVariable int typeRoomId){
        return ResponseEntity.ok(roomService.findByTypeRoomId(typeRoomId));
    }
}

