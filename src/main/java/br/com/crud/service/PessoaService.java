package br.com.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.crud.model.Pessoa;
import br.com.crud.repository.PessoaRepository;
import br.com.crud.repository.filter.PessoaFilter;
import br.com.crud.service.exception.PessoaNaoEncontradaException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa salvar(Pessoa pessoa) {
		adicionarTelefones(pessoa);
		return pessoaRepository.save(pessoa);
	}

	public Pessoa getPorId(Long id) {
		return pessoaRepository.findById(id).orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada"));
	}

	public Page<Pessoa> filtrar(PessoaFilter filter, Pageable pageable) {
		return pessoaRepository.filtrar(filter, pageable);
	}

	public void deletar(Long id) {
		pessoaRepository.deleteById(id);
	}

	public void atualizar(Long id, Pessoa pessoa) {
		Pessoa pessoaSalva = getPorId(id);
		pessoaSalva = removerTelefones(pessoa, pessoaSalva);
		pessoaSalva = adicionarTelefones(pessoa);
		pessoa.setId(id);
		pessoaRepository.save(pessoaSalva);
	}

	private Pessoa adicionarTelefones(Pessoa pessoa) {
		if(pessoa.getTelefones() != null && !pessoa.getTelefones().isEmpty()) {
			pessoa.getTelefones().forEach(t -> {
				t.setPessoa(pessoa);
			});
		}
		return pessoa;
	}
	
	private Pessoa removerTelefones(Pessoa pessoa, Pessoa pessoaSalva) {
		if(pessoaSalva.getTelefones() != null && !pessoaSalva.getTelefones().isEmpty()) {
			pessoaSalva.getTelefones().removeIf(t -> !pessoa.getTelefones().contains(t));
		}
		
		return pessoaSalva;
	}
}
