CREATE TABLE pais(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	capital VARCHAR(20) NOT NULL,
	codigo_cantinente BIGINT(20) NOT NULL,
	codigo_regiao BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_cantinente) REFERENCES continente(codigo),
	FOREIGN KEY (codigo_regiao) REFERENCES regiao(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pais(nome, capital, codigo_cantinente, codigo_regiao) VALUES ("Mocambique", "Maputo", 1, 1);
INSERT INTO pais(nome, capital, codigo_cantinente, codigo_regiao) VALUES ("South Africa", "Pritoria", 1, 1);