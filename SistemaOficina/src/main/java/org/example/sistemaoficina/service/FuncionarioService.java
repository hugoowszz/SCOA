package org.example.sistemaoficina.service;

import org.example.sistemaoficina.entity.Funcionario;
import org.example.sistemaoficina.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario criarFuncionario(String nome, String telefone) {
        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome(nome);
        novoFuncionario.setTelefone(telefone);
        return funcionarioRepository.save(novoFuncionario);
    }
}
