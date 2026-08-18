package org.example.sistemaoficina.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.sistemaoficina.entity.Orcamento;
import org.example.sistemaoficina.repository.OrcamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrcamentoService {

    private final OrcamentoRepository orcamentoRepository;

    public OrcamentoService(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }

    public Orcamento criarOrcamento(String modeloVeiculo, String marcaVeiculo, String corVeiculo, String placaVeiculo, String observacao, Double preco) {
        Orcamento novoOrcamento = new Orcamento();
        novoOrcamento.setModeloVeiculo(modeloVeiculo);
        novoOrcamento.setMarcaVeiculo(marcaVeiculo);
        novoOrcamento.setCorVeiculo(corVeiculo);
        novoOrcamento.setPlacaVeiculo(placaVeiculo);
        novoOrcamento.setObservacao(observacao);
        novoOrcamento.setPreco(preco);
        return orcamentoRepository.save(novoOrcamento);
    }

    public List<Orcamento> listarTodos() {
        return orcamentoRepository.findAll();
    }

    public Orcamento listarPorId(Long id) {
        return orcamentoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Orcamento editar(Long id, String modeloVeiculo, String marcaVeiculo, String corVeiculo, String placaVeiculo, String observacao, Double preco) {
        Orcamento orcamento = orcamentoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        orcamento.setModeloVeiculo(modeloVeiculo);
        orcamento.setMarcaVeiculo(marcaVeiculo);
        orcamento.setCorVeiculo(corVeiculo);
        orcamento.setPlacaVeiculo(placaVeiculo);
        orcamento.setObservacao(observacao);
        orcamento.setPreco(preco);
        return orcamentoRepository.save(orcamento);
    }

    public void excluir(Long id) {
        if (orcamentoRepository.existsById(id)) {
            orcamentoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
