package com.vuw17.controllers.admin;

import com.vuw17.dto.price.PriceDTORequest;
import com.vuw17.dto.price.PriceDTOResponse;
import com.vuw17.entities.Price;
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

    //API lấy danh sách price
    @GetMapping("/list")
    public ResponseEntity<List<PriceDTOResponse>> listPrice(@RequestParam String keyword, @RequestParam int status){
        List<PriceDTOResponse> priceDTOResponses = priceService.findAllPrice(keyword,status);
        return ResponseEntity.ok(priceDTOResponses);
    }

    //API tạo price
    @PostMapping
    public ResponseEntity<Void> createPrice(@RequestBody PriceDTORequest priceDTORequest){
        priceService.createPrice(priceDTORequest);
        return ResponseEntity.ok().build();
    }

    //API sửa price
    @GetMapping("/{id}")
    public ResponseEntity<PriceDTOResponse> getPrice(@PathVariable("id") int id){
        PriceDTOResponse priceDTOResponse = priceService.findPriceById(id);
        return ResponseEntity.ok(priceDTOResponse);
    }

    //API sửa price
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePrice(@PathVariable("id") int id ,@RequestBody PriceDTORequest priceDTORequest){
        priceService.updatePrice(id,priceDTORequest);
        return ResponseEntity.ok().build();
    }

    //API thay đổi trạng thái ngưng áp dụng giá
    @PutMapping("/status-not-apply/{id}")
    public ResponseEntity<Void> changeStatusPrice(@PathVariable("id") int id){
        priceService.changeStatusPrice(id);
        return ResponseEntity.ok().build();
    }

    //API xóa trạng thái giá
    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable("id") int id){
        priceService.deletePrice(id);
        return ResponseEntity.ok().build();
    }


}
