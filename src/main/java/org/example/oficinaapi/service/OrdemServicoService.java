package org.example.oficinaapi.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.oficinaapi.entity.Funcionario;
import org.example.oficinaapi.entity.Orcamento;
import org.example.oficinaapi.entity.OrdemServico;
import org.example.oficinaapi.entity.dto.request.OrdemServicoRequest;
import org.example.oficinaapi.entity.dto.response.OrdemServicoResponse;
import org.example.oficinaapi.repository.FuncionarioRepository;
import org.example.oficinaapi.repository.OrcamentoRepository;
import org.example.oficinaapi.repository.OrdemServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final OrcamentoRepository orcamentoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public OrdemServicoService(
            OrdemServicoRepository ordemServicoRepository,
            OrcamentoRepository orcamentoRepository,
            FuncionarioRepository funcionarioRepository
    ) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.orcamentoRepository = orcamentoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public OrdemServicoResponse criarOrdemServico(OrdemServicoRequest request) {
        Orcamento orcamento = orcamentoRepository.findById(request.orcamentoId())
                .orElseThrow(() -> new EntityNotFoundException("Orçamento não encontrado"));
        Funcionario funcionario = funcionarioRepository.findById(request.funcionarioId())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setOrcamento(orcamento);
        ordemServico.setFuncionario(funcionario);
        ordemServico.setNomeCliente(request.nomeCliente());
        ordemServico.setContatoCliente(request.contatoCliente());
        ordemServico.setStatus(request.status());
        ordemServico.setDataCriacao(request.dataCriacao());
        ordemServico.setDataEntrega(request.dataEntrega());
        ordemServico.setMetodoPagamento(request.metodoPagamento());

        OrdemServico salvo = ordemServicoRepository.save(ordemServico);
        return OrdemServicoResponse.from(salvo);
    }

    public List<OrdemServicoResponse> buscarTodos() {
        return ordemServicoRepository.findAll()
                .stream()
                .map(OrdemServicoResponse::from)
                .toList();
    }

    public OrdemServicoResponse buscarPorId(Long id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada"));
        return OrdemServicoResponse.from(ordemServico);
    }

    public OrdemServicoResponse atualizar(Long id, OrdemServicoRequest request) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada"));
        Orcamento orcamento = orcamentoRepository.findById(request.orcamentoId())
                .orElseThrow(() -> new EntityNotFoundException("Orçamento não encontrado"));
        Funcionario funcionario = funcionarioRepository.findById(request.funcionarioId())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));

        ordemServico.setOrcamento(orcamento);
        ordemServico.setFuncionario(funcionario);
        ordemServico.setNomeCliente(request.nomeCliente());
        ordemServico.setContatoCliente(request.contatoCliente());
        ordemServico.setStatus(request.status());
        ordemServico.setDataCriacao(request.dataCriacao());
        ordemServico.setDataEntrega(request.dataEntrega());
        ordemServico.setMetodoPagamento(request.metodoPagamento());

        OrdemServico salvo = ordemServicoRepository.save(ordemServico);
        return OrdemServicoResponse.from(salvo);
    }

    public void excluir(Long id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada"));
        ordemServicoRepository.delete(ordemServico);
    }
}
