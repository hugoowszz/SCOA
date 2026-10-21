package org.example.oficinaapi.controller;

import jakarta.validation.Valid;
import org.example.oficinaapi.entity.dto.request.FuncionarioRequest;
import org.example.oficinaapi.entity.dto.response.FuncionarioResponse;
import org.example.oficinaapi.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponse> criarFuncionario(@RequestBody @Valid FuncionarioRequest request) {
        FuncionarioResponse response = funcionarioService.criarFuncionario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponse>> buscarTodos() {
        return ResponseEntity.ok(funcionarioService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(funcionarioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> atualizar(@PathVariable Long id, @RequestBody @Valid FuncionarioRequest request) {
        FuncionarioResponse response = funcionarioService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        funcionarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
