package org.example.oficinaapi.repository;

import org.example.oficinaapi.entity.RegistroSaida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroSaidaRepository extends JpaRepository<RegistroSaida, Long> {
}
