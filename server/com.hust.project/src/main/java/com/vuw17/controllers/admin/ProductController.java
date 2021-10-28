package com.vuw17.controllers.admin;

import com.vuw17.dto.product.ProductResponseDTO;
import com.vuw17.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    //API List danh sách product có search và phân loại, trạng thái
    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDTO>> listProduct(@RequestParam String keyword, @RequestParam int idTypeProduct, @RequestParam int status){
        List<ProductResponseDTO> productResponseDTOS = productService.findAllProductByParam(keyword, idTypeProduct, status);
        return ResponseEntity.ok(productResponseDTOS);
    }

    //API Tạo product

}
