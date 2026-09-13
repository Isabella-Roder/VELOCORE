CREATE TABLE veiculos (
    id UUID PRIMARY KEY,
    chassi VARCHAR(17) NOT NULL UNIQUE,
    modelo VARCHAR(150) NOT NULL,
    marca VARCHAR(150) NOT NULL,
    ano_fabricacao INTEGER NOT NULL,
    cor VARCHAR(80) NOT NULL,
    preco NUMERIC(19,2) NOT NULL,
    status VARCHAR(30) NOT NULL,
    criado TIMESTAMP NOT NULL,
    atualizado TIMESTAMP NOT NULL
);