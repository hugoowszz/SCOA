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
    Long id;

    @NotBlank
    String modeloVeiculo;

    @NotBlank
    String marcaVeiculo;

    @NotBlank
    String corVeiculo;

    @NotBlank
    @Size(max = 7)
    String placaVeiculo;

    String observacao;

    @NotNull
    Double preco;
}
