package org.example.sistemaoficina.controller;

import jakarta.validation.Valid;
import org.example.sistemaoficina.entity.Funcionario;
import org.example.sistemaoficina.service.FuncionarioService;
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
    public ResponseEntity<Funcionario> criar(@RequestBody @Valid Funcionario funcionario) {
        Funcionario criado = funcionarioService.criarFuncionario(funcionario.getNome(), funcionario.getTelefone());
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> listarTodos() {
        return ResponseEntity.ok(funcionarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(funcionarioService.listarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> editar(@PathVariable Long id, @RequestBody @Valid Funcionario funcionario) {
        return ResponseEntity.ok(funcionarioService.editar(id, funcionario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        funcionarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
