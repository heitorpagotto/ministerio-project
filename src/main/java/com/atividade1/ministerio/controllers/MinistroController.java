package com.atividade1.ministerio.controllers;

import com.atividade1.ministerio.dao.MinistroDao;
import com.atividade1.ministerio.dto.DefaultResponseDto;
import com.atividade1.ministerio.dto.ministro.AddMinistroDto;
import com.atividade1.ministerio.dto.ministro.UpdateMinistroDto;
import com.atividade1.ministerio.models.Ministro;
import com.atividade1.ministerio.services.MinistroService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/ministro/")
public class MinistroController {
    private final MinistroService _service;

    public MinistroController() {
        _service = new MinistroService();
    }

    @GetMapping("list")
    public ResponseEntity<DefaultResponseDto<List<Ministro>>> getAll() {
        return ResponseEntity.ok().body(new DefaultResponseDto<>(_service.ListAll(), "OK", "Success"));
    }

    @GetMapping("{id}")
    public ResponseEntity<DefaultResponseDto<Ministro>> getById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(new DefaultResponseDto<>(_service.GetById(id), "OK", "Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DefaultResponseDto<>(null, "Error", e.getMessage()));
        }
    }

    @PostMapping("add")
    public ResponseEntity<DefaultResponseDto<Integer>> add(@RequestBody AddMinistroDto request) {
        try {
            return ResponseEntity.ok().body(new DefaultResponseDto<>(_service.Insert(request), "OK", "Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DefaultResponseDto<>(null, "Error", e.getMessage()));
        }
    }

    @PutMapping("update")
    public ResponseEntity<DefaultResponseDto<Integer>> add(@RequestBody UpdateMinistroDto request) {
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
