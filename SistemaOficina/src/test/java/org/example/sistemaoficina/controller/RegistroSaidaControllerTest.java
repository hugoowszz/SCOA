package org.example.sistemaoficina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.sistemaoficina.entity.Material;
import org.example.sistemaoficina.entity.RegistroSaida;
import org.example.sistemaoficina.service.RegistroSaidaService;
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
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegistroSaidaController.class)
public class RegistroSaidaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistroSaidaService registroSaidaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarRegistroSaida() throws Exception {
        RegistroSaida input = new RegistroSaida();
        input.setMaterial(new Material());
        input.setQuantidadeGasta(5.0);
        input.setDataSaida(LocalDateTime.now());

        RegistroSaida output = new RegistroSaida();
        output.setId(1L);
        output.setQuantidadeGasta(5.0);

        Mockito.when(registroSaidaService.criarRegistroSaida(any(), anyDouble(), any())).thenReturn(output);

        mockMvc.perform(post("/registros-saida")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.quantidadeGasta").value(5.0));
    }

    @Test
    void deveListarRegistrosSaida() throws Exception {
        Mockito.when(registroSaidaService.listarTodos()).thenReturn(List.of(new RegistroSaida()));

        mockMvc.perform(get("/registros-saida"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void deveBuscarRegistroSaidaPorId() throws Exception {
        RegistroSaida r = new RegistroSaida();
        r.setId(1L);
        r.setQuantidadeGasta(10.0);
        
        Mockito.when(registroSaidaService.listarPorId(1L)).thenReturn(r);

        mockMvc.perform(get("/registros-saida/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantidadeGasta").value(10.0));
    }

    @Test
    void deveEditarRegistroSaida() throws Exception {
        RegistroSaida input = new RegistroSaida();
        input.setMaterial(new Material());
        input.setQuantidadeGasta(15.0);
        input.setDataSaida(LocalDateTime.now());

        RegistroSaida output = new RegistroSaida();
        output.setId(1L);
        output.setQuantidadeGasta(15.0);

        Mockito.when(registroSaidaService.editar(eq(1L), any(RegistroSaida.class))).thenReturn(output);

        mockMvc.perform(put("/registros-saida/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantidadeGasta").value(15.0));
    }

    @Test
    void deveExcluirRegistroSaida() throws Exception {
        Mockito.doNothing().when(registroSaidaService).excluir(1L);

        mockMvc.perform(delete("/registros-saida/1"))
                .andExpect(status().isNoContent());
    }
}
