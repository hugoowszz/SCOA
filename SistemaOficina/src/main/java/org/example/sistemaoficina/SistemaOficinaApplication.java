package org.example.sistemaoficina;

import org.example.sistemaoficina.entity.Funcionario;
import org.example.sistemaoficina.entity.Orcamento;
import org.example.sistemaoficina.service.FuncionarioService;
import org.example.sistemaoficina.service.MaterialService;
import org.example.sistemaoficina.service.OrcamentoService;
import org.example.sistemaoficina.service.ServicoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class SistemaOficinaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaOficinaApplication.class, args);
    }

}
