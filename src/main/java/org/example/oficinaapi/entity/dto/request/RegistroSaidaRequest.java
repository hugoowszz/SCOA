package org.example.oficinaapi.entity.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import org.example.oficinaapi.entity.Material;

import java.time.LocalDate;

public record RegistroSaidaRequest(
        @NotNull Long materialId,
        @NotNull @Positive Integer quantidadeGasta,
        @NotNull @PastOrPresent LocalDate dataSaida
) {}
