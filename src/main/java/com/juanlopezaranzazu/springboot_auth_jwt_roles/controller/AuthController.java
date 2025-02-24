package com.juanlopezaranzazu.springboot_auth_jwt_roles.controller;

import com.juanlopezaranzazu.springboot_auth_jwt_roles.dto.AuthResponseDTO;
import com.juanlopezaranzazu.springboot_auth_jwt_roles.dto.LoginRequestDTO;
import com.juanlopezaranzazu.springboot_auth_jwt_roles.dto.RegisterRequestDTO;
import com.juanlopezaranzazu.springboot_auth_jwt_roles.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    // login de usuarios
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    // registro de usuarios
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authService.register(registerRequestDTO));
    }
}
