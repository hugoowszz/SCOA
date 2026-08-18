package org.example.sistemaoficina.controller;

import jakarta.validation.Valid;
import org.example.sistemaoficina.entity.RegistroSaida;
import org.example.sistemaoficina.service.RegistroSaidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros-saida")
public class RegistroSaidaController {

    private final RegistroSaidaService registroSaidaService;

    public RegistroSaidaController(RegistroSaidaService registroSaidaService) {
        this.registroSaidaService = registroSaidaService;
    }

    @PostMapping
    public ResponseEntity<RegistroSaida> criar(@RequestBody @Valid RegistroSaida registroSaida) {
        RegistroSaida criado = registroSaidaService.criarRegistroSaida(
                registroSaida.getMaterial(), registroSaida.getQuantidadeGasta(),
                registroSaida.getDataSaida());
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<RegistroSaida>> listarTodos() {
        return ResponseEntity.ok(registroSaidaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroSaida> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(registroSaidaService.listarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroSaida> editar(@PathVariable Long id, @RequestBody @Valid RegistroSaida registroSaida) {
        return ResponseEntity.ok(registroSaidaService.editar(id, registroSaida));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        registroSaidaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
