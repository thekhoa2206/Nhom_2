package com.vuw17.controllers;

import com.vuw17.configuration.sercurity.jwt.JwtProvider;
import com.vuw17.dto.sercurity.JwtResponse;
import com.vuw17.dto.sercurity.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth/login")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public LoginController(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }


    @PostMapping
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest, final HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);

        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setMaxAge(100);
        response.addCookie(cookie);
        System.out.println("cookie" + cookie);

        return ResponseEntity.ok(new JwtResponse(jwt));
}
}
