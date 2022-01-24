package mz.com.soto.junior.iBiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mz.com.soto.junior.iBiapi.modelo.Continente;

public interface ContinenteRepository extends JpaRepository<Continente, Long> {
	
	Continente findById(long id);

	Continente findByNome(String nome);
}
