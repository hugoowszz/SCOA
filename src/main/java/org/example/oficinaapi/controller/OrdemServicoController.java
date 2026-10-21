package org.example.oficinaapi.controller;

import jakarta.validation.Valid;
import org.example.oficinaapi.entity.dto.request.OrdemServicoRequest;
import org.example.oficinaapi.entity.dto.response.OrdemServicoResponse;
import org.example.oficinaapi.service.OrdemServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping
    public ResponseEntity<OrdemServicoResponse> criarOrdemServico(@RequestBody @Valid OrdemServicoRequest request) {
        OrdemServicoResponse response = ordemServicoService.criarOrdemServico(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrdemServicoResponse>> buscarTodos() {
        return ResponseEntity.ok(ordemServicoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordemServicoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemServicoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid OrdemServicoRequest request) {
        OrdemServicoResponse response = ordemServicoService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        ordemServicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
