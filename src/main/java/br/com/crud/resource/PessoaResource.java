package br.com.crud.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.crud.model.Pessoa;
import br.com.crud.repository.filter.PessoaFilter;
import br.com.crud.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public ResponseEntity<Page<Pessoa>> filtrar(PessoaFilter filter, Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.filtrar(filter, pageable));
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Pessoa pessoa) {
		pessoa = pessoaService.salvar(pessoa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getPorId(@PathVariable("id") Long id) {
		Pessoa pessoa = pessoaService.getPorId(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(pessoa);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		pessoaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Pessoa pessoa, @PathVariable Long id) {
		pessoaService.atualizar(id, pessoa);
		return ResponseEntity.noContent().build();
	}
	
}
