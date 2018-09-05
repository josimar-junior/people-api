package br.com.crud.service.exception;

public class PessoaNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 2503711739091834157L;

	public PessoaNaoEncontradaException(String msg) {
		super(msg);
	}
}
