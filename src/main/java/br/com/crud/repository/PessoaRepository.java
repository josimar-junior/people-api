package br.com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crud.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
