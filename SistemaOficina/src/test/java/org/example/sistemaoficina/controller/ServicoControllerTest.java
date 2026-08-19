package org.example.sistemaoficina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.sistemaoficina.entity.Funcionario;
import org.example.sistemaoficina.entity.Orcamento;
import org.example.sistemaoficina.entity.Servico;
import org.example.sistemaoficina.service.ServicoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ServicoController.class)
public class ServicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicoService servicoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarServico() throws Exception {
        Servico input = new Servico();
        input.setNomeCliente("Cliente Silva");
        input.setContatoCliente("119999");
        input.setStatus("Não iniciado");
        input.setOrcamento(new Orcamento());
        input.setFuncionario(new Funcionario());

        Servico output = new Servico();
        output.setId(1L);
        output.setNomeCliente("Cliente Silva");

        Mockito.when(servicoService.criarServico(any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(output);

        mockMvc.perform(post("/servicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomeCliente").value("Cliente Silva"));
    }

    @Test
    void deveListarServicos() throws Exception {
        Mockito.when(servicoService.listarTodos()).thenReturn(List.of(new Servico()));

        mockMvc.perform(get("/servicos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void deveBuscarServicoPorId() throws Exception {
        Servico s = new Servico();
        s.setId(1L);
        s.setNomeCliente("Cliente Busca");
        
        Mockito.when(servicoService.listarPorId(1L)).thenReturn(s);

        mockMvc.perform(get("/servicos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeCliente").value("Cliente Busca"));
    }

    @Test
    void deveEditarServico() throws Exception {
        Servico input = new Servico();
        input.setNomeCliente("Cliente Editado");
        input.setContatoCliente("118888");
        input.setStatus("Entregue");
        input.setOrcamento(new Orcamento());
        input.setFuncionario(new Funcionario());

        Servico output = new Servico();
        output.setId(1L);
        output.setNomeCliente("Cliente Editado");

        Mockito.when(servicoService.editar(eq(1L), any(), any(), any(Servico.class))).thenReturn(output);

        mockMvc.perform(put("/servicos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeCliente").value("Cliente Editado"));
    }

    @Test
    void deveExcluirServico() throws Exception {
        Mockito.doNothing().when(servicoService).excluir(1L);

        mockMvc.perform(delete("/servicos/1"))
                .andExpect(status().isNoContent());
    }
}
