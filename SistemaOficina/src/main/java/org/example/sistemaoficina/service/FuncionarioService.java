package org.example.sistemaoficina.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.sistemaoficina.entity.Funcionario;
import org.example.sistemaoficina.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Funcionario listarPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Funcionario editar(Long id, String nome, String telefone) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        funcionario.setNome(nome);
        funcionario.setTelefone(telefone);
        return funcionarioRepository.save(funcionario);
    }

    public void excluir(Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
