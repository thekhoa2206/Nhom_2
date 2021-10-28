package com.vuw17.controllers.admin;

import com.vuw17.dto.price.PriceDTOResponse;
import com.vuw17.services.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/price")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    // Hàm lấy danh sách price
    @GetMapping("/list")
    public ResponseEntity<List<PriceDTOResponse>> listPrice(@RequestParam String keyword, @RequestParam int status){
        List<PriceDTOResponse> priceDTOResponses = priceService.findAllPrice(keyword,status);
        return ResponseEntity.ok(priceDTOResponses);
    }


}
