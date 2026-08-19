package org.example.sistemaoficina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.sistemaoficina.entity.Material;
import org.example.sistemaoficina.service.MaterialService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MaterialController.class)
public class MaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaterialService materialService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarMaterial() throws Exception {
        Material input = new Material();
        input.setNome("Óleo");
        input.setDescricao("Oleo de motor");
        input.setUnidadeMedida("Litro");
        input.setQuantidadeEstoque(10.0);
        input.setEstoqueMinimo(2.0);

        Material output = new Material();
        output.setId(1L);
        output.setNome("Óleo");

        Mockito.when(materialService.criarMaterial(anyString(), anyString(), anyString(), anyDouble(), anyDouble())).thenReturn(output);

        mockMvc.perform(post("/materiais")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Óleo"));
    }

    @Test
    void deveListarMateriais() throws Exception {
        Mockito.when(materialService.listarTodos()).thenReturn(List.of(new Material()));

        mockMvc.perform(get("/materiais"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void deveBuscarMaterialPorId() throws Exception {
        Material m = new Material();
        m.setId(1L);
        m.setNome("Filtro");
        
        Mockito.when(materialService.listarPorId(1L)).thenReturn(m);

        mockMvc.perform(get("/materiais/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Filtro"));
    }

    @Test
    void deveEditarMaterial() throws Exception {
        Material input = new Material();
        input.setNome("Filtro Ar");
        input.setDescricao("Filtro cabine");
        input.setUnidadeMedida("Un");
        input.setQuantidadeEstoque(5.0);
        input.setEstoqueMinimo(1.0);

        Material output = new Material();
        output.setId(1L);
        output.setNome("Filtro Ar");

        Mockito.when(materialService.editar(eq(1L), any(Material.class))).thenReturn(output);

        mockMvc.perform(put("/materiais/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Filtro Ar"));
    }

    @Test
    void deveExcluirMaterial() throws Exception {
        Mockito.doNothing().when(materialService).excluir(1L);

        mockMvc.perform(delete("/materiais/1"))
                .andExpect(status().isNoContent());
    }
}
