package mz.com.soto.junior.iBiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mz.com.soto.junior.iBiapi.modelo.Pais;

public interface PaisRepository extends JpaRepository<Pais, Long> {
	
	Pais findById(long id);
	
	Pais findByNome(String nome);
}
