package org.example.oficinaapi.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.oficinaapi.entity.Material;
import org.example.oficinaapi.entity.dto.request.MaterialRequest;
import org.example.oficinaapi.entity.dto.response.MaterialResponse;
import org.example.oficinaapi.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public MaterialResponse criarMaterial(MaterialRequest request) {
        Material material = new Material();
        material.setNome(request.nome());
        material.setUnidadeMedida(request.unidadeMedida());
        material.setQuantidade(request.quantidade());
        if (request.estoqueMinimo() != null) {
            material.setEstoqueMinimo(request.estoqueMinimo());
        }
        Material salvo = materialRepository.save(material);
        return MaterialResponse.from(salvo);
    }

    public List<MaterialResponse> buscarTodos() {
        return materialRepository.findAll()
                .stream()
                .map(MaterialResponse::from)
                .toList();
    }

    public MaterialResponse buscarPorId(Long id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Material não encontrado"));
        return MaterialResponse.from(material);
    }

    public MaterialResponse atualizar(Long id, MaterialRequest request) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Material não encontrado"));
        material.setNome(request.nome());
        material.setUnidadeMedida(request.unidadeMedida());
        material.setQuantidade(request.quantidade());
        if (request.estoqueMinimo() != null) {
            material.setEstoqueMinimo(request.estoqueMinimo());
        }
        Material salvo = materialRepository.save(material);
        return MaterialResponse.from(salvo);
    }

    public void excluir(Long id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Material não encontrado"));
        materialRepository.delete(material);
    }
}
