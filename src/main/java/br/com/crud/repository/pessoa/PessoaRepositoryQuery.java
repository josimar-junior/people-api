package br.com.crud.repository.pessoa;

import java.util.List;

import br.com.crud.model.Pessoa;
import br.com.crud.repository.filter.PessoaFilter;

public interface PessoaRepositoryQuery {

	List<Pessoa> filtrar(PessoaFilter filter);
}
