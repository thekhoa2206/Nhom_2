package com.vuw17.controllers.admin;

import com.vuw17.dto.hotel.HotelDTO;
import com.vuw17.services.HotelService;
import com.vuw17.services.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> insertHotel(@Valid @RequestBody HotelDTO hotel, HttpServletRequest request) {
        return ResponseEntity.ok().body(hotelService.insertOne(hotel));
    }

    @GetMapping("")
    public ResponseEntity<List<HotelDTO>> getAllHotels(HttpServletRequest request) {
        return ResponseEntity.ok(hotelService.findAll());
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<HotelDTO>> getHotelsByStatus(@PathVariable int status){
        return ResponseEntity.ok(hotelService.findByStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotel(@PathVariable("id") int id, HttpServletRequest request) {
        return ResponseEntity.ok(hotelService.findById(id));
    }

    @PutMapping()
    public ResponseEntity<String> updateHotel(@Valid @RequestBody HotelDTO hotel, HttpServletRequest request) {
        return ResponseEntity.ok(hotelService.updateOne(hotel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable("id") int id, HttpServletRequest request) {
        return ResponseEntity.ok(hotelService.deleteOne(id));
    }
}
