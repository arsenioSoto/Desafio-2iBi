package mz.com.soto.junior.iBiapi.resource;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mz.com.soto.junior.iBiapi.event.RecursoCriadoEvent;
import mz.com.soto.junior.iBiapi.modelo.Continente;
import mz.com.soto.junior.iBiapi.repository.ContinenteRepository;
import mz.com.soto.junior.iBiapi.service.ContinenteService;

	@RestController
	@RequestMapping("/continentes")
	public class ContinenteResource {

		@Autowired
		private ContinenteRepository continenteRepository;

		@Autowired
		private ContinenteService continenteService;
		
		@Autowired
		private ApplicationEventPublisher publisher;

	
		
		@PostMapping
		public ResponseEntity<Continente> criar(@Valid @RequestBody Continente continente, HttpServletResponse response) {
			Continente continenteSalva = continenteRepository.save(continente);
			
			publisher.publishEvent(new RecursoCriadoEvent(this, response, continenteSalva.getCodigo()));
			
			return ResponseEntity.status(HttpStatus.CREATED).body(continenteSalva);
			
			
		}
		
		@GetMapping// essa anotacao diz que deve fazzer um get neste metodo
		 public List<Continente> listar() {
	        return continenteRepository.findAll();
	    }
		
		@GetMapping("/{codigo}")
		public ResponseEntity<Continente> buscarPeloCodigo(@PathVariable Long codigo) {
			Optional<Continente> continente = continenteRepository.findById(codigo);
			return continente.isPresent() ? ResponseEntity.ok(continente.get()) : ResponseEntity.notFound().build();
		}

		@DeleteMapping("/{codigo}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void remover(@PathVariable Long codigo) {
			this.continenteRepository.deleteById(codigo);
		}

		@PutMapping("/{codigo}")
		public ResponseEntity<Continente> atualizar(@PathVariable Long codigo, @Valid @RequestBody Continente continente) {
			Continente continenteSalva = continenteService.atualizar(codigo, continente);
			return ResponseEntity.ok(continenteSalva);
		
		
	}
}
