CREATE TABLE continente(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO continente(nome) VALUES('AFRICANO');
INSERT INTO continente(nome) VALUES('Asiatico');
INSERT INTO continente(nome) VALUES('Americano');
INSERT INTO continente(nome) VALUES('Europa');
INSERT INTO continente(nome) VALUES('Outros');