package org.example.sistemaoficina.repository;

import org.example.sistemaoficina.entity.RegistroSaida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroSaidaRepository extends JpaRepository<RegistroSaida, Long> {
}
