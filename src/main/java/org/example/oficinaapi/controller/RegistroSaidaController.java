package org.example.oficinaapi.controller;

import jakarta.validation.Valid;
import org.example.oficinaapi.entity.dto.request.RegistroSaidaRequest;
import org.example.oficinaapi.entity.dto.response.RegistroSaidaResponse;
import org.example.oficinaapi.service.RegistroSaidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros")
public class RegistroSaidaController {

    private final RegistroSaidaService registroSaidaService;

    public RegistroSaidaController(RegistroSaidaService registroSaidaService) {
        this.registroSaidaService = registroSaidaService;
    }

    @PostMapping
    public ResponseEntity<RegistroSaidaResponse> criarRegistroSaida(@RequestBody @Valid RegistroSaidaRequest request) {
        RegistroSaidaResponse response = registroSaidaService.criarRegistroSaida(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<RegistroSaidaResponse>> buscarTodos() {
        return ResponseEntity.ok(registroSaidaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroSaidaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(registroSaidaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroSaidaResponse> atualizar(@PathVariable Long id, @RequestBody @Valid RegistroSaidaRequest request) {
        RegistroSaidaResponse response = registroSaidaService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        registroSaidaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
