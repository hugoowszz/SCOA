package org.example.oficinaapi.entity.dto.response;

import org.example.oficinaapi.entity.Funcionario;

public record FuncionarioResponse(Long id, String nome, String telefone) {

    public static FuncionarioResponse from(Funcionario f) {
        return new FuncionarioResponse(f.getId(), f.getNome(), f.getTelefone());
    }
}