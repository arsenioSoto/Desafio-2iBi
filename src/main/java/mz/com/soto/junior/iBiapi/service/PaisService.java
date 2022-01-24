package mz.com.soto.junior.iBiapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mz.com.soto.junior.iBiapi.modelo.Pais;
import mz.com.soto.junior.iBiapi.repository.PaisRepository;

@Service
public class PaisService {
	
	@Autowired
	private PaisRepository paisRepository;
	
	public Pais atualizar(Long codigo, Pais pais) {
		Pais paisSalva = buscarPaisPeloCodigo(codigo);
				
		BeanUtils.copyProperties(pais, paisSalva, "codigo");	
		return paisRepository.save(paisSalva);
	}
	
	
	private Pais buscarPaisPeloCodigo(Long codigo) {
	Pais paisSalva =  paisRepository.findById(codigo)
			.orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		return paisSalva;
	}
	
	public Pais atualizar(long id,Pais pais) {

		Pais paisSalva = paisRepository.findById(id);
		if (paisSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(pais,paisSalva, "id");
		return paisRepository.save(paisSalva);

	}
}


