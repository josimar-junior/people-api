package br.com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crud.model.Pessoa;
import br.com.crud.repository.pessoa.PessoaRepositoryQuery;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery {

}
