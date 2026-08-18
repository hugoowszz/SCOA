package org.example.sistemaoficina.controller;

import jakarta.validation.Valid;
import org.example.sistemaoficina.entity.Servico;
import org.example.sistemaoficina.service.ServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @PostMapping
    public ResponseEntity<Servico> criar(@RequestBody @Valid Servico servico) {
        Servico criado = servicoService.criarServico(
                servico.getOrcamento(), servico.getFuncionario(),
                servico.getNomeCliente(), servico.getContatoCliente(),
                servico.getStatus(), LocalDateTime.now(), servico.getDataFim(), servico.getMetodoPagamento());
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Servico>> listarTodos() {
        return ResponseEntity.ok(servicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicoService.listarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> editar(@PathVariable Long id, @RequestBody @Valid Servico servico) {
        return ResponseEntity.ok(servicoService.editar(id, servico.getOrcamento(), servico.getFuncionario(), servico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        servicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
