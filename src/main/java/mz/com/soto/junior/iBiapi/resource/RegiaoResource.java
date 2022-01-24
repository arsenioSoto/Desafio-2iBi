package mz.com.soto.junior.iBiapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mz.com.soto.junior.iBiapi.event.RecursoCriadoEvent;
import mz.com.soto.junior.iBiapi.modelo.Regiao;
import mz.com.soto.junior.iBiapi.repository.RegiaoRepository;
import mz.com.soto.junior.iBiapi.service.RegiaoService;

@RestController
@RequestMapping("/regioes")
public class RegiaoResource {
	
	@Autowired
	private RegiaoRepository regiaooRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private RegiaoService regiaoService;
    
	@GetMapping
	public List<Regiao> listar() {
		return regiaooRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Regiao> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Regiao> regiaoo = regiaooRepository.findById(codigo);
		return regiaoo.isPresent() ? ResponseEntity.ok(regiaoo.get()) : ResponseEntity.notFound().build();
	}
		
	@PostMapping
	public ResponseEntity<Regiao> criar(@Valid @RequestBody Regiao regiaoo, HttpServletResponse response) {
		Regiao regiaooSalvo = regiaooRepository.save(regiaoo);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, regiaooSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(regiaooSalvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Regiao> atualizar(@PathVariable Long codigo, @Valid @RequestBody Regiao regiaoo) {
		Regiao regiaooSalva = regiaoService.atualizar(codigo, regiaoo);
		return ResponseEntity.ok(regiaooSalva);
	}

}
