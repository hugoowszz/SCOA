package org.example.sistemaoficina.controller;

import jakarta.validation.Valid;
import org.example.sistemaoficina.entity.Material;
import org.example.sistemaoficina.service.MaterialService;
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
    public ResponseEntity<Material> criar(@RequestBody @Valid Material material) {
        Material criado = materialService.criarMaterial(
                material.getNome(), material.getDescricao(), material.getUnidadeMedida(),
                material.getQuantidadeEstoque(), material.getEstoqueMinimo());
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Material>> listarTodos() {
        return ResponseEntity.ok(materialService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.listarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Material> editar(@PathVariable Long id, @RequestBody @Valid Material material) {
        return ResponseEntity.ok(materialService.editar(id, material));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        materialService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
