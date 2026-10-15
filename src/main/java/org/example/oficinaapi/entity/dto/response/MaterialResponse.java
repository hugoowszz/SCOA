package org.example.oficinaapi.entity.dto.response;

import org.example.oficinaapi.entity.Material;

public record MaterialResponse(
        Long id, String nome, Integer quantidade
) {
    public static MaterialResponse from(Material m) {
        return new MaterialResponse(m.getId(), m.getNome(), m.getQuantidade());
    }
}