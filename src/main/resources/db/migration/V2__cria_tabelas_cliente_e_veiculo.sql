CREATE TABLE Clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    documento VARCHAR(20),
    telefone VARCHAR(200) NOT NULL,
    email VARCHAR(200),
    endereco VARCHAR(255)
);

CREATE TABLE Veiculos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(20) NOT NULL,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    ano INT NOT NULL,
    cor VARCHAR(50) NOT NULL,
    km INT,
    cliente_id BIGINT NOT NULL,

    FOREIGN KEY (cliente_id) REFERENCES Clientes(id)
);
