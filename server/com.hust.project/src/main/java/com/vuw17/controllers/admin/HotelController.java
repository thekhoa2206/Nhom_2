package com.vuw17.controllers.admin;

import com.vuw17.common.ConstantVariableCommon;
import com.vuw17.configuration.sercurity.jwt.JwtProvider;
import com.vuw17.controllers.BaseController;
import com.vuw17.dto.InsertResponse;
import com.vuw17.dto.UpdateResponse;
import com.vuw17.dto.hotel.HotelDTO;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.entities.User;
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
public class HotelController extends BaseController {
    private final HotelService hotelService;
    private final UserService userService;

    public HotelController(HotelService hotelService, UserService userService) {
        super(userService);
        this.hotelService = hotelService;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<InsertResponse> insertHotel(@Valid @RequestBody HotelDTO hotel, HttpServletRequest request) {
        UserDTOResponse userDTOResponse = getUserDTOResponse(request);
        return ResponseEntity.ok().body(new InsertResponse(hotelService.insertOne(hotel,userDTOResponse)));
    }

    @GetMapping("")
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        return ResponseEntity.ok(hotelService.findAll());
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<HotelDTO>> getHotelsByStatus(@PathVariable int status){
        return ResponseEntity.ok(hotelService.findByStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotel(@PathVariable("id") int id) {
        return ResponseEntity.ok(hotelService.findById(id));
    }

    @PutMapping()
    public ResponseEntity<UpdateResponse> updateHotel(@Valid @RequestBody HotelDTO hotel,HttpServletRequest request) {
        UserDTOResponse userDTOResponse = getUserDTOResponse(request);
        return ResponseEntity.ok(new UpdateResponse(hotelService.updateOne(hotel,userDTOResponse)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UpdateResponse> deleteHotel(@PathVariable("id") int id,HttpServletRequest request) {
        UserDTOResponse userDTOResponse = getUserDTOResponse(request);
        return ResponseEntity.ok(new UpdateResponse(hotelService.deleteOne(id,userDTOResponse)));
    }

}
