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

    public Orcamento editar(Long id, Orcamento novoOrcamento) {
        Orcamento orcamento = orcamentoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        orcamento.setModeloVeiculo(novoOrcamento.getModeloVeiculo());
        orcamento.setMarcaVeiculo(novoOrcamento.getMarcaVeiculo());
        orcamento.setCorVeiculo(novoOrcamento.getCorVeiculo());
        orcamento.setPlacaVeiculo(novoOrcamento.getPlacaVeiculo());
        orcamento.setObservacao(novoOrcamento.getObservacao());
        orcamento.setPreco(novoOrcamento.getPreco());
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
