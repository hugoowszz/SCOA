package org.example.sistemaoficina.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "servicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "orcamento_id")
    @NotNull
    Orcamento orcamento;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    @NotNull
    Funcionario funcionario;

    @NotBlank
    String nomeCliente;

    @NotBlank
    String contatoCliente;

    @NotBlank
    @Pattern(regexp = "^(Não iniciado|Em andamento|Entregue)$")
    String status;

    @NotNull
    LocalDateTime dataCriacao;

    LocalDateTime dataFim;

    String metodoPagamento;
}
