package org.example.sistemaoficina.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orcamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String modeloVeiculo;

    @NotBlank
    private String marcaVeiculo;

    @NotBlank
    private String corVeiculo;

    @NotBlank
    @Size(max = 7)
    private String placaVeiculo;

    private String observacao;

    @NotNull
    private Double preco;
}
