package org.example.sistemaoficina.service;

import org.example.sistemaoficina.entity.Orcamento;
import org.example.sistemaoficina.repository.OrcamentoRepository;
import org.springframework.stereotype.Service;

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
}
