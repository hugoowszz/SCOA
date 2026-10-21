package org.example.oficinaapi.controller;

import jakarta.validation.Valid;
import org.example.oficinaapi.entity.dto.request.MaterialRequest;
import org.example.oficinaapi.entity.dto.response.MaterialResponse;
import org.example.oficinaapi.service.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiais")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping
    public ResponseEntity<MaterialResponse> criarMaterial(@RequestBody @Valid MaterialRequest request) {
        MaterialResponse response = materialService.criarMaterial(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MaterialResponse>> buscarTodos() {
        return ResponseEntity.ok(materialService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialResponse> atualizar(@PathVariable Long id, @RequestBody @Valid MaterialRequest request) {
        MaterialResponse response = materialService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        materialService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
