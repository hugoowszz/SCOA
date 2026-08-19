package org.example.sistemaoficina.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.sistemaoficina.entity.Funcionario;
import org.example.sistemaoficina.repository.FuncionarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTest {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Test
    void deveCriarFuncionario() {
        when(funcionarioRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Funcionario funcionario = funcionarioService.criarFuncionario("Fulano", "00000000000");

        Assertions.assertEquals("Fulano", funcionario.getNome());
        Assertions.assertEquals("00000000000", funcionario.getTelefone());
        verify(funcionarioRepository).save(any());
    }

    @Test
    void deveListarFuncionarios() {
        when(funcionarioRepository.findAll()).thenReturn(List.of(new Funcionario(), new Funcionario()));

        List<Funcionario> funcionarios = funcionarioService.listarTodos();

        Assertions.assertNotNull(funcionarios);
        Assertions.assertEquals(2, funcionarios.size());
    }

    @Test
    void deveBuscarFuncionarioPorId() {
        Funcionario mockFuncionario = new Funcionario();
        mockFuncionario.setId(1L);
        mockFuncionario.setNome("Ciclano");
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(mockFuncionario));

        Funcionario funcionario = funcionarioService.listarPorId(1L);

        Assertions.assertNotNull(funcionario);
        Assertions.assertEquals("Ciclano", funcionario.getNome());
    }

    @Test
    void deveLancarExcecaoAoBuscarFuncionarioInexistente() {
        when(funcionarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> funcionarioService.listarPorId(99L));
    }

    @Test
    void deveEditarFuncionario() {
        Funcionario antigo = new Funcionario();
        antigo.setId(1L);
        antigo.setNome("Joao");
        
        Funcionario novoData = new Funcionario();
        novoData.setNome("Maria");
        novoData.setTelefone("119999");

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(antigo));
        when(funcionarioRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Funcionario atualizado = funcionarioService.editar(1L, novoData);

        Assertions.assertEquals("Maria", atualizado.getNome());
        Assertions.assertEquals("119999", atualizado.getTelefone());
    }

    @Test
    void deveExcluirFuncionario() {
        when(funcionarioRepository.existsById(1L)).thenReturn(true);
        doNothing().when(funcionarioRepository).deleteById(1L);

        funcionarioService.excluir(1L);

        verify(funcionarioRepository).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoExcluirFuncionarioInexistente() {
        when(funcionarioRepository.existsById(99L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> funcionarioService.excluir(99L));
        verify(funcionarioRepository, never()).deleteById(anyLong());
    }
}
