package com.atividade1.ministerio.controllers;

import com.atividade1.ministerio.dto.DefaultResponseDto;
import com.atividade1.ministerio.dto.auth.AuthDto;
import com.atividade1.ministerio.dto.auth.UserResponseDto;
import com.atividade1.ministerio.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth/")
public class AuthController {
    private final AuthService _service;

    public AuthController() {
        _service = new AuthService();
    }

    @PostMapping("login")
    public ResponseEntity<DefaultResponseDto<UserResponseDto>> Login(@RequestBody AuthDto request) {
        try {
            return ResponseEntity.ok().body(new DefaultResponseDto<>(_service.Login(request), "OK", "Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DefaultResponseDto<>(null, "Error", e.getMessage()));
        }
    }
}
