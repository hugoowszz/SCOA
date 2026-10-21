package org.example.oficinaapi.entity.dto.response;

import org.example.oficinaapi.entity.Orcamento;

import java.math.BigDecimal;

public record OrcamentoResponse(
        Long id, String modeloVeiculo, String marcaVeiculo, BigDecimal preco
) {
    public static OrcamentoResponse from(Orcamento o) {
        return new OrcamentoResponse(o.getId(), o.getVeiculo().getModelo(), o.getVeiculo().getMarca(), o.getPreco());
    }
}
