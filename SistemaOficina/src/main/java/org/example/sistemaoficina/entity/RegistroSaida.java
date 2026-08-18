package org.example.sistemaoficina.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "registros_saida")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroSaida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "material_id")
    @NotNull
    Material material;

    @NotNull
    Double quantidadeGasta;

    @NotNull
    LocalDateTime dataSaida;
}
