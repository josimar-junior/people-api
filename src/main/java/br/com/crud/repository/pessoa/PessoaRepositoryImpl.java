package br.com.crud.repository.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import br.com.crud.model.Pessoa;
import br.com.crud.repository.filter.PessoaFilter;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Pessoa> filtrar(PessoaFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Pessoa> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(PessoaFilter filter, CriteriaBuilder builder, Root<Pessoa> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(filter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get("nome")), "%" + filter.getNome() + "%"));
		}
		
		if(!StringUtils.isEmpty(filter.getCpf())) {
			predicates.add(builder.equal(root.get("cpf"), filter.getCpf()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
