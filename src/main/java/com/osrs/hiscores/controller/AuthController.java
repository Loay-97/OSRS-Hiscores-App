package com.osrs.hiscores.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.osrs.hiscores.dto.auth.RegisterRequest;
import com.osrs.hiscores.dto.auth.LoginRequest;
import com.osrs.hiscores.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // ----------- REGISTER -----------
    @PostMapping("/register")
    public JsonNode register(@RequestBody RegisterRequest request) {
        return authService.register(request.getEmail(), request.getPassword(), request.getDisplayName());
    }

    // ----------- LOGIN -----------
    @PostMapping("/login")
    public JsonNode login(@RequestBody LoginRequest request) {
        return authService.login(request.getEmail(), request.getPassword());
    }

}
