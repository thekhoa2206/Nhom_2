package com.vuw17.controllers.user;

import com.vuw17.configuration.sercurity.jwt.JwtProvider;
import com.vuw17.dto.user.RoleByUserResponseDTO;
import com.vuw17.dto.user.UserDTORequest;
import com.vuw17.dto.user.UserDTOResponse;
import com.vuw17.dto.user.UserDTOUpdateRequest;
import com.vuw17.entities.User;
import com.vuw17.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserService userService;

    public UserController(AuthenticationManager authenticationManager, JwtProvider jwtProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    //API lấy chi tiết người dùng
    @GetMapping("/info")
    public ResponseEntity<UserDTOResponse> getUser(final HttpServletRequest request){
        UserDTOResponse userDTOResponse = userService.findInfoUser(request.getHeader("Authorization"));
        return ResponseEntity.ok(userDTOResponse);
    }

    //API lấy list user
    @GetMapping("/list")
    public ResponseEntity<List<UserDTOResponse>> listUser(){
        List<UserDTOResponse> userDTOResponses = userService.findAllUser();
        return ResponseEntity.ok(userDTOResponses);
    }

    //Api tạo mới user
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTORequest userDTORequest){
        User user = userService.createUser(userDTORequest);
        return ResponseEntity.ok(user);
    }

    //API lấy chi tiết người dùng
    @GetMapping("/{id}")
    public ResponseEntity<UserDTOResponse> getUserById(@PathVariable int id){
        UserDTOResponse userDTOResponse = userService.selectUserById(id);
        return ResponseEntity.ok(userDTOResponse);
    }
    //API sửa người dùng
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @Valid @RequestBody UserDTOUpdateRequest userDTOUpdateRequest){
        User user = userService.updateUser(userDTOUpdateRequest, id);
        return ResponseEntity.ok(user);
    }

    //API xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.ok(id);
    }

    //APi list role
    @GetMapping
    public ResponseEntity<List<RoleByUserResponseDTO>> listRole(){
        List<RoleByUserResponseDTO> roles = userService.findAllRole();
        return ResponseEntity.ok(roles);
    }
}
