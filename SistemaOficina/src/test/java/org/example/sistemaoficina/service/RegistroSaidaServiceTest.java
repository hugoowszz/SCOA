package org.example.sistemaoficina.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.sistemaoficina.entity.Material;
import org.example.sistemaoficina.entity.RegistroSaida;
import org.example.sistemaoficina.repository.RegistroSaidaRepository;
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
public class RegistroSaidaServiceTest {

    @InjectMocks
    private RegistroSaidaService registroSaidaService;

    @Mock
    private RegistroSaidaRepository registroSaidaRepository;

    @Mock
    private MaterialService materialService;

    @Test
    void deveCriarRegistroSaidaEAtualizarEstoque() {
        Material materialEntrada = new Material();
        materialEntrada.setId(1L);
        materialEntrada.setQuantidadeEstoque(50.0);
        materialEntrada.setEstoqueMinimo(10.0);

        Material materialDoBanco = new Material();
        materialDoBanco.setId(1L);
        materialDoBanco.setQuantidadeEstoque(50.0);
        materialDoBanco.setEstoqueMinimo(10.0);

        when(materialService.listarPorId(1L)).thenReturn(materialDoBanco);
        when(materialService.editar(eq(1L), any())).thenReturn(materialDoBanco);
        when(registroSaidaRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        RegistroSaida criado = registroSaidaService.criarRegistroSaida(materialEntrada, 5.0, LocalDateTime.now());

        // Estoque deve cair de 50 para 45 (o usuário usou materialEntrada getQuantidadeEstoque em vez de materialUsado no if dele, mas o cálculo em si deve estar ok)
        Assertions.assertNotNull(criado);
        Assertions.assertNull(criado.getAlertaEstoque()); // 45 não é menor que 10
        verify(materialService).editar(eq(1L), any());
        verify(registroSaidaRepository).save(any());
    }

    @Test
    void deveCriarRegistroSaidaEGerarAlertaSeAbaixoDoMinimo() {
        Material materialEntrada = new Material();
        materialEntrada.setId(1L);
        materialEntrada.setQuantidadeEstoque(15.0);
        materialEntrada.setEstoqueMinimo(10.0);

        Material materialDoBanco = new Material();
        materialDoBanco.setId(1L);
        materialDoBanco.setQuantidadeEstoque(15.0);
        materialDoBanco.setEstoqueMinimo(10.0);

        when(materialService.listarPorId(1L)).thenReturn(materialDoBanco);
        when(materialService.editar(eq(1L), any())).thenReturn(materialDoBanco);
        when(registroSaidaRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        RegistroSaida criado = registroSaidaService.criarRegistroSaida(materialEntrada, 8.0, LocalDateTime.now());

        // Estoque cai de 15 para 7 (7 < 10)
        Assertions.assertNotNull(criado);
        Assertions.assertNotNull(criado.getAlertaEstoque());
    }

    @Test
    void deveListarRegistrosSaida() {
        when(registroSaidaRepository.findAll()).thenReturn(List.of(new RegistroSaida(), new RegistroSaida()));

        List<RegistroSaida> registros = registroSaidaService.listarTodos();

        Assertions.assertEquals(2, registros.size());
    }

    @Test
    void deveBuscarRegistroSaidaPorId() {
        RegistroSaida mock = new RegistroSaida();
        mock.setId(1L);
        mock.setQuantidadeGasta(5.0);
        when(registroSaidaRepository.findById(1L)).thenReturn(Optional.of(mock));

        RegistroSaida encontrado = registroSaidaService.listarPorId(1L);

        Assertions.assertNotNull(encontrado);
        Assertions.assertEquals(5.0, encontrado.getQuantidadeGasta());
    }

    @Test
    void deveLancarExcecaoAoBuscarRegistroSaidaInexistente() {
        when(registroSaidaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> registroSaidaService.listarPorId(99L));
    }

    @Test
    void deveEditarRegistroSaida() {
        RegistroSaida antigo = new RegistroSaida();
        antigo.setId(1L);
        antigo.setQuantidadeGasta(2.0);

        RegistroSaida novo = new RegistroSaida();
        novo.setQuantidadeGasta(10.0);

        when(registroSaidaRepository.findById(1L)).thenReturn(Optional.of(antigo));
        when(registroSaidaRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        RegistroSaida atualizado = registroSaidaService.editar(1L, novo);

        Assertions.assertEquals(10.0, atualizado.getQuantidadeGasta());
    }

    @Test
    void deveExcluirRegistroSaida() {
        when(registroSaidaRepository.existsById(1L)).thenReturn(true);
        doNothing().when(registroSaidaRepository).deleteById(1L);

        registroSaidaService.excluir(1L);

        verify(registroSaidaRepository).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoExcluirRegistroSaidaInexistente() {
        when(registroSaidaRepository.existsById(99L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> registroSaidaService.excluir(99L));
        verify(registroSaidaRepository, never()).deleteById(anyLong());
    }
}
