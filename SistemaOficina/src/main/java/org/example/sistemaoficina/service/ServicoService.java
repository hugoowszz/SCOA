package org.example.sistemaoficina.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.sistemaoficina.entity.Funcionario;
import org.example.sistemaoficina.entity.Orcamento;
import org.example.sistemaoficina.entity.Servico;
import org.example.sistemaoficina.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public Servico criarServico(Orcamento orcamento, Funcionario funcionario, String nomeCliente, String contatoCliente, String status, LocalDateTime dataCriacao, LocalDateTime dataFim, String metodoPagamento) {
        Servico novoServico = new Servico();
        novoServico.setOrcamento(orcamento);
        novoServico.setFuncionario(funcionario);
        novoServico.setNomeCliente(nomeCliente);
        novoServico.setContatoCliente(contatoCliente);
        novoServico.setStatus(status);
        novoServico.setDataCriacao(dataCriacao);
        novoServico.setDataFim(dataFim);
        novoServico.setMetodoPagamento(metodoPagamento);
        return servicoRepository.save(novoServico);
    }

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public Servico listarPorId(Long id) {
        return servicoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Servico editar(Long id, Orcamento orcamento, Funcionario funcionario, Servico novoServico) {
        Servico servico = servicoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        servico.setOrcamento(orcamento);
        servico.setFuncionario(funcionario);
        servico.setNomeCliente(novoServico.getNomeCliente());
        servico.setContatoCliente(novoServico.getContatoCliente());
        servico.setStatus(novoServico.getStatus());
        servico.setDataCriacao(novoServico.getDataCriacao());
        servico.setDataFim(novoServico.getDataFim());
        servico.setMetodoPagamento(novoServico.getMetodoPagamento());
        return servicoRepository.save(servico);
    }

    public void excluir(Long id) {
        if (servicoRepository.existsById(id)) {
            servicoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
