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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orcamento_id")
    @NotNull
    private Orcamento orcamento;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    @NotNull
    private Funcionario funcionario;

    @NotBlank
    private String nomeCliente;

    @NotBlank
    private String contatoCliente;

    @NotBlank
    @Pattern(regexp = "^(Não iniciado|Em andamento|Entregue)$")
    private String status;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataFim;

    private String metodoPagamento;
}
