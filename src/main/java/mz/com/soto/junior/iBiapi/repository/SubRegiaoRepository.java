package mz.com.soto.junior.iBiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mz.com.soto.junior.iBiapi.modelo.SubRegiao;

public interface SubRegiaoRepository extends JpaRepository<SubRegiao, Long> {
	SubRegiao findById(long id);

	SubRegiao findByNome(String nome);

}
