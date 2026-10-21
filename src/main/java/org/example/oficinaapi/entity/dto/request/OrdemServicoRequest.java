package org.example.oficinaapi.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.oficinaapi.entity.Funcionario;
import org.example.oficinaapi.entity.Orcamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrdemServicoRequest(
        @NotNull Long orcamentoId,
        @NotNull Long funcionarioId,
        @NotNull Long clienteId,
        String status,
        @NotNull LocalDate dataCriacao,
        LocalDate dataEntrega,
        @Size(max = 200) String metodoPagamento
) {}
