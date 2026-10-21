package org.example.oficinaapi.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.oficinaapi.entity.Material;
import org.example.oficinaapi.entity.Orcamento;
import org.example.oficinaapi.entity.Veiculo;
import org.example.oficinaapi.entity.dto.request.OrcamentoRequest;
import org.example.oficinaapi.entity.dto.response.OrcamentoResponse;
import org.example.oficinaapi.repository.OrcamentoRepository;
import org.example.oficinaapi.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrcamentoService {

    private final OrcamentoRepository orcamentoRepository;
    private final VeiculoRepository veiculoRepository;

    public OrcamentoService(OrcamentoRepository orcamentoRepository,  VeiculoRepository veiculoRepository) {
        this.orcamentoRepository = orcamentoRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public OrcamentoResponse criarOrcamento(OrcamentoRequest request) {
        Orcamento orcamento = new Orcamento();
        Veiculo veiculo = veiculoRepository.findById(request.veiculoId())
                .orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado"));
        orcamento.setVeiculo(veiculo);
        orcamento.setObservacao(request.observacao());
        orcamento.setPreco(request.preco());
        Orcamento salvo = orcamentoRepository.save(orcamento);
        return OrcamentoResponse.from(salvo);
    }

    public List<OrcamentoResponse> buscarTodos() {
        return orcamentoRepository.findAll()
                .stream()
                .map(OrcamentoResponse::from)
                .toList();
    }

    public OrcamentoResponse buscarPorId(Long id) {
        Orcamento orcamento = orcamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orçamento não encontrado"));
        return OrcamentoResponse.from(orcamento);
    }

    public OrcamentoResponse atualizar(Long id, OrcamentoRequest request) {
        Orcamento orcamento = orcamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orçamento não encontrado"));
        Veiculo veiculo = veiculoRepository.findById(request.veiculoId())
                .orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado"));
        orcamento.setVeiculo(veiculo);
        orcamento.setObservacao(request.observacao());
        orcamento.setPreco(request.preco());
        Orcamento salvo = orcamentoRepository.save(orcamento);
        return OrcamentoResponse.from(salvo);
    }

    public void excluir(Long id) {
        Orcamento orcamento = orcamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orçamento não encontrado"));
        orcamentoRepository.delete(orcamento);
    }
}
