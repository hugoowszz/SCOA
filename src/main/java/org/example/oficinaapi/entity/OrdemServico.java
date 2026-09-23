package org.example.oficinaapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @OneToOne
    @JoinColumn(name = "orcamento_id")
    private Orcamento orcamento_id;

    @NotBlank
    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario_id;

    @NotBlank
    private String nome_cliente;

    @NotBlank
    private String contato_cliente;

    private String status;

    @NotBlank
    private LocalDate data_criacao;

    private LocalDate data_entrega;

    private String metodo_pagamento;
}
