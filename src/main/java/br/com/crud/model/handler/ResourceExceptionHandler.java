package br.com.crud.model.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.crud.model.DetalheErro;
import br.com.crud.service.exception.PessoaNaoEncontradaException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(PessoaNaoEncontradaException.class)
	public ResponseEntity<DetalheErro> handlePessoaNaoEncontradaException(PessoaNaoEncontradaException e, HttpServletRequest request) {

		DetalheErro detalheErro = new DetalheErro("A pessoa não pôde ser encontrada", 404L, System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
	}
}
