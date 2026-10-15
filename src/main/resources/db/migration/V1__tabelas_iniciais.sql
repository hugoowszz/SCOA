CREATE TABLE Funcionarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    telefone VARCHAR(200) NOT NULL
);

CREATE TABLE Materiais (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    unidade_medida VARCHAR(20) NOT NULL,
    quantidade INT NOT NULL,
    estoque_minimo INT
);

CREATE TABLE Orcamentos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    modelo_veiculo VARCHAR(200) NOT NULL,
    marca_veiculo VARCHAR(200) NOT NULL,
    cor_veiculo VARCHAR(200) NOT NULL,
    ano_veiculo INT NOT NULL,
    placa_veiculo VARCHAR(200) NOT NULL,
    observacao TEXT,
    preco NUMERIC(12, 2) NOT NULL
);

CREATE TABLE Ordens_Servico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    orcamento_id BIGINT NOT NULL,
    funcionario_id BIGINT NOT NULL,
    nome_cliente VARCHAR(200) NOT NULL,
    contato_cliente VARCHAR(200) NOT NULL,
    status VARCHAR(30),
    data_criacao DATE NOT NULL,
    data_entrega DATE,
    metodo_pagamento VARCHAR(200),

    FOREIGN KEY (orcamento_id) REFERENCES Orcamentos(id),
    FOREIGN KEY (funcionario_id) REFERENCES Funcionarios(id)
);

CREATE TABLE Registros_Saida (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    material_id BIGINT NOT NULL,
    quantidade_gasta INT NOT NULL,
    data_saida DATE NOT NULL,

    FOREIGN KEY (material_id) REFERENCES Materiais(id)
);