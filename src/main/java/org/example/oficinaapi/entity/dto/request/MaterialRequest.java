package org.example.oficinaapi.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record MaterialRequest(
        @NotBlank @Size(max = 200) String nome,
        @NotBlank @Size(max = 20) String unidadeMedida,
        @NotNull @PositiveOrZero Integer quantidade,
        @PositiveOrZero Integer estoqueMinimo
) {}
