ALTER TABLE Orcamentos DROP COLUMN modelo_veiculo;
ALTER TABLE Orcamentos DROP COLUMN marca_veiculo;
ALTER TABLE Orcamentos DROP COLUMN cor_veiculo;
ALTER TABLE Orcamentos DROP COLUMN ano_veiculo;
ALTER TABLE Orcamentos DROP COLUMN placa_veiculo;

ALTER TABLE Orcamentos ADD COLUMN veiculo_id BIGINT NOT NULL;

ALTER TABLE Ordens_Servico DROP COLUMN nome_cliente;
ALTER TABLE Ordens_Servico DROP COLUMN contato_cliente;

ALTER TABLE Ordens_Servico ADD COLUMN cliente_id BIGINT NOT NULL;
