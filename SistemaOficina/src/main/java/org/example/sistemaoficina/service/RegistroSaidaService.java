package org.example.sistemaoficina.service;

import org.example.sistemaoficina.entity.Material;
import org.example.sistemaoficina.entity.RegistroSaida;
import org.example.sistemaoficina.repository.MaterialRepository;
import org.example.sistemaoficina.repository.RegistroSaidaRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class RegistroSaidaService {

    private final RegistroSaidaRepository registroSaidaRepository;

    private final MaterialService materialService;

    public RegistroSaidaService(RegistroSaidaRepository registroSaidaRepository, MaterialRepository materialRepository, MaterialService materialService) {
        this.registroSaidaRepository = registroSaidaRepository;
        this.materialService = materialService;
    }

    public RegistroSaida criarRegistroSaida(Material material, Double quantidadeGasta, LocalDateTime dataSaida) {
        RegistroSaida novoRegistroSaida = new RegistroSaida();
        novoRegistroSaida.setMaterial(material);
        novoRegistroSaida.setQuantidadeGasta(quantidadeGasta);
        novoRegistroSaida.setDataSaida(dataSaida);

        Material materialUsado = materialService.listarPorId(material.getId());

        materialUsado.setQuantidadeEstoque(material.getQuantidadeEstoque() - quantidadeGasta);
        materialService.editar(materialUsado.getId(), materialUsado);

        if(materialUsado.getQuantidadeEstoque() < materialUsado.getEstoqueMinimo()) {
            novoRegistroSaida.setAlertaEstoque("Atenção: O estoque caiu a baixo do mínimo!");
        }

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
