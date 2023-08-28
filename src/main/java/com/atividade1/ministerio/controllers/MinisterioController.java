package com.atividade1.ministerio.controllers;

import com.atividade1.ministerio.dto.ministerio.AddMinisterioDto;
import com.atividade1.ministerio.dto.DefaultResponseDto;
import com.atividade1.ministerio.services.MinisterioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/ministerio")
public class MinisterioController {
    private final MinisterioService _ministerioService;

    public MinisterioController() {
        _ministerioService = new MinisterioService();
    }

    @PostMapping("/")
    public ResponseEntity<DefaultResponseDto<Integer>> add(@RequestBody AddMinisterioDto request) {
        try {
            return ResponseEntity.ok().body(new DefaultResponseDto<Integer>(_ministerioService.Insert(request), "OK", "Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DefaultResponseDto<>(null, "Error", e.getMessage()));
        }
    }
}
