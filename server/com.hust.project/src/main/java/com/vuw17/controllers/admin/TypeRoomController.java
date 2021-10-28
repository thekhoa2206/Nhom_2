package com.vuw17.controllers.admin;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dto.typeroom.TypeRoomDTORequest;
import com.vuw17.entities.TypeRoom;
import com.vuw17.services.TypeRoomService;
import com.vuw17.services.UserService;
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
    public String insertOne(@Valid  @RequestBody TypeRoomDTORequest typeRoomDTORequest, HttpServletRequest request) {
        if (userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION)) != null) {
            return typeRoomService.insertOne(typeRoomDTORequest);
        }
        return "Can not authorize you";
    }

    @GetMapping()
    public List<TypeRoomDTORequest> getAll(HttpServletRequest request) {
        if (userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION)) != null) {
            return typeRoomService.findAll();
        }
        return null;
    }

    @GetMapping("/{id}")
    public TypeRoomDTORequest getOne(@PathVariable int id, HttpServletRequest request) {
        if (userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION)) != null) {

            return typeRoomService.findById(id);
        }
        return null;
    }

    @PutMapping()
    public String updateOne(@Valid @RequestBody TypeRoomDTORequest typeRoomDTORequest, HttpServletRequest request) {
        if (userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION)) != null) {
            return typeRoomService.updateOne(typeRoomDTORequest);
        }
        return "Can not authorize you";
    }

    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable int id, HttpServletRequest request) {
        if (userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION)) != null) {
            return typeRoomService.deleteOne(id);
        }
        return null;
    }
}
