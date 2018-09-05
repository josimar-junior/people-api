package br.com.crud.repository.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.crud.model.Pessoa;
import br.com.crud.repository.filter.PessoaFilter;

public interface PessoaRepositoryQuery {

	Page<Pessoa> filtrar(PessoaFilter filter, Pageable pageable);
}
