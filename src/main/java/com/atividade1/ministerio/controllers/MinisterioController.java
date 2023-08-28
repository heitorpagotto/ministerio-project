package com.atividade1.ministerio.controllers;

import com.atividade1.ministerio.dto.ministerio.AddMinisterioDto;
import com.atividade1.ministerio.dto.DefaultResponseDto;
import com.atividade1.ministerio.dto.ministerio.UpdateMinisterioDto;
import com.atividade1.ministerio.models.Ministerio;
import com.atividade1.ministerio.services.MinisterioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/ministerio/")
public class MinisterioController {
    private final MinisterioService _ministerioService;

    public MinisterioController() {
        _ministerioService = new MinisterioService();
    }

    @GetMapping("list")
    public ResponseEntity<DefaultResponseDto<List<Ministerio>>> listAll() {
        return ResponseEntity.ok().body(new DefaultResponseDto<>(_ministerioService.ListAll(), "OK", "Success"));
    }

    @GetMapping("{id}")
    public ResponseEntity<DefaultResponseDto<Ministerio>> getById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(new DefaultResponseDto<>(_ministerioService.GetById(id), "OK", "Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DefaultResponseDto<>(null, "Error", e.getMessage()));
        }
    }

    @PostMapping("add")
    public ResponseEntity<DefaultResponseDto<Integer>> add(@RequestBody AddMinisterioDto request) {
        try {
            return ResponseEntity.ok().body(new DefaultResponseDto<Integer>(_ministerioService.Insert(request), "OK", "Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DefaultResponseDto<>(null, "Error", e.getMessage()));
        }
    }

    @PutMapping("update")
    public ResponseEntity<DefaultResponseDto<Integer>> update(@RequestBody UpdateMinisterioDto request) {
        try {
            return ResponseEntity.ok().body(new DefaultResponseDto<>(_ministerioService.Update(request), "OK", "Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DefaultResponseDto<>(null, "Error", e.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DefaultResponseDto<Integer>> delete(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(new DefaultResponseDto<>(_ministerioService.Delete(id), "OK", "Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DefaultResponseDto<>(null, "Error", e.getMessage()));
        }
    }
}
