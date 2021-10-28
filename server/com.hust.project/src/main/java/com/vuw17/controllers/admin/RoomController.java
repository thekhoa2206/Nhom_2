package com.vuw17.controllers.admin;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dto.room.RoomDTORequest;
import com.vuw17.services.RoomService;
import com.vuw17.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public String insertOne(@Valid @RequestBody RoomDTORequest roomDTORequest, HttpServletRequest request) {
        if (userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION)) != null) {
            return roomService.insertOne(roomDTORequest);
        }
        return "Can not authorize you";
    }

    @GetMapping()
    public List<RoomDTORequest> getAll(HttpServletRequest request) {
        if (userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION)) != null) {
            return roomService.findAll();
        }
        return null;
    }

    @GetMapping("/{id}")
    public RoomDTORequest getOne(@PathVariable int id, HttpServletRequest request) {
        if (userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION)) != null) {

            return roomService.findById(id);
        }
        return null;
    }

    @PutMapping()
    public String updateOne(@Valid @RequestBody RoomDTORequest roomDTORequest, HttpServletRequest request) {
        if (userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION)) != null) {
            return roomService.updateOne(roomDTORequest);
        }
        return "Can not authorize you";
    }

    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable int id, HttpServletRequest request) {
        if (userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION)) != null) {
            return roomService.deleteOne(id);
        }
        return null;
    }
}
