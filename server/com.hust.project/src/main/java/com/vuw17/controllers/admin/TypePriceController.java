package com.vuw17.controllers.admin;

import com.vuw17.controllers.BaseController;
import com.vuw17.dto.InsertResponse;
import com.vuw17.dto.room.RoomDTO;
import com.vuw17.dto.typeprice.TypePriceDTO;
import com.vuw17.services.TypePriceService;
import com.vuw17.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/typeprices")
public class TypePriceController extends BaseController {
    private final TypePriceService typePriceService;
    public TypePriceController(UserService userService, TypePriceService typePriceService) {
        super(userService);
        this.typePriceService = typePriceService;
    }

    @PostMapping()
    public ResponseEntity<InsertResponse> insertRoom(@Valid @RequestBody TypePriceDTO typePriceDTO, HttpServletRequest request) {
        return ResponseEntity.ok(new InsertResponse(typePriceService.insertOne(typePriceDTO,getUserDTOResponse(request))));
    }
}
