package org.example.oficinaapi.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequest(
        @NotBlank @Size(max = 200) String nome,
        @Size(max = 20) String documento,
        @NotBlank @Size(max = 200) String telefone,
        @Size(max = 200) String email,
        @Size(max = 255) String endereco
) {}
