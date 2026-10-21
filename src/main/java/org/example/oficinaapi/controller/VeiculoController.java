package org.example.oficinaapi.controller;

import jakarta.validation.Valid;
import org.example.oficinaapi.entity.dto.request.VeiculoRequest;
import org.example.oficinaapi.entity.dto.response.VeiculoResponse;
import org.example.oficinaapi.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<VeiculoResponse> criarVeiculo(@RequestBody @Valid VeiculoRequest request) {
        VeiculoResponse response = veiculoService.criarVeiculo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponse>> buscarTodos() {
        return ResponseEntity.ok(veiculoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid VeiculoRequest request) {
        VeiculoResponse response = veiculoService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        veiculoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
