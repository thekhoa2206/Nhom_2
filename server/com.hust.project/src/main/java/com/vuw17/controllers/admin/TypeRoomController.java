package com.vuw17.controllers.admin;

import com.vuw17.dto.InsertResponse;
import com.vuw17.dto.UpdateResponse;
import com.vuw17.dto.typeroom.TypeRoomDTO;
import com.vuw17.services.TypeRoomService;
import com.vuw17.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/type_rooms")
public class TypeRoomController {
    private final TypeRoomService typeRoomService;
    private final UserService userService;

    public TypeRoomController(TypeRoomService typeRoomService, UserService userService) {
        this.typeRoomService = typeRoomService;
        this.userService = userService;
    }
    @PostMapping()
    public ResponseEntity<InsertResponse> insertTypeRoom(@Valid  @RequestBody TypeRoomDTO typeRoomDTO) {
        return ResponseEntity.ok(new InsertResponse(typeRoomService.insertOne(typeRoomDTO)));
    }

    @GetMapping()
    public ResponseEntity<List<TypeRoomDTO>> getAllTypeRooms() {
        return ResponseEntity.ok(typeRoomService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeRoomDTO> getTypeRoom(@PathVariable int id) {
        return ResponseEntity.ok(typeRoomService.findById(id));
    }

    @PutMapping()
    public ResponseEntity<UpdateResponse> updateRoom(@Valid @RequestBody TypeRoomDTO typeRoomDTO) {
        return ResponseEntity.ok(new UpdateResponse(typeRoomService.updateOne(typeRoomDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UpdateResponse> deleteRoom(@PathVariable int id) {
        return ResponseEntity.ok(new UpdateResponse(typeRoomService.deleteOne(id)));
    }
}
