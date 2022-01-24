package mz.com.soto.junior.iBiapi.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mz.com.soto.junior.iBiapi.modelo.Regiao;
import mz.com.soto.junior.iBiapi.repository.RegiaoRepository;

@Service
public class RegiaoService {
	
	@Autowired
	private RegiaoRepository regiaoRepository;
	
	public Regiao atualizar(Long codigo, Regiao regiao) {
		Regiao regiaoSalva = buscarRegiaoPeloCodigo(codigo);
				
		BeanUtils.copyProperties(regiao, regiaoSalva, "codigo");	
		return regiaoRepository.save(regiaoSalva);
	}
	
	
	private Regiao buscarRegiaoPeloCodigo(Long codigo) {
	Regiao regiaoSalva =  regiaoRepository.findById(codigo)
			.orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		return regiaoSalva;
	}
	
	public Regiao atualizar(long id,Regiao regiao) {

		Regiao regiaoSalva = regiaoRepository.findById(id);
		if (regiaoSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(regiao,regiaoSalva, "id");
		return regiaoRepository.save(regiaoSalva);

	}
}


