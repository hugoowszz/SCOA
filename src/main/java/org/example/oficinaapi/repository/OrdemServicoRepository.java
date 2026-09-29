package org.example.oficinaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServico extends JpaRepository<OrdemServico, Long> {
}
