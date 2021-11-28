//package com.vuw17.controllers.admin;
//
//import com.vuw17.dto.typeproduct.TypeProductRequestDTO;
//import com.vuw17.dto.typeproduct.TypeProductResponseListDTO;
//import com.vuw17.services.TypeProductService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@RequestMapping("/api/admin/type_products")
//public class TypeProductController {
//    private final TypeProductService typeProductService;
//
//    public TypeProductController(TypeProductService typeProductService) {
//        this.typeProductService = typeProductService;
//    }
//
//    //API Lấy list type Product
//    @GetMapping("/list")
//    public ResponseEntity<List<TypeProductResponseListDTO>> listTypeProduct(@RequestParam String keyword, @RequestParam int status){
//        List<TypeProductResponseListDTO> typeProductResponseListDTOS = typeProductService.findAllTypeProductByParam(keyword,status);
//        return ResponseEntity.ok(typeProductResponseListDTOS);
//    }
//
//    //API tạo mới type Product
//    @PostMapping
//    public ResponseEntity<Void> createTypeProduct(@RequestBody TypeProductRequestDTO typeProductRequestDTO){
//        typeProductService.createTypeProduct(typeProductRequestDTO);
//        return ResponseEntity.ok().build();
//    }
//
//    //API lấy type product theo id
//    @GetMapping("/{id}")
//    public ResponseEntity<TypeProduct> getTypeProductById(@PathVariable("id") int id){
//        TypeProduct typeProduct = typeProductService.getTypeProductById(id);
//        return ResponseEntity.ok(typeProduct);
//    }
//
//    //API sửa type product
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateTypeProduct(@PathVariable("id") int id, @RequestBody TypeProductRequestDTO typeProductRequestDTO){
//        typeProductService.updateTypeProduct(id, typeProductRequestDTO);
//        return ResponseEntity.ok().build();
//    }
//
//    //API thay đổi trạng thái => status = 2 => ngừng áp dụng
//    @PutMapping("/status/{id}")
//    public ResponseEntity<Void> changeStatusTypeProduct(@PathVariable("id") int id){
//        typeProductService.changeStatusTypeProduct(id);
//        return ResponseEntity.ok().build();
//    }
//
//    //API thay đổi trạng thái => status = 2 => xóa
//    @PutMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteTypeProduct(@PathVariable("id") int id){
//        typeProductService.deleteTypeProduct(id);
//        return ResponseEntity.ok().build();
//    }
//}
