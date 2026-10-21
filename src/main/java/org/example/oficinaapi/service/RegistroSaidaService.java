package org.example.oficinaapi.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.oficinaapi.entity.Material;
import org.example.oficinaapi.entity.RegistroSaida;
import org.example.oficinaapi.entity.dto.request.RegistroSaidaRequest;
import org.example.oficinaapi.entity.dto.response.RegistroSaidaResponse;
import org.example.oficinaapi.repository.MaterialRepository;
import org.example.oficinaapi.repository.RegistroSaidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroSaidaService {

    private final RegistroSaidaRepository registroSaidaRepository;
    private final MaterialRepository materialRepository;

    public RegistroSaidaService(
            RegistroSaidaRepository registroSaidaRepository,
            MaterialRepository materialRepository
    ) {
        this.registroSaidaRepository = registroSaidaRepository;
        this.materialRepository = materialRepository;
    }

    public RegistroSaidaResponse criarRegistroSaida(RegistroSaidaRequest request) {
        Material material = materialRepository.findById(request.materialId())
                .orElseThrow(() -> new EntityNotFoundException("Material não encontrado"));

        RegistroSaida registroSaida = new RegistroSaida();
        registroSaida.setMaterial(material);
        registroSaida.setQuantidadeGasta(request.quantidadeGasta());
        registroSaida.setDataSaida(request.dataSaida());

        RegistroSaida salvo = registroSaidaRepository.save(registroSaida);

        material.setQuantidade(material.getQuantidade() - registroSaida.getQuantidadeGasta());

        materialRepository.save(material);
        return RegistroSaidaResponse.from(salvo);
    }

    public List<RegistroSaidaResponse> buscarTodos() {
        return registroSaidaRepository.findAll()
                .stream()
                .map(RegistroSaidaResponse::from)
                .toList();
    }

    public RegistroSaidaResponse buscarPorId(Long id) {
        RegistroSaida registroSaida = registroSaidaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de saída não encontrado"));
        return RegistroSaidaResponse.from(registroSaida);
    }

    public RegistroSaidaResponse atualizar(Long id, RegistroSaidaRequest request) {
        RegistroSaida registroSaida = registroSaidaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de saída não encontrado"));
        Material material = materialRepository.findById(request.materialId())
                .orElseThrow(() -> new EntityNotFoundException("Material não encontrado"));

        registroSaida.setMaterial(material);
        registroSaida.setQuantidadeGasta(request.quantidadeGasta());
        registroSaida.setDataSaida(request.dataSaida());

        RegistroSaida salvo = registroSaidaRepository.save(registroSaida);
        return RegistroSaidaResponse.from(salvo);
    }

    public void excluir(Long id) {
        RegistroSaida registroSaida = registroSaidaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de saída não encontrado"));
        registroSaidaRepository.delete(registroSaida);
    }
}
