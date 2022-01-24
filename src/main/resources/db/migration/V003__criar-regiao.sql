CREATE TABLE regiao (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	codigo_subregiao BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_subregiao) REFERENCES subregiao(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO regiao(nome, codigo_subregiao) VALUES('Nort', 1);