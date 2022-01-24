package mz.com.soto.junior.iBiapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
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
import mz.com.soto.junior.iBiapi.modelo.Pais;
import mz.com.soto.junior.iBiapi.repository.PaisRepository;
import mz.com.soto.junior.iBiapi.service.PaisService;

	@RestController
	@RequestMapping("/pais")
	public class PaisResource {

		@Autowired
		private PaisRepository paisRepository;

		@Autowired
		private PaisService paisService;
		
		@Autowired
		private ApplicationEventPublisher publisher;

		@PostMapping
		public ResponseEntity<Pais> criar(@Valid @RequestBody Pais pais, HttpServletResponse response) {
			Pais paisSalva = paisRepository.save(pais);
			
			publisher.publishEvent(new RecursoCriadoEvent(this, response, paisSalva.getCodigo()));
			
			return ResponseEntity.status(HttpStatus.CREATED).body(paisSalva);
			
		}
		
		@GetMapping// essa anotacao diz que deve fazzer um get neste metodo
		 public List<Pais> listar() {
	        return paisRepository.findAll();
	    }
		
		@GetMapping("/{codigo}")
		public ResponseEntity<Pais> buscarPeloCodigo(@PathVariable Long codigo) {
			Optional<Pais> pais = paisRepository.findById(codigo);
			return pais.isPresent() ? ResponseEntity.ok(pais.get()) : ResponseEntity.notFound().build();
		}

		@DeleteMapping("/{codigo}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void remover(@PathVariable Long codigo) {
			this.paisRepository.deleteById(codigo);
		}

		@PutMapping("/{codigo}")
		public ResponseEntity<Pais> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pais pais) {
			Pais paisSalva = paisService.atualizar(codigo, pais);
			return ResponseEntity.ok(paisSalva);
		
		
	}
		
		@GetMapping("/{nome}")
		public List<Pais> OrdenarPorNome(){
			List<Pais> allPaisSortedByNome = paisRepository.findAll(Sort.by("nome"));
			return allPaisSortedByNome;

		}
		
		@GetMapping("/{capital}")
		public List<Pais> OrdenarPorcapital(){
			List<Pais> allPaisSortedByCapital = paisRepository.findAll(Sort.by("capital"));
			return allPaisSortedByCapital;

		}
		
		
		
		@GetMapping("/{subregiao}")
		public List<Pais> OrdenarPorSubRegiao(){
			List<Pais> allPaisSortedBySubRegiao = paisRepository.findAll(Sort.by("subRegiao"));
			return allPaisSortedBySubRegiao;

		}
}


