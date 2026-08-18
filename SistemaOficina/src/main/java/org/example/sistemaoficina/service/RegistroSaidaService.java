package org.example.sistemaoficina.service;

import org.example.sistemaoficina.entity.Material;
import org.example.sistemaoficina.entity.RegistroSaida;
import org.example.sistemaoficina.repository.RegistroSaidaRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class RegistroSaidaService {

    private final RegistroSaidaRepository registroSaidaRepository;

    public RegistroSaidaService(RegistroSaidaRepository registroSaidaRepository) {
        this.registroSaidaRepository = registroSaidaRepository;
    }

    public RegistroSaida criarRegistroSaida(Material material, Double quantidadeGasta, LocalDateTime dataSaida) {
        RegistroSaida novoRegistroSaida = new RegistroSaida();
        novoRegistroSaida.setMaterial(material);
        novoRegistroSaida.setQuantidadeGasta(quantidadeGasta);
        novoRegistroSaida.setDataSaida(dataSaida);
        return registroSaidaRepository.save(novoRegistroSaida);
    }

    public List<RegistroSaida> listarTodos() {
        return registroSaidaRepository.findAll();
    }

    public RegistroSaida listarPorId(Long id) {
        return registroSaidaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public RegistroSaida editar(Long id, RegistroSaida novoRegistroSaida) {
        RegistroSaida registroSaida = registroSaidaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        registroSaida.setMaterial(novoRegistroSaida.getMaterial());
        registroSaida.setQuantidadeGasta(novoRegistroSaida.getQuantidadeGasta());
        registroSaida.setDataSaida(novoRegistroSaida.getDataSaida());
        return registroSaidaRepository.save(registroSaida);
    }

    public void excluir(Long id) {
        if (registroSaidaRepository.existsById(id)) {
            registroSaidaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
