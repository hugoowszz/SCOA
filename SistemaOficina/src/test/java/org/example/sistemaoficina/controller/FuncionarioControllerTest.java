package org.example.sistemaoficina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.sistemaoficina.entity.Funcionario;
import org.example.sistemaoficina.service.FuncionarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FuncionarioController.class)
public class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuncionarioService funcionarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarFuncionario() throws Exception {
        Funcionario funcionarioInput = new Funcionario();
        funcionarioInput.setNome("Joao");
        funcionarioInput.setTelefone("119999");

        Funcionario funcionarioOutput = new Funcionario();
        funcionarioOutput.setId(1L);
        funcionarioOutput.setNome("Joao");
        funcionarioOutput.setTelefone("119999");

        Mockito.when(funcionarioService.criarFuncionario(anyString(), anyString())).thenReturn(funcionarioOutput);

        mockMvc.perform(post("/funcionarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(funcionarioInput)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Joao"));
    }

    @Test
    void deveListarFuncionarios() throws Exception {
        Mockito.when(funcionarioService.listarTodos()).thenReturn(List.of(new Funcionario(), new Funcionario()));

        mockMvc.perform(get("/funcionarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void deveBuscarFuncionarioPorId() throws Exception {
        Funcionario f = new Funcionario();
        f.setId(1L);
        f.setNome("Maria");
        
        Mockito.when(funcionarioService.listarPorId(1L)).thenReturn(f);

        mockMvc.perform(get("/funcionarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Maria"));
    }

    @Test
    void deveEditarFuncionario() throws Exception {
        Funcionario input = new Funcionario();
        input.setNome("Maria Editada");
        input.setTelefone("2222");

        Funcionario output = new Funcionario();
        output.setId(1L);
        output.setNome("Maria Editada");
        output.setTelefone("2222");

        Mockito.when(funcionarioService.editar(eq(1L), any(Funcionario.class))).thenReturn(output);

        mockMvc.perform(put("/funcionarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Maria Editada"));
    }

    @Test
    void deveExcluirFuncionario() throws Exception {
        Mockito.doNothing().when(funcionarioService).excluir(1L);

        mockMvc.perform(delete("/funcionarios/1"))
                .andExpect(status().isNoContent());
    }
}
