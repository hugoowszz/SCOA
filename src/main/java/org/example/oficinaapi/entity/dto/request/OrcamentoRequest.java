package org.example.oficinaapi.entity.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record OrcamentoRequest(
        @NotBlank @Size(max = 200) String modeloVeiculo,
        @NotBlank @Size(max = 200) String marcaVeiculo,
        @NotBlank @Size(max = 200) String corVeiculo,
        @NotNull Integer anoVeiculo,
        @NotBlank @Size(max = 200) String placaVeiculo,
        String observacao,
        @NotNull @Positive @Digits(integer = 10, fraction = 2) BigDecimal preco
) {}
