//package com.vuw17.controllers.admin;
//
//import com.vuw17.dto.product.ProductRequestDTO;
//import com.vuw17.dto.product.ProductResponseDTO;
//import com.vuw17.entities.Product;
//import com.vuw17.services.ProductService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@RequestMapping("/api/admin/products")
//public class ProductController {
//    private final ProductService productService;
//
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }
//
//
//    //API List danh sách product có search và phân loại, trạng thái
//    @GetMapping("/list")
//    public ResponseEntity<List<ProductResponseDTO>> listProduct(@RequestParam String keyword, @RequestParam int idTypeProduct, @RequestParam int status){
//        List<ProductResponseDTO> productResponseDTOS = productService.findAllProductByParam(keyword, idTypeProduct, status);
//        return ResponseEntity.ok(productResponseDTOS);
//    }
//
//    //API Tạo product
//    @PostMapping
//    public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO){
//        productService.createProduct(productRequestDTO);
//        return ResponseEntity.ok().build();
//    }
//
//    //API Tìm product bằng id
//    @GetMapping("/{id}")
//    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") int id){
//        ProductResponseDTO productResponseDTO = productService.findProductDTOById(id);
//        return ResponseEntity.ok(productResponseDTO);
//    }
//}
