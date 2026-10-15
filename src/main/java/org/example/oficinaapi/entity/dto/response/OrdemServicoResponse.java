package org.example.oficinaapi.entity.dto.response;

import org.example.oficinaapi.entity.Orcamento;
import org.example.oficinaapi.entity.OrdemServico;

import java.time.LocalDate;

public record OrdemServicoResponse(
        Long id,
        Long orcamentoId, String nomeCliente, String status, LocalDate dataCriacao, String metodoPagamento
) {
    public static OrdemServicoResponse from(OrdemServico os) {
        return new OrdemServicoResponse(
                os.getId(), os.getOrcamento().getId(), os.getNomeCliente(), os.getStatus(), os.getDataCriacao(), os.getMetodoPagamento());
    }
}
