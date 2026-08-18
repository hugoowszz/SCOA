# 🚗 SCOA - Sistema de Controle de Oficina Automotiva

Um sistema de gerenciamento de backend desenvolvido em **Java com Spring Boot**, focado em modernizar a rotina de oficinas mecânicas e centros de estética automotiva.

## 🚀 Funcionalidades Principais

- **Gestão de Funcionários:** Cadastro e controle da equipe (mecânicos, funileiros, atendentes).
- **Orçamentos:** Criação de orçamentos vinculando dados essenciais do veículo e valores.
- **Ordens de Serviço:** Controle do fluxo de trabalho, status atual ("Em andamento", "Entregue") e definição de métodos de pagamento.
- **Gestão de Estoque:** Cadastro de materiais de uso contínuo (tintas, massas, fitas) com definição de **estoque mínimo**.
- **Registro de Saídas:** Baixa de materiais do estoque com lógica automatizada para garantir integridade e gerar futuros alertas de reposição.

## 🛠️ Tecnologias Utilizadas

- **Java**
- **Spring Boot 3** (Web, Data JPA)
- **Hibernate** (ORM)
- **Lombok** (Redução de boilerplate)
- **Jakarta Validation** (Validação rigorosa de dados de entrada)