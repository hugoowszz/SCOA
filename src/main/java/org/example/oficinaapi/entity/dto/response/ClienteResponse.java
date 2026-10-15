package org.example.oficinaapi.entity.dto.response;

import org.example.oficinaapi.entity.Cliente;

public record ClienteResponse(
        Long id,
        String nome,
        String documento,
        String telefone,
        String email,
        String endereco
) {
    public static ClienteResponse from(Cliente c) {
        return new ClienteResponse(
                c.getId(),
                c.getNome(),
                c.getDocumento(),
                c.getTelefone(),
                c.getEmail(),
                c.getEndereco()
        );
    }
}
