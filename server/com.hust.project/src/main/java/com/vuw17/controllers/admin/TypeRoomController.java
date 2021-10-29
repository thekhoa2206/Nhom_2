package com.vuw17.controllers.admin;

import com.vuw17.dto.typeroom.TypeRoomDTO;
import com.vuw17.services.TypeRoomService;
import com.vuw17.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<String> insertTypeRoom(@Valid  @RequestBody TypeRoomDTO typeRoomDTO) {
        return ResponseEntity.ok(typeRoomService.insertOne(typeRoomDTO));
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
    public ResponseEntity<String> updateRoom(@Valid @RequestBody TypeRoomDTO typeRoomDTO) {
        return ResponseEntity.ok(typeRoomService.updateOne(typeRoomDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable int id) {
        return ResponseEntity.ok(typeRoomService.deleteOne(id));
    }
}
