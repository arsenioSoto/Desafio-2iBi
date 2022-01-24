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
import mz.com.soto.junior.iBiapi.modelo.SubRegiao;
import mz.com.soto.junior.iBiapi.repository.SubRegiaoRepository;
import mz.com.soto.junior.iBiapi.service.SubRegiaoService;

	@RestController
	@RequestMapping("/subRegioes")
	public class SubRegiaoResource {

		@Autowired
		private SubRegiaoRepository subRegiaoRepository;

		@Autowired
		private SubRegiaoService subRegiaoService;
		
		@Autowired
		private ApplicationEventPublisher publisher;

		@PostMapping
		public ResponseEntity<SubRegiao> criar(@Valid @RequestBody SubRegiao subRegiao, HttpServletResponse response) {
			SubRegiao subRegiaoSalva = subRegiaoRepository.save(subRegiao);
			
			publisher.publishEvent(new RecursoCriadoEvent(this, response, subRegiaoSalva.getCodigo()));
			
			return ResponseEntity.status(HttpStatus.CREATED).body(subRegiaoSalva);
			
			
		}
		
		@GetMapping// essa anotacao diz que deve fazzer um get neste metodo
		 public List<SubRegiao> listar() {
	        return subRegiaoRepository.findAll();
	    }
		
		@GetMapping("/{codigo}")
		public ResponseEntity<SubRegiao> buscarPeloCodigo(@PathVariable Long codigo) {
			Optional<SubRegiao> subRegiao = subRegiaoRepository.findById(codigo);
			return subRegiao.isPresent() ? ResponseEntity.ok(subRegiao.get()) : ResponseEntity.notFound().build();
		}

		@DeleteMapping("/{codigo}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void remover(@PathVariable Long codigo) {
			this.subRegiaoRepository.deleteById(codigo);
		}

		@PutMapping("/{codigo}")
		public ResponseEntity<SubRegiao> atualizar(@PathVariable Long codigo, @Valid @RequestBody SubRegiao subRegiao) {
			SubRegiao subRegiaoSalva = subRegiaoService.atualizar(codigo, subRegiao);
			return ResponseEntity.ok(subRegiaoSalva);
		
		
	}
}

