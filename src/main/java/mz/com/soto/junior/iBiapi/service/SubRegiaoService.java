package mz.com.soto.junior.iBiapi.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mz.com.soto.junior.iBiapi.modelo.SubRegiao;
import mz.com.soto.junior.iBiapi.repository.SubRegiaoRepository;

@Service
public class SubRegiaoService {
	
	@Autowired
	private SubRegiaoRepository subRegiaoRepository;
	
	public SubRegiao atualizar(Long codigo, SubRegiao subRegiao) {
		SubRegiao subRegiaoSalva = buscarSubRegiaoPeloCodigo(codigo);
				
		BeanUtils.copyProperties(subRegiao, subRegiaoSalva, "codigo");	
		return subRegiaoRepository.save(subRegiaoSalva);
	}
	
	
	private SubRegiao buscarSubRegiaoPeloCodigo(Long codigo) {
	SubRegiao subRegiaoSalva =  subRegiaoRepository.findById(codigo)
			.orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		return subRegiaoSalva;
	}
	
	public SubRegiao atualizar(long id,SubRegiao subRegiao) {

		SubRegiao subRegiaoSalva = subRegiaoRepository.findById(id);
		if (subRegiaoSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(subRegiao,subRegiaoSalva, "id");
		return subRegiaoRepository.save(subRegiaoSalva);

	}
}


