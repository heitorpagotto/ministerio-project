package com.atividade1.ministerio.controllers;

//TODO: Adicionar Secretaria controller

import com.atividade1.ministerio.dto.DefaultResponseDto;
import com.atividade1.ministerio.dto.secretaria.AddSecretariaDto;
import com.atividade1.ministerio.dto.secretaria.UpdateSecretariaDto;
import com.atividade1.ministerio.models.Secretaria;
import com.atividade1.ministerio.services.SecretariaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/secretaria/")
public class SecretariaController {
    private final SecretariaService _service;

    public SecretariaController() {
        _service = new SecretariaService();
    }

    @GetMapping("list")
    public ResponseEntity<DefaultResponseDto<List<Secretaria>>> getAll() {
        return ResponseEntity.ok().body(new DefaultResponseDto<>(_service.ListAll(), "OK", "Success"));
    }

    @GetMapping("ministerio/{id}")
    public ResponseEntity<DefaultResponseDto<List<Secretaria>>> getByMinisterioId(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(new DefaultResponseDto<>(_service.ListByMinisterioId(id), "OK", "Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DefaultResponseDto<>(null, "Error", e.getMessage()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<DefaultResponseDto<Secretaria>> getById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(new DefaultResponseDto<>(_service.GetById(id), "OK", "Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DefaultResponseDto<>(null, "Error", e.getMessage()));
        }
    }

    @PostMapping("add")
    public ResponseEntity<DefaultResponseDto<Integer>> add(@RequestBody AddSecretariaDto request) {
        try {
            return ResponseEntity.ok().body(new DefaultResponseDto<>(_service.Insert(request), "OK", "Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DefaultResponseDto<>(null, "Error", e.getMessage()));
        }
    }

    @PutMapping("update")
    public ResponseEntity<DefaultResponseDto<Integer>> add(@RequestBody UpdateSecretariaDto request) {
        try {
            return ResponseEntity.ok().body(new DefaultResponseDto<>(_service.Update(request), "OK", "Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DefaultResponseDto<>(null, "Error", e.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DefaultResponseDto<Integer>> delete(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(new DefaultResponseDto<>(_service.Delete(id), "OK", "Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DefaultResponseDto<>(null, "Error", e.getMessage()));
        }
    }
}
