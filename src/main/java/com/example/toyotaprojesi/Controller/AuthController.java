package com.example.toyotaprojesi.Controller;

import com.example.toyotaprojesi.Service.AuthService;
import com.example.toyotaprojesi.Service.UserDetailImplement;
import com.example.toyotaprojesi.jwt.JwtProvider;
import com.example.toyotaprojesi.request.LoginRequest;
import com.example.toyotaprojesi.request.SignRequest;
import com.example.toyotaprojesi.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

@PostMapping("signin")
    public ResponseEntity<String> signUser(@RequestBody SignRequest signRequest) {
        authService.signUp(signRequest);
        return ResponseEntity.ok("User added succesfuly");

    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
