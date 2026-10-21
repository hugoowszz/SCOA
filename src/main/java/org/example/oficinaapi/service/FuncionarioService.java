package org.example.oficinaapi.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.oficinaapi.entity.Funcionario;
import org.example.oficinaapi.entity.dto.request.FuncionarioRequest;
import org.example.oficinaapi.entity.dto.response.FuncionarioResponse;
import org.example.oficinaapi.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public FuncionarioResponse criarFuncionario(FuncionarioRequest request) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(request.nome());
        funcionario.setTelefone(request.telefone());
        Funcionario salvo = funcionarioRepository.save(funcionario);
        return FuncionarioResponse.from(salvo);
    }

    public List<FuncionarioResponse> buscarTodos() {
        return funcionarioRepository.findAll()
                .stream()
                .map(FuncionarioResponse::from)
                .toList();
    }

    public FuncionarioResponse buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));
        return FuncionarioResponse.from(funcionario);
    }

    public FuncionarioResponse atualizar(Long id, FuncionarioRequest request) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));
        funcionario.setNome(request.nome());
        funcionario.setTelefone(request.telefone());
        Funcionario salvo = funcionarioRepository.save(funcionario);
        return FuncionarioResponse.from(salvo);
    }

    public void excluir(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));
        funcionarioRepository.delete(funcionario);
    }
}
