package com.vuw17.controllers.admin;

import com.vuw17.controllers.BaseController;
import com.vuw17.dto.InsertResponse;
import com.vuw17.dto.UpdateResponse;
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
@RequestMapping("/api/admin/type-rooms")
public class TypeRoomController extends BaseController {
    private final TypeRoomService typeRoomService;
    private final UserService userService;

    public TypeRoomController(TypeRoomService typeRoomService, UserService userService) {
        super(userService);
        this.typeRoomService = typeRoomService;
        this.userService = userService;
    }
    //OK
    @PostMapping()
    public ResponseEntity<InsertResponse> insertTypeRoom(@Valid  @RequestBody TypeRoomDTO typeRoomDTO, HttpServletRequest request) {
        return ResponseEntity.ok(new InsertResponse(typeRoomService.insertOne(typeRoomDTO,getUserDTOResponse(request))));
    }
    //OK
    @GetMapping()
    public ResponseEntity<List<TypeRoomDTO>> getAllTypeRooms() {
        return ResponseEntity.ok(typeRoomService.findAll());
    }
    //OK
    @GetMapping("/{id}")
    public ResponseEntity<TypeRoomDTO> getTypeRoom(@PathVariable int id) {
        return ResponseEntity.ok(typeRoomService.findById(id));
    }

    @PutMapping()
    public ResponseEntity<UpdateResponse> updateRoom(@Valid @RequestBody TypeRoomDTO typeRoomDTO, HttpServletRequest request) {
        return ResponseEntity.ok(new UpdateResponse(typeRoomService.updateOne(typeRoomDTO,getUserDTOResponse(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UpdateResponse> deleteRoom(@PathVariable int id, HttpServletRequest request) {
        return ResponseEntity.ok(new UpdateResponse(typeRoomService.deleteOne(id,getUserDTOResponse(request))));
    }
}
