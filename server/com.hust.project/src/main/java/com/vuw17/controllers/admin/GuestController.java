package com.vuw17.controllers.admin;

import com.vuw17.controllers.BaseController;
import com.vuw17.dto.InsertResponse;
import com.vuw17.dto.UpdateResponse;
import com.vuw17.dto.guest.GuestDTO;
import com.vuw17.services.GuestService;
import com.vuw17.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/admin/guests")
public class GuestController extends BaseController {
    private final GuestService guestService;
    private final UserService userService;
    public GuestController(GuestService guestService, UserService userService) {
        super(userService);
        this.guestService = guestService;
        this.userService = userService;
    }

    //api insert guest
    //OK
    @PostMapping
    public ResponseEntity<InsertResponse> insertGuest(@Valid @RequestBody GuestDTO guestDTO, HttpServletRequest request){
        return ResponseEntity.ok(new InsertResponse(guestService.insertOne(guestDTO,getUserDTOResponse(request))));
    }
    //OK
    @GetMapping
    public ResponseEntity<List<GuestDTO>> getAllGuests(){
        return ResponseEntity.ok(guestService.findAll());
    }
    //api search guest by id card,phone number
    //OK
    @GetMapping("/searching")
    public ResponseEntity<List<GuestDTO>> getAllGuestsByKeyword(@RequestParam("keyword") String keyword){
        return ResponseEntity.ok(guestService.findByKeyword(keyword));
    }
    //OK
    @PutMapping()
    public ResponseEntity<GuestDTO> getAllGuestsByKeyword(@Valid @RequestBody GuestDTO guestDTO, HttpServletRequest request){
        return ResponseEntity.ok(guestService.update(guestDTO,getUserDTOResponse(request)));
    }
    //OK
    @DeleteMapping("/{id}")
    public ResponseEntity<UpdateResponse> updateGuest(@PathVariable int id, HttpServletRequest request){
        return ResponseEntity.ok(guestService.delete(id,getUserDTOResponse(request)));
    }

}
