package com.vuw17.controllers.admin;

import com.vuw17.controllers.BaseController;
import com.vuw17.dto.InsertResponse;
import com.vuw17.dto.service.ServiceDTORequest;
import com.vuw17.dto.service.ServiceDTOResponse;
import com.vuw17.services.ServiceService;
import com.vuw17.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/services")
public class ServiceController extends BaseController {
    private final ServiceService serviceService;
    public ServiceController(UserService userService, ServiceService serviceService) {
        super(userService);
        this.serviceService = serviceService;
    }
    //OK
    @PostMapping()
    public ResponseEntity<InsertResponse> insertRoom(@Valid @RequestBody ServiceDTORequest serviceDTORequest, HttpServletRequest request) {
        return ResponseEntity.ok(new InsertResponse(serviceService.insertOne(serviceDTORequest,getUserDTOResponse(request))));
    }
    //OK
    @GetMapping()
    public ResponseEntity<List<ServiceDTOResponse>> getAllServices() {
        return ResponseEntity.ok(serviceService.findAll());
    }

}
