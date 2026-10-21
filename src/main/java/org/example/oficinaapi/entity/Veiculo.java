package org.example.oficinaapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;

    private String marca;

    private String modelo;

    private int ano;

    private String cor;

    private Integer km;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
