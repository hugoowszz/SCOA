package org.example.sistemaoficina.service;

import org.example.sistemaoficina.entity.Material;
import org.example.sistemaoficina.repository.MaterialRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public Material criarMaterial(String nome, String descricao , String unidadeMedida, Double quantidadeEstoque, Double estoqueMinimo) {
        Material novoMaterial = new Material();
        novoMaterial.setNome(nome);
        novoMaterial.setDescricao(descricao);
        novoMaterial.setUnidadeMedida(unidadeMedida);
        novoMaterial.setQuantidadeEstoque(quantidadeEstoque);
        novoMaterial.setEstoqueMinimo(estoqueMinimo);
        return materialRepository.save(novoMaterial);
    }

    public List<Material> listarTodos() {
        return materialRepository.findAll();
    }

    public Material listarPorId(Long id) {
        return materialRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Material editar(Long id, Material novoMaterial) {
        Material material = materialRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        material.setNome(novoMaterial.getNome());
        material.setUnidadeMedida(novoMaterial.getUnidadeMedida());
        material.setQuantidadeEstoque(novoMaterial.getQuantidadeEstoque());
        material.setEstoqueMinimo(novoMaterial.getEstoqueMinimo());
        return materialRepository.save(material);
    }

    public void excluir(Long id) {
        if (materialRepository.existsById(id)) {
            materialRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
