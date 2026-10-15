package org.example.oficinaapi.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record VeiculoRequest(
        @NotBlank @Size(max = 20) String placa,
        @NotBlank @Size(max = 100) String marca,
        @NotBlank @Size(max = 100) String modelo,
        @NotNull Integer ano,
        @NotBlank @Size(max = 50) String cor,
        @PositiveOrZero Integer km,
        @NotNull Long clienteId
) {}
