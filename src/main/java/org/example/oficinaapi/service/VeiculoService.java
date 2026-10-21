package org.example.oficinaapi.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.oficinaapi.entity.Cliente;
import org.example.oficinaapi.entity.Veiculo;
import org.example.oficinaapi.entity.dto.request.VeiculoRequest;
import org.example.oficinaapi.entity.dto.response.VeiculoResponse;
import org.example.oficinaapi.repository.ClienteRepository;
import org.example.oficinaapi.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;

    public VeiculoService(VeiculoRepository veiculoRepository, ClienteRepository clienteRepository) {
        this.veiculoRepository = veiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    public VeiculoResponse criarVeiculo(VeiculoRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(request.placa());
        veiculo.setMarca(request.marca());
        veiculo.setModelo(request.modelo());
        veiculo.setAno(request.ano());
        veiculo.setCor(request.cor());
        veiculo.setKm(request.km());
        veiculo.setCliente(cliente);

        Veiculo salvo = veiculoRepository.save(veiculo);
        return VeiculoResponse.from(salvo);
    }

    public List<VeiculoResponse> buscarTodos() {
        return veiculoRepository.findAll()
                .stream()
                .map(VeiculoResponse::from)
                .toList();
    }

    public VeiculoResponse buscarPorId(Long id) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veículo não encontrado"));
        return VeiculoResponse.from(veiculo);
    }

    public VeiculoResponse atualizar(Long id, VeiculoRequest request) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veículo não encontrado"));
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        veiculo.setPlaca(request.placa());
        veiculo.setMarca(request.marca());
        veiculo.setModelo(request.modelo());
        veiculo.setAno(request.ano());
        veiculo.setCor(request.cor());
        veiculo.setKm(request.km());
        veiculo.setCliente(cliente);

        Veiculo salvo = veiculoRepository.save(veiculo);
        return VeiculoResponse.from(salvo);
    }

    public void excluir(Long id) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veículo não encontrado"));
        veiculoRepository.delete(veiculo);
    }
}
