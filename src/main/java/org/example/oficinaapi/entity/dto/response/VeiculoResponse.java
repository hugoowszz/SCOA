package org.example.oficinaapi.entity.dto.response;

import org.example.oficinaapi.entity.Veiculo;

public record VeiculoResponse(
        Long id,
        String placa,
        String marca,
        String modelo,
        int ano,
        String cor,
        Integer km,
        Long clienteId,
        String clienteNome
) {
    public static VeiculoResponse from(Veiculo v) {
        return new VeiculoResponse(
                v.getId(),
                v.getPlaca(),
                v.getMarca(),
                v.getModelo(),
                v.getAno(),
                v.getCor(),
                v.getKm(),
                v.getCliente() != null ? v.getCliente().getId() : null,
                v.getCliente() != null ? v.getCliente().getNome() : null
        );
    }
}
