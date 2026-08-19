package org.example.sistemaoficina.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.sistemaoficina.entity.Material;
import org.example.sistemaoficina.repository.MaterialRepository;
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
public class MaterialServiceTest {

    @InjectMocks
    private MaterialService materialService;

    @Mock
    private MaterialRepository materialRepository;

    @Test
    void deveCriarMaterial() {
        when(materialRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Material material = materialService.criarMaterial("Lixa", "Lixa para polimento", "Unidade", 100.0, 10.0);

        Assertions.assertEquals("Lixa", material.getNome());
        Assertions.assertEquals(100.0, material.getQuantidadeEstoque());
        verify(materialRepository).save(any());
    }

    @Test
    void deveListarMateriais() {
        when(materialRepository.findAll()).thenReturn(List.of(new Material(), new Material()));

        List<Material> materiais = materialService.listarTodos();

        Assertions.assertEquals(2, materiais.size());
    }

    @Test
    void deveBuscarMaterialPorId() {
        Material mock = new Material();
        mock.setId(1L);
        mock.setNome("Verniz");
        when(materialRepository.findById(1L)).thenReturn(Optional.of(mock));

        Material encontrado = materialService.listarPorId(1L);

        Assertions.assertNotNull(encontrado);
        Assertions.assertEquals("Verniz", encontrado.getNome());
    }

    @Test
    void deveLancarExcecaoAoBuscarMaterialInexistente() {
        when(materialRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> materialService.listarPorId(99L));
    }

    @Test
    void deveEditarMaterial() {
        Material antigo = new Material();
        antigo.setId(1L);
        antigo.setNome("Lixa");

        Material novo = new Material();
        novo.setNome("Lixa D'agua");
        novo.setQuantidadeEstoque(200.0);

        when(materialRepository.findById(1L)).thenReturn(Optional.of(antigo));
        when(materialRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Material atualizado = materialService.editar(1L, novo);

        Assertions.assertEquals("Lixa D'agua", atualizado.getNome());
        Assertions.assertEquals(200.0, atualizado.getQuantidadeEstoque());
    }

    @Test
    void deveExcluirMaterial() {
        when(materialRepository.existsById(1L)).thenReturn(true);
        doNothing().when(materialRepository).deleteById(1L);

        materialService.excluir(1L);

        verify(materialRepository).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoExcluirMaterialInexistente() {
        when(materialRepository.existsById(99L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> materialService.excluir(99L));
        verify(materialRepository, never()).deleteById(anyLong());
    }

    @Test
    void deveListarMateriaisComEstoqueMinimo() {
        Material mat1 = new Material();
        mat1.setNome("OK");
        mat1.setQuantidadeEstoque(50.0);
        mat1.setEstoqueMinimo(10.0);

        Material mat2 = new Material();
        mat2.setNome("Abaixo");
        mat2.setQuantidadeEstoque(5.0);
        mat2.setEstoqueMinimo(10.0);

        Material mat3 = new Material();
        mat3.setNome("No Limite");
        mat3.setQuantidadeEstoque(10.0);
        mat3.setEstoqueMinimo(10.0);

        when(materialRepository.findAll()).thenReturn(List.of(mat1, mat2, mat3));

        List<Material> emAlerta = materialService.listarMateriaisComEstoqueNoMinimo();

        Assertions.assertEquals(2, emAlerta.size());
        Assertions.assertTrue(emAlerta.contains(mat2));
        Assertions.assertTrue(emAlerta.contains(mat3));
        Assertions.assertFalse(emAlerta.contains(mat1));
    }
}
