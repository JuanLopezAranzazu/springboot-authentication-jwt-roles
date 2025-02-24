package com.juanlopezaranzazu.springboot_auth_jwt_roles.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    // todos los roles
    @GetMapping("/message1")
    public String getMessage1() {
        return "Cualquier usuario";
    }

    // solo rol ROLE_ADMIN
    @GetMapping("/message2")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getMessage2() {
        return "Usuario con rol ROLE_ADMIN";
    }

    // solo rol ROLE_USER
    @GetMapping("/message3")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getMessage3() {
        return "Usuario con rol ROLE_USER";
    }
}
