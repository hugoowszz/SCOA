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

    @Bean
    public CommandLineRunner run(FuncionarioService funcionarioService,
                                 OrcamentoService orcamentoService,
                                 ServicoService servicoService,
                                 MaterialService materialService) {
        return args -> {
            Funcionario f1 = funcionarioService.criarFuncionario("João da Silva", "11999999999");
            Funcionario f2 = funcionarioService.criarFuncionario("Maria Souza", "11888888888");

            Orcamento o1 = orcamentoService.criarOrcamento("Civic", "Honda", "Preto", "ABC1234", "Troca de óleo", 250.0);
            Orcamento o2 = orcamentoService.criarOrcamento("Corolla", "Toyota", "Prata", "XYZ9876", "Revisão geral", 1500.0);
            Orcamento o3 = orcamentoService.criarOrcamento("Gol", "Volkswagen", "Branco", "DEF5678", "Troca de pastilhas de freio", 400.0);

            servicoService.criarServico(o1, f1, "Carlos", "11977777777", "Não iniciado", null, null, "Cartão de Crédito");
            servicoService.criarServico(o2, f2, "Ana", "11966666666", "Em andamento", null, null, "Dinheiro");

            materialService.criarMaterial("Primer", "Anjo" , "Latas", 5.0, 2.0);
            materialService.criarMaterial("Tinta", "Preto ninja" , "Latas", 2.0, 1.0);
            System.out.println("Dados de teste criados com sucesso!");
        };
    }
}
