package org.example.oficinaapi.entity.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record OrcamentoRequest(
        Long veiculoId,
        String observacao,
        @NotNull @Positive @Digits(integer = 10, fraction = 2) BigDecimal preco
) {}
