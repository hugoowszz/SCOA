package org.example.sistemaoficina.controller;

import jakarta.validation.Valid;
import org.example.sistemaoficina.entity.Orcamento;
import org.example.sistemaoficina.service.OrcamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {

    private final OrcamentoService orcamentoService;

    public OrcamentoController(OrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
    }

    @PostMapping
    public ResponseEntity<Orcamento> criar(@RequestBody @Valid Orcamento orcamento) {
        Orcamento criado = orcamentoService.criarOrcamento(
                orcamento.getModeloVeiculo(), orcamento.getMarcaVeiculo(),
                orcamento.getCorVeiculo(), orcamento.getPlacaVeiculo(),
                orcamento.getObservacao(), orcamento.getPreco());
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Orcamento>> listarTodos() {
        return ResponseEntity.ok(orcamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orcamento> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(orcamentoService.listarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orcamento> editar(@PathVariable Long id, @RequestBody @Valid Orcamento orcamento) {
        return ResponseEntity.ok(orcamentoService.editar(id, orcamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        orcamentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
