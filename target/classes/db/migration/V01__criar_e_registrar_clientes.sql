CREATE TABLE cliente(
    codCliente BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(70) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    email VARCHAR(40),
    dataNascimento DATE,
    telefone VARCHAR(15)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;