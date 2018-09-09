package br.com.crud.model.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<DetalheErro> handleDataInvalida(HttpMessageNotReadableException e, HttpServletRequest request) {

		DetalheErro detalheErro = new DetalheErro("Data inválida", 400L, System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detalheErro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<DetalheErro> handleCpfInvalido(MethodArgumentNotValidException e, HttpServletRequest request) {

		DetalheErro detalheErro = new DetalheErro("CPF inválido", 400L, System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detalheErro);
	}
}
