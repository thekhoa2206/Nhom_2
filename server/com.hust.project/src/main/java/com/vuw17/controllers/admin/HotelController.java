package com.vuw17.controllers.admin;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.dto.hotel.HotelDTORequest;
import com.vuw17.entities.Hotel;
import com.vuw17.services.HotelService;
import com.vuw17.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/hotels")
public class HotelController {
    private final HotelService hotelService;
    private final UserService userService;

    public HotelController(HotelService hotelService, UserService userService) {
        this.hotelService = hotelService;
        this.userService = userService;
    }

    @PostMapping()
    public String insertOne(@Valid @RequestBody HotelDTORequest hotel, HttpServletRequest request) {
        if (userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION)) != null) {
            return hotelService.insertOne(hotel);
        }
        return "Can not authorize you";
    }

    @GetMapping()
    public List<HotelDTORequest> getAll(HttpServletRequest request) {
        if (userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION)) != null) {
            return hotelService.findAll();
        }
        return null;
    }

    @GetMapping("/{id}")
    public HotelDTORequest getOne(@PathVariable int id, HttpServletRequest request) {
        if (userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION)) != null) {

            return hotelService.findById(id);
        }
        return null;
    }

    @PutMapping()
    public String updateOne(@Valid @RequestBody HotelDTORequest hotel, HttpServletRequest request) {
        if (userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION)) != null) {
            return hotelService.updateOne(hotel);
        }
        return "Can not authorize you";
    }

    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable int id, HttpServletRequest request) {
        if (userService.findInfoUser(request.getHeader(ConstantVariableCommon.AUTHORIZATION)) != null) {
            return hotelService.deleteOne(id);
        }
        return null;
    }
}
