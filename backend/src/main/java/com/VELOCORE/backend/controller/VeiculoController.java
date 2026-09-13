package com.VELOCORE.backend.controller;

import com.VELOCORE.backend.dtos.VeiculoRequest;
import com.VELOCORE.backend.dtos.VeiculoResponse;
import com.VELOCORE.backend.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<VeiculoResponse> cadastrar(@Valid @RequestBody VeiculoRequest request) {
        VeiculoResponse response = veiculoService.cadastrar(request);

        URI localizar = URI.create("/api/veiculos/" + response.id());

        return ResponseEntity.created(localizar).body(response);
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponse>> listar() {
        return ResponseEntity.ok(veiculoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(veiculoService.buscarPorId(id));
    }
}
