package org.example.sistemaoficina.service;

import org.example.sistemaoficina.entity.Funcionario;
import org.example.sistemaoficina.entity.Orcamento;
import org.example.sistemaoficina.entity.Servico;
import org.example.sistemaoficina.repository.ServicoRepository;
import org.springframework.stereotype.Service;

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
}
