package mz.com.soto.junior.iBiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mz.com.soto.junior.iBiapi.modelo.Regiao;

public interface RegiaoRepository extends JpaRepository<Regiao, Long> {
	
	Regiao findById(long id);

	Regiao findByNome(String nome);


}
