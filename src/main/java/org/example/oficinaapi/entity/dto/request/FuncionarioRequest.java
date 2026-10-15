package org.example.oficinaapi.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FuncionarioRequest(
        @NotBlank @Size(max = 200) String nome,
        @NotBlank @Size(max = 200) String telefone
) {}
