package org.example.oficinaapi.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.oficinaapi.entity.Cliente;
import org.example.oficinaapi.entity.dto.request.ClienteRequest;
import org.example.oficinaapi.entity.dto.response.ClienteResponse;
import org.example.oficinaapi.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponse criarCliente(ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNome(request.nome());
        cliente.setDocumento(request.documento());
        cliente.setTelefone(request.telefone());
        cliente.setEmail(request.email());
        cliente.setEndereco(request.endereco());
        Cliente salvo = clienteRepository.save(cliente);
        return ClienteResponse.from(salvo);
    }

    public List<ClienteResponse> buscarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteResponse::from)
                .toList();
    }

    public ClienteResponse buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        return ClienteResponse.from(cliente);
    }

    public ClienteResponse atualizar(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        cliente.setNome(request.nome());
        cliente.setDocumento(request.documento());
        cliente.setTelefone(request.telefone());
        cliente.setEmail(request.email());
        cliente.setEndereco(request.endereco());
        Cliente salvo = clienteRepository.save(cliente);
        return ClienteResponse.from(salvo);
    }

    public void excluir(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        clienteRepository.delete(cliente);
    }
}
