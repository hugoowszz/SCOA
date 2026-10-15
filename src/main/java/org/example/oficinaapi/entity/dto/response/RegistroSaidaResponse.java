package org.example.oficinaapi.entity.dto.response;

import org.example.oficinaapi.entity.Material;
import org.example.oficinaapi.entity.RegistroSaida;

import java.time.LocalDate;

public record RegistroSaidaResponse(
        Long id, Long materialId, String materialNome, Integer quantidadeGasta, LocalDate dataSaida
) {
    public static RegistroSaidaResponse from(RegistroSaida r) {
        return new RegistroSaidaResponse(r.getId(), r.getMaterial().getId(),
                r.getMaterial().getNome(), r.getQuantidadeGasta(), r.getDataSaida());
    }
}
