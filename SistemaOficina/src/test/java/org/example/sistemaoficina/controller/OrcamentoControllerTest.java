package org.example.sistemaoficina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.sistemaoficina.entity.Orcamento;
import org.example.sistemaoficina.service.OrcamentoService;
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

@WebMvcTest(OrcamentoController.class)
public class OrcamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrcamentoService orcamentoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarOrcamento() throws Exception {
        Orcamento input = new Orcamento();
        input.setModeloVeiculo("Gol");
        input.setMarcaVeiculo("VW");
        input.setCorVeiculo("Branco");
        input.setPlacaVeiculo("ABC1234");
        input.setObservacao("Teste");
        input.setPreco(100.0);

        Orcamento output = new Orcamento();
        output.setId(1L);
        output.setModeloVeiculo("Gol");
        output.setPreco(100.0);

        Mockito.when(orcamentoService.criarOrcamento(anyString(), anyString(), anyString(), anyString(), anyString(), anyDouble())).thenReturn(output);

        mockMvc.perform(post("/orcamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.modeloVeiculo").value("Gol"));
    }

    @Test
    void deveListarOrcamentos() throws Exception {
        Mockito.when(orcamentoService.listarTodos()).thenReturn(List.of(new Orcamento()));

        mockMvc.perform(get("/orcamentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void deveBuscarOrcamentoPorId() throws Exception {
        Orcamento o = new Orcamento();
        o.setId(1L);
        o.setModeloVeiculo("Civic");
        
        Mockito.when(orcamentoService.listarPorId(1L)).thenReturn(o);

        mockMvc.perform(get("/orcamentos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modeloVeiculo").value("Civic"));
    }

    @Test
    void deveEditarOrcamento() throws Exception {
        Orcamento input = new Orcamento();
        input.setModeloVeiculo("Civic 2");
        input.setMarcaVeiculo("Honda");
        input.setCorVeiculo("Preto");
        input.setPlacaVeiculo("XYZ9876");
        input.setPreco(200.0);

        Orcamento output = new Orcamento();
        output.setId(1L);
        output.setModeloVeiculo("Civic 2");

        Mockito.when(orcamentoService.editar(eq(1L), any(Orcamento.class))).thenReturn(output);

        mockMvc.perform(put("/orcamentos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modeloVeiculo").value("Civic 2"));
    }

    @Test
    void deveExcluirOrcamento() throws Exception {
        Mockito.doNothing().when(orcamentoService).excluir(1L);

        mockMvc.perform(delete("/orcamentos/1"))
                .andExpect(status().isNoContent());
    }
}
