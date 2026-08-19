package org.example.sistemaoficina.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.sistemaoficina.entity.Funcionario;
import org.example.sistemaoficina.entity.Orcamento;
import org.example.sistemaoficina.entity.Servico;
import org.example.sistemaoficina.repository.ServicoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServicoServiceTest {

    @InjectMocks
    private ServicoService servicoService;

    @Mock
    private ServicoRepository servicoRepository;

    @Test
    void deveCriarServico() {
        Orcamento orcamento = new Orcamento();
        Funcionario funcionario = new Funcionario();
        
        when(servicoRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Servico servico = servicoService.criarServico(orcamento, funcionario, "Cliente Teste", "99999", "Não iniciado", LocalDateTime.now(), null, "PIX");

        Assertions.assertEquals("Cliente Teste", servico.getNomeCliente());
        Assertions.assertEquals("Não iniciado", servico.getStatus());
        verify(servicoRepository).save(any());
    }

    @Test
    void deveListarServicos() {
        when(servicoRepository.findAll()).thenReturn(List.of(new Servico(), new Servico()));

        List<Servico> servicos = servicoService.listarTodos();

        Assertions.assertEquals(2, servicos.size());
    }

    @Test
    void deveBuscarServicoPorId() {
        Servico mock = new Servico();
        mock.setId(1L);
        mock.setNomeCliente("Cliente 1");
        when(servicoRepository.findById(1L)).thenReturn(Optional.of(mock));

        Servico encontrado = servicoService.listarPorId(1L);

        Assertions.assertNotNull(encontrado);
        Assertions.assertEquals("Cliente 1", encontrado.getNomeCliente());
    }

    @Test
    void deveLancarExcecaoAoBuscarServicoInexistente() {
        when(servicoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> servicoService.listarPorId(99L));
    }

    @Test
    void deveEditarServico() {
        Servico antigo = new Servico();
        antigo.setId(1L);
        antigo.setNomeCliente("Cliente Velho");

        Servico novo = new Servico();
        novo.setNomeCliente("Cliente Novo");
        novo.setStatus("Em andamento");

        Orcamento o = new Orcamento();
        Funcionario f = new Funcionario();

        when(servicoRepository.findById(1L)).thenReturn(Optional.of(antigo));
        when(servicoRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Servico atualizado = servicoService.editar(1L, o, f, novo);

        Assertions.assertEquals("Cliente Novo", atualizado.getNomeCliente());
        Assertions.assertEquals("Em andamento", atualizado.getStatus());
    }

    @Test
    void deveExcluirServico() {
        when(servicoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(servicoRepository).deleteById(1L);

        servicoService.excluir(1L);

        verify(servicoRepository).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoExcluirServicoInexistente() {
        when(servicoRepository.existsById(99L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> servicoService.excluir(99L));
        verify(servicoRepository, never()).deleteById(anyLong());
    }
}
