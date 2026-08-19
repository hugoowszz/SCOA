package org.example.sistemaoficina.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.sistemaoficina.entity.Orcamento;
import org.example.sistemaoficina.repository.OrcamentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrcamentoServiceTest {

    @InjectMocks
    private OrcamentoService orcamentoService;

    @Mock
    private OrcamentoRepository orcamentoRepository;

    @Test
    void deveCriarOrcamento() {
        when(orcamentoRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Orcamento orcamento = orcamentoService.criarOrcamento("Gol", "VW", "Branco", "ABC1234", "Pneu", 150.0);

        Assertions.assertEquals("Gol", orcamento.getModeloVeiculo());
        Assertions.assertEquals(150.0, orcamento.getPreco());
        verify(orcamentoRepository).save(any());
    }

    @Test
    void deveListarOrcamentos() {
        when(orcamentoRepository.findAll()).thenReturn(List.of(new Orcamento(), new Orcamento()));

        List<Orcamento> orcamentos = orcamentoService.listarTodos();

        Assertions.assertEquals(2, orcamentos.size());
    }

    @Test
    void deveBuscarOrcamentoPorId() {
        Orcamento mock = new Orcamento();
        mock.setId(1L);
        mock.setModeloVeiculo("Civic");
        when(orcamentoRepository.findById(1L)).thenReturn(Optional.of(mock));

        Orcamento encontrado = orcamentoService.listarPorId(1L);

        Assertions.assertNotNull(encontrado);
        Assertions.assertEquals("Civic", encontrado.getModeloVeiculo());
    }

    @Test
    void deveLancarExcecaoAoBuscarOrcamentoInexistente() {
        when(orcamentoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orcamentoService.listarPorId(99L));
    }

    @Test
    void deveEditarOrcamento() {
        Orcamento antigo = new Orcamento();
        antigo.setId(1L);
        antigo.setModeloVeiculo("Gol");

        Orcamento novo = new Orcamento();
        novo.setModeloVeiculo("Polo");
        novo.setPreco(200.0);

        when(orcamentoRepository.findById(1L)).thenReturn(Optional.of(antigo));
        when(orcamentoRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Orcamento atualizado = orcamentoService.editar(1L, novo);

        Assertions.assertEquals("Polo", atualizado.getModeloVeiculo());
        Assertions.assertEquals(200.0, atualizado.getPreco());
    }

    @Test
    void deveExcluirOrcamento() {
        when(orcamentoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(orcamentoRepository).deleteById(1L);

        orcamentoService.excluir(1L);

        verify(orcamentoRepository).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoExcluirOrcamentoInexistente() {
        when(orcamentoRepository.existsById(99L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> orcamentoService.excluir(99L));
        verify(orcamentoRepository, never()).deleteById(anyLong());
    }
}
