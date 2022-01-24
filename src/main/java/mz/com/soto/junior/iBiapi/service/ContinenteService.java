package mz.com.soto.junior.iBiapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mz.com.soto.junior.iBiapi.modelo.Continente;
import mz.com.soto.junior.iBiapi.repository.ContinenteRepository;

@Service
public class ContinenteService {
	
	@Autowired
	private ContinenteRepository continenteRepository;
	
	public Continente atualizar(Long codigo, Continente continente) {
		Continente continenteSalva = buscarContinentePeloCodigo(codigo);
				
		BeanUtils.copyProperties(continente, continenteSalva, "codigo");	
		return continenteRepository.save(continenteSalva);
	}
	
	
	private Continente buscarContinentePeloCodigo(Long codigo) {
	Continente continenteSalva =  continenteRepository.findById(codigo)
			.orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		return continenteSalva;
	}
	
	public Continente atualizar(long id,Continente continente) {

		Continente continenteSalva = continenteRepository.findById(id);
		if (continenteSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(continente,continenteSalva, "id");
		return continenteRepository.save(continenteSalva);

	}
}

